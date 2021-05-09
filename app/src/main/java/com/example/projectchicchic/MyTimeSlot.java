package com.example.projectchicchic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyTimeSlot extends RecyclerView.Adapter<MyTimeSlot.MyViewHolder> {

    public static final int TIME_SLOT_TOTAL =20;

    Context context;
    List<TimeSlot> timeSlotList;

    public MyTimeSlot(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_time_slot,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_time_slot.setText(new StringBuilder(convertTimeSlotToString(position)).toString());
        if (timeSlotList.size() == 0 ){
            holder.txt_time_slot_description.setText("Available");
        }

    }

    private String convertTimeSlotToString(int position) {
        switch (position)
        {
            case 0 :
            return "9.00-9.30";
            case 1 :
                return "9.30-10.00";
            case 2 :
                return "10.00-10.30";
            case 3 :
                return "10.30-11.00";
            case 4 :
                return "11.00-11.30";
            case 5 :
                return "11.30-12.00";
            case 6 :
                return "12.00-12.30";
            case 7 :
                return "12.30-13.00";
            case 8 :
                return "13.00-13.30";
            case 9 :
                return "13.30-14.00";
            case 10 :
                return "14.00-14.30";
            case 11 :
                return "14.30-15.00";
            case 12 :
                return "15.00-15.30";
            case 13 :
                return "15.30-16.00";
            case 14 :
                return "16.00-16.30";
            case 15 :
                return "16.30-17.00";
            case 16 :
                return "17.00-17.30";
            case 17 :
                return "17.30-18.00";
            case 18 :
                return "18.00-18.30";
            case 19 :
                return "18.30-19.00";
            default:
                return "Close";
        }

    }

    @Override
    public int getItemCount() {
        return TIME_SLOT_TOTAL;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_time_slot,txt_time_slot_description ;
        CardView card_time_slot;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_time_slot = (CardView)itemView.findViewById(R.id.card_time_slot);
            txt_time_slot = (TextView)itemView.findViewById(R.id.card_time_slot);
            txt_time_slot_description = (TextView)itemView.findViewById(R.id.card_time_slot);

        }
    }
}
