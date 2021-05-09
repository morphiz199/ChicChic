package com.example.projectchicchic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cuberto.liquid_swipe.ViewI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//public class MyViewHolder  extends RecyclerView.Adapter<MyViewHolder.ViewHolder> {
//    private static final String Tag = "RecyclerView";
//    private Context mContext;
//    private ArrayList<Nail> nailsList;


//    public MyViewHolder(Context mContext, ArrayList<Nail> nailsList) {
//        this.mContext = mContext;
//        this.nailsList = nailsList;
//    }
//
//
//    @NonNull
//    @Override
//    public MyViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_user_search, parent, false );
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.textView.setText(nailsList.get(position).getTypeNail());
//
//        Glide.with(mContext)
//                .load(nailsList.get(position).getImageNail())
//                .into(holder.imageView);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return nailsList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        ImageView imageView;
//        TextView textView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            imageView = itemView.findViewById(R.id.image_single_view);
//            textView = itemView.findViewById(R.id.text_type);
//        }
//    }
//}
