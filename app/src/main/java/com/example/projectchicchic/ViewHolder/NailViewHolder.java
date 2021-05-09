package com.example.projectchicchic.ViewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectchicchic.Interface.itemClickListenner;
import com.example.projectchicchic.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imgNail;
    TextView text_nameStore, text_type,text_price,branch;

    private itemClickListenner itemClickListenner;

    public NailViewHolder(@NonNull View itemView) {
        super(itemView);
        imgNail = (ImageView)itemView.findViewById(R.id.imgNail);
        text_nameStore = (TextView)itemView.findViewById(R.id.text_nameStore);
        text_type = (TextView)itemView.findViewById(R.id.text_type);
        text_price = (TextView)itemView.findViewById(R.id.text_price);
        branch = (TextView)itemView.findViewById(R.id.branch);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListenner(com.example.projectchicchic.Interface.itemClickListenner itemClickListenner) {
        this.itemClickListenner = itemClickListenner;

    }

    @Override
    public void onClick(View v) {
        itemClickListenner.onClick(v,getAdapterPosition(),false);

    }
}
