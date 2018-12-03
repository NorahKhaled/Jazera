package com.example.nouraalrossiny.androidbottomnav.Cart;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nouraalrossiny.androidbottomnav.Database.Database;
import com.example.nouraalrossiny.androidbottomnav.R;
import com.example.nouraalrossiny.androidbottomnav.model.Order;
import com.example.nouraalrossiny.androidbottomnav.viewHolder.CartAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseAuth mAuh;

    FirebaseDatabase database ;
    DatabaseReference  requests;
    View view;
    TextView txtTotalPrice;
    Button btnplace;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;
    int total;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView =inflater.inflate(R.layout.fragment_chart, container, false);
        // Inflate the layout for this fragment

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests =database.getReference("Requests");
        //requests = database.getReference("Requsts");

        //Init

        recyclerView =(RecyclerView)RootView.findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice=(TextView)RootView.findViewById(R.id.total);
        btnplace = (Button)RootView.findViewById(R.id.btnPlaceOrder);

        btnplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Total = txtTotalPrice.getText().toString();

                if (total==0){
                    Toast.makeText(getActivity(), "السلة فارغة", Toast.LENGTH_LONG).show();
                }
                else if(FirebaseAuth.getInstance().getCurrentUser() != null ){
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    String user_id =  mAuth.getCurrentUser().getUid();
                    Intent intent = new Intent(getActivity(),Payment.class);
                    intent.putExtra("Total",Total);
                    intent.putExtra("user_id",user_id);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "يرجى تسجيل الدخول لإكمال عملية الشراء", Toast.LENGTH_LONG).show();
                }
            }
        });

        loadListFood();

        return RootView;
    }


    private void loadListFood() {
        cart = new Database(getActivity()).getCarts();


        adapter = new CartAdapter(cart,getActivity());
        recyclerView.setAdapter(adapter);

        //Calculate total price
        total = 0;
        for (Order order:cart) {
            int Item_order = Integer.parseInt(order.getPrice().replaceAll("[\\D]",""));
            int Item_Q = (Integer.parseInt(order.getQuantity()));

            total +=Item_order * Item_Q;
        }
        Log.d("TAGReem", "Total : " + total);
        Locale locale = new Locale("ar","SA");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));





    }

}