package com.example.projectchicchic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectchicchic.Booking.FilterableFirestoreRecyclerAdapter;
import com.example.projectchicchic.Model.loadSuccess;
import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapterSuccess extends FilterableFirestoreRecyclerAdapter<loadSuccess,myAdapterSuccess.myviewholder> {

    public Context c;
    public ArrayList<loadSuccess> arrayList;
    public myAdapterSuccess(@NonNull FirebaseRecyclerOptions<loadSuccess> options) {
        super(options, true);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull loadSuccess loadSuccess) {
        holder .user_booking.setText(loadSuccess.getUser());
        holder .text_type.setText(loadSuccess.getNameTtpe());
        holder .text_price.setText(loadSuccess.getPrice());
        holder.branch.setText(loadSuccess.getBranch());
        holder.date.setText(loadSuccess.getDate());
        holder.time.setText(loadSuccess.getTime());

    }





    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_booking,parent,false);
        return  new myviewholder(view);
    }

    @Override
    public void onChildChanged(@NonNull ChangeEventType type, @NonNull DataSnapshot snapshot, int newIndex, int oldIndex) {
        onChildChangedTmp(type,snapshot,newIndex,oldIndex);

    }

    @Override
    public void onError(@NonNull DatabaseError databaseError) {

    }



    public class myviewholder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout;
        TextView user_booking, text_type,text_price,branch,date,time;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = (ConstraintLayout)itemView.findViewById(R.id.constraintLayout);
            user_booking = (TextView)itemView.findViewById(R.id.text_nameUserBooking);
            text_type = (TextView)itemView.findViewById(R.id.text_type);
            text_price = (TextView)itemView.findViewById(R.id.text_price);
            branch = (TextView)itemView.findViewById(R.id.branch);
            date = (TextView)itemView.findViewById(R.id.date);
            time = (TextView)itemView.findViewById(R.id.time);
        }
    }
}
