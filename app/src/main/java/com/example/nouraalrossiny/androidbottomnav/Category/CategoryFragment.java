package com.example.nouraalrossiny.androidbottomnav.Category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.example.nouraalrossiny.androidbottomnav.Category.FoodList;
import com.example.nouraalrossiny.androidbottomnav.Interface.itemClickListener;
import com.example.nouraalrossiny.androidbottomnav.R;
import com.example.nouraalrossiny.androidbottomnav.model.catgry;
import com.example.nouraalrossiny.androidbottomnav.viewHolder.menuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class CategoryFragment extends Fragment {

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference category;
    FirebaseRecyclerAdapter<catgry, menuViewHolder> adapter;
    private static final String TAG = "CategoryFragment";

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "OnCreat: started");
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recycler_menu = (RecyclerView) view.findViewById(R.id.recycler_menu);
        layoutManager = new LinearLayoutManager(getActivity());
        recycler_menu.setLayoutManager(layoutManager);
        category = FirebaseDatabase.getInstance().getReference("Category");
        recycler_menu = (RecyclerView)view.findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recycler_menu.setLayoutManager(layoutManager);

        loadMenu();
        return view;
    }

    private void loadMenu() {
        Log.d(TAG, "loadMenu: start");
        adapter = new FirebaseRecyclerAdapter<catgry, menuViewHolder>(catgry.class,R.layout.menu_item,menuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(menuViewHolder viewHolder, catgry model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.imageview);
                final catgry clickItem = model;
                viewHolder.setItemClickListener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClik) {
                        Intent intent = new Intent(getActivity(), FoodList.class);
                        intent.putExtra("CategoryId", adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}

