package com.example.nouraalrossiny.androidbottomnav;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nouraalrossiny.androidbottomnav.Database.Database;
import com.example.nouraalrossiny.androidbottomnav.model.Order;
import com.example.nouraalrossiny.androidbottomnav.model.Request;
import com.example.nouraalrossiny.androidbottomnav.viewHolder.CartAdapter;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

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

    public ChartFragment() {
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
        //layoutManager = new LinearLayoutManager(this);
        // layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice=(TextView)RootView.findViewById(R.id.total);
        btnplace = (Button)RootView.findViewById(R.id.btnPlaceOrder);

        btnplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create new Request
                //Create new Request

                //   showAlertDialog();
                String Total = txtTotalPrice.getText().toString();
/*if (mAuth.getCurrentUser() != null ) {
                    Intent intent = new Intent(getActivity(),Payment.class);
                    intent.putExtra("Total",Total);
                    startActivity(intent);
                }
                else*/


if(FirebaseAuth.getInstance().getCurrentUser() != null){

    Intent intent = new Intent(getActivity(),Payment.class);
    intent.putExtra("Total",Total);
    startActivity(intent);}
else  if (Total == "0"){
                    Toast.makeText(getActivity(), "السلة فارغة", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(), "يرجى تسجيل الدخول لإكمال عملية الشراء", Toast.LENGTH_LONG).show();
                }




            }
        });
        loadListFood();



        return RootView;


    }


    private void loadListFood() {
        //cart = new Database(this).getCarts();
        cart = new Database(getActivity()).getCarts();

        //cart = new Database(this).getCarts();
        // adapter = new CartAdapter(cart,this);
        //adapter = new CartAdapter(cart,this);
        adapter = new CartAdapter(cart,getActivity());
        recyclerView.setAdapter(adapter);

        //Calculate total price
        int total = 0;
        for (Order order:cart) {
//Minute 36
            int Item_order = Integer.parseInt(order.getPrice().replaceAll("[\\D]",""));
            //int Item_order = Integer.parseInt("3");
            int Item_Q = (Integer.parseInt(order.getQuantity()));

            total +=Item_order * Item_Q;
        }
        Locale locale = new Locale("ar","KSA");
        // Locale locale = new Locale("ar","KSA");

        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));





    }

}
