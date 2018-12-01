package com.example.nouraalrossiny.androidbottomnav.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nouraalrossiny.androidbottomnav.Interface.itemClickListener;
import com.example.nouraalrossiny.androidbottomnav.R;

public class menuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView txtMenuName;
    public ImageView imageview;

    private itemClickListener itemClickListener;

    public menuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageview = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(itemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
