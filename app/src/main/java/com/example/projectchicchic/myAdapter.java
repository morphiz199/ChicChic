package com.example.projectchicchic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectchicchic.Booking.FilterableFirestoreRecyclerAdapter;
import com.example.projectchicchic.Booking.descfragment;
import com.example.projectchicchic.Model.model;
import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends FilterableFirestoreRecyclerAdapter<model,myAdapter.myviewholder> {

    public Context c;
    public ArrayList<model> arrayList;

    public myAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options,true);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model model) {
            holder .text_nameStore.setText(model.getNameStore());
            holder .text_type.setText(model.getTypeNail());
            holder .text_price.setText(model.getPriceNail());
            holder.branch.setText(model.getBranch());
            Glide.with(holder.imgNail.getContext()).load(model.getImageUrl()).into(holder.imgNail);

            holder.imgNail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity)v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new descfragment(model.getNameStore(),model.getTypeNail(),model.getPriceNail(),model.getImageUrl(),model.getBranch()))
                            .addToBackStack(null).commit();
                }
            });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        return  new myviewholder(view);
    }

    @Override
    public void onChildChanged(@NonNull ChangeEventType type, @NonNull DataSnapshot snapshot, int newIndex, int oldIndex) {
        onChildChangedTmp(type,snapshot,newIndex,oldIndex);
    }

    @Override
    public void onError(@NonNull DatabaseError databaseError) {

    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ConstraintLayout constraintLayout;
        ImageView imgNail;
        TextView text_nameStore, text_type,text_price,branch;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = (ConstraintLayout)itemView.findViewById(R.id.constraintLayout);
            imgNail = (ImageView)itemView.findViewById(R.id.imgNail);
            text_nameStore = (TextView)itemView.findViewById(R.id.text_nameStore);
            text_type = (TextView)itemView.findViewById(R.id.text_type);
            text_price = (TextView)itemView.findViewById(R.id.text_price);
            branch = (TextView)itemView.findViewById(R.id.branch);
        }

    }
}
