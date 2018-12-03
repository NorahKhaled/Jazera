package com.example.nouraalrossiny.androidbottomnav.Category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.example.nouraalrossiny.androidbottomnav.Interface.itemClickListener;
import com.example.nouraalrossiny.androidbottomnav.R;
import com.example.nouraalrossiny.androidbottomnav.model.Food;
import com.example.nouraalrossiny.androidbottomnav.viewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodList extends AppCompatActivity {


    private static final String TAG = "FoodList";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference foodList;
    String categoryId = "";
    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        Log.d(TAG, "OnCreate : start");
        init();
    }

    private void loadListFood(String categoryId) {
        Log.d(TAG, "LoadList : start");
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodList.orderByChild("MenuID").equalTo(categoryId) // Like : Select * From Foods where MenuId =
        ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.food_name.setText(model.getName());
                Log.d("TAG", ""+adapter.getItemCount());
                //we put it later
               // Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.food_image);

                final Food local = model;
                viewHolder.setItemClickListener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClik) {
                        Intent foodDetail = new Intent(FoodList.this, FoodDetail.class);
                        foodDetail.putExtra("FoodId", adapter.getRef(position).getKey()); //Send food Id to new activity
                        startActivity(foodDetail);
                    }
                });
            }
        };

        // Set Adapter
        recyclerView.setAdapter(adapter);
    }

    private void init(){

        //Firebase Init
        foodList = FirebaseDatabase.getInstance().getReference("Foods");
        recyclerView = findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent Here
        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && !categoryId.equals("null")){
            loadListFood(categoryId);
        }
    }

}
