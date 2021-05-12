package com.example.projectchicchic.Booking;

import android.widget.Filter;
import android.widget.Filterable;

import com.example.projectchicchic.Model.model;
import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.ChangeEventListener;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @param <T>  model class, for parsing {@link DocumentSnapshot}s.
 * @param <VH> {@link RecyclerView.ViewHolder} class.
 */
public abstract class FilterableFirestoreRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH>
        implements ChangeEventListener, LifecycleObserver, Filterable {

    private static final String TAG = "FirestoreRecycler";

    private FirebaseRecyclerOptions<T> mOptions;
    private final ObservableSnapshotArray<T> mSnapshots;
    private final List<T> list, backupList;
    private CustomFilter mCustomFilter;
    private boolean isFiltarable;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirebaseRecyclerOptions} for configuration options.
     */
    public FilterableFirestoreRecyclerAdapter(@NonNull FirebaseRecyclerOptions<T> options, boolean isFiltarable) {
        mOptions = options;
        mSnapshots = options.getSnapshots();

        list = new ArrayList<>();
        backupList = new ArrayList<>();
        if (options.getOwner() != null) {
            options.getOwner().getLifecycle().addObserver(this);
        }
        this.isFiltarable = isFiltarable;
    }

    /**
     * Start listening for database changes and populate the adapter.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void startListening() {
        if (!mSnapshots.isListening(this)) {
            mSnapshots.addChangeEventListener(this);
        }
    }

    /**
     * Stop listening for database changes and clear all items in the adapter.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stopListening() {
        mSnapshots.removeChangeEventListener(this);
        notifyDataSetChanged();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void cleanup(LifecycleOwner source) {
        source.getLifecycle().removeObserver(this);
    }

    /**
     * Returns the backing {@link ObservableSnapshotArray} used to populate this adapter.
     *
     * @return the backing snapshot array
     */
    @NonNull
    public ObservableSnapshotArray<T> getSnapshots() {
        return mSnapshots;
    }

    /**
     * Gets the item at the specified position from the backing snapshot array.
     *
     * @see ObservableSnapshotArray#get(int)
     */
    @NonNull
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void onChildChangedTmp(@NonNull ChangeEventType type,
                               @NonNull DataSnapshot snapshot,
                               int newIndex,
                               int oldIndex) {
        T model = mSnapshots.get(newIndex);
        onChildUpdate(model, type, snapshot, newIndex, oldIndex);
    }

    protected void onChildUpdate(T model, ChangeEventType type,
                                 DataSnapshot snapshot,
                                 int newIndex,
                                 int oldIndex) {
        switch (type) {
            case ADDED:
                addItem(snapshot.getKey(), model);
                notifyItemInserted(newIndex);
                break;
            case CHANGED:
                addItem(snapshot.getKey(), model, newIndex);
                notifyItemChanged(newIndex);
                break;
            case REMOVED:
                removeItem(newIndex);
                notifyItemRemoved(newIndex);
                break;
            case MOVED:
                moveItem(snapshot.getKey(), model, newIndex, oldIndex);
                notifyItemMoved(oldIndex, newIndex);
                break;
            default:
                throw new IllegalStateException("Incomplete case statement");
        }
    }

    private void moveItem(String key, T t, int newIndex, int oldIndex) {
        list.remove(oldIndex);
        list.add(newIndex, t);
        if (isFiltarable) {
            backupList.remove(oldIndex);
            backupList.add(newIndex, t);
        }
    }

    private void removeItem(int newIndex) {
        list.remove(newIndex);
        if (isFiltarable)
            backupList.remove(newIndex);
    }

    private void addItem(String key, T t, int newIndex) {
        list.remove(newIndex);
        list.add(newIndex, t);
        if (isFiltarable) {
            backupList.remove(newIndex);
            backupList.add(newIndex, t);
        }
    }

    private void addItem(String id, T t) {
        list.add(t);
        if (isFiltarable)
            backupList.add(t);
    }

    @Override
    public void onDataChanged() {
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindViewHolder(holder, position, getItem(position));
    }

    /**
     * @param model the model object containing the data that should be used to populate the view.
     * @see #onBindViewHolder(RecyclerView.ViewHolder, int)
     */
    protected abstract void onBindViewHolder(@NonNull VH holder, int position, @NonNull T model);

    /**
     * filter condition for Filter
     *
     * @param model         model T
     * @param filterPattern filter pattern with Lower Case
     */
    protected boolean filterCondition(T model, String filterPattern) {
        model tmp = (model)model;
        return tmp.getNameStore().toLowerCase().contains(filterPattern) || tmp.getBranch().toLowerCase().contains(filterPattern)
                || tmp.getType().toLowerCase().contains(filterPattern)|| tmp.getPriceNail().toLowerCase().contains(filterPattern)|| tmp.getTime().toLowerCase().contains(filterPattern);
    }

    @Override
    public Filter getFilter() {
        if (mCustomFilter == null) {
            mCustomFilter = new CustomFilter();
        }
        return mCustomFilter;
    }

    public class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                results.values = backupList;
                results.count = backupList.size();
            } else {
                List<T> filteredList = new ArrayList<>();
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (T t : backupList) {
                    if (filterCondition(t, filterPattern)) {
                        filteredList.add(t);
                    }
                }
                results.values = filteredList;
                results.count = filteredList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends T>) results.values);
            notifyDataSetChanged();
        }
    }
}