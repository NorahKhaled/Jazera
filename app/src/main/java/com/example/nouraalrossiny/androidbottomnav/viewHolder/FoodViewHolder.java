package com.example.nouraalrossiny.androidbottomnav.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nouraalrossiny.androidbottomnav.Interface.itemClickListener;
import com.example.nouraalrossiny.androidbottomnav.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView food_name;
   // public ImageView food_image;

    private itemClickListener ItemClickListener;

    public void setItemClickListener(itemClickListener ItemClickListener) {
        this.ItemClickListener = ItemClickListener;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_name = itemView.findViewById(R.id.food_name);
      //  food_image = itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ItemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
