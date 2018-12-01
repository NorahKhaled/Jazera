package com.example.nouraalrossiny.androidbottomnav.viewHolder;


import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.nouraalrossiny.androidbottomnav.ChartFragment;
import com.example.nouraalrossiny.androidbottomnav.R;
import com.example.nouraalrossiny.androidbottomnav.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.example.nouraalrossiny.androidbottomnav.Interface.itemClickListener;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView txt_cart_name , txt_price;
    public ImageView img_cart_count;

    private itemClickListener itemClickListener;

   // private MenuItem.OnMenuItemClickListener itemClickListener;
    //private ItemClickListener itemClickListener;
   // private AdapterView.OnItemClickListener itemClickListener;
   // https://www.youtube.com/watch?v=WtLZK1kh-yM
    public void setTxt_cart_name(TextView txt_cart_name){
        this.txt_cart_name = txt_cart_name;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        txt_cart_name = (TextView)itemView.findViewById(R.id.cart_item_name);
        txt_price =(TextView)itemView.findViewById(R.id.cart_item_price);
        img_cart_count = (ImageView)itemView.findViewById(R.id.cart_item_count);
    }

    @Override
    public void onClick(View view) {

    }
}


public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> listData = new ArrayList<>();
   // private List<Order> listData ;

    private Context context;


    public CartAdapter(List<Order> listData , Context context){
        listData = new ArrayList<>();
        this.listData = listData;
        this.context = context;

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //  listData = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);

        //Locale locale = new Locale("en","US");
        Locale locale = new Locale("ar","KSA");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);


        int price = (Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_price.setText(fmt.format(price));
        holder.txt_cart_name.setText(listData.get(position).getProductName());


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
