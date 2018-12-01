package com.example.nouraalrossiny.androidbottomnav;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


//i don't need it
public class ListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return OurData.names.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView MenuTxt;
        private ImageView MenuImg;

        public ListViewHolder(View itemView) {
            super(itemView);
            MenuTxt = (TextView)itemView.findViewById(R.id.menu_name);
            MenuImg = (ImageView) itemView.findViewById(R.id.menu_image);
            itemView.setOnClickListener(this);
        }

        public void bindView(int i){
            MenuTxt.setText(OurData.names[i]);
            MenuImg.setImageResource(OurData.imgs[i]);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
