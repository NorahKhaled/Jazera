package com.example.nouraalrossiny.androidbottomnav;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static java.util.ResourceBundle.getBundle;

public class Payment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , AdapterView.OnItemSelectedListener{

    static final String fromEmail = "aljazeraha602@gmail.com";
    static final String fromPassword = "Jj123456789";
    //********************
   // FirebaseAuth mAuth;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    DatabaseReference requests;
    FirebaseDatabase database ;

    LinearLayout ship_info_Home;
    LinearLayout ship_info_branch;
    TextView date , Sun_Total;
    int number_choice;
    String Subject , info ;
    Button Submit;
    EditText add1 , add2 ;
    String B_choic;
    String Total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

//***************
        //mAuth = FirebaseAuth.getInstance();


        ship_info_branch = (LinearLayout)findViewById(R.id.linear2b);
        ship_info_Home = (LinearLayout)findViewById(R.id.linear2a);
        Submit = (Button)findViewById(R.id.S);
        Sun_Total = (TextView)findViewById(R.id.sub_total);

        number_choice = 0 ;

        //******************
        //Firebase
        database = FirebaseDatabase.getInstance();
        requests =database.getReference("Requests");
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);

        // Get Total number
        Intent iin= getIntent();
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null)
        {
            Total =(String) bundle.get("Total");
        }
        Sun_Total.setText(Total);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
        });

    }

    public void Home(View V) {
        number_choice=1;
        ship_info_Home.setVisibility(View.VISIBLE);
        ship_info_branch.setVisibility(View.INVISIBLE);
        add2 =(EditText)findViewById(R.id.address); // الوصف
        add1 = (EditText)findViewById(R.id.Nei);//الحي

    }


    public void Data(View V){
        DataPickerFragment datePicker = new DataPickerFragment();
        datePicker.show(getSupportFragmentManager(),"date picker");

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = (TextView) findViewById(R.id.tv_date);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateStrint = DateFormat.getDateInstance().format(c.getTime());
        date.setText(currentDateStrint);
    }



    public void Branch(View V) {

        number_choice=2;
        ship_info_branch.setVisibility(View.VISIBLE);
        ship_info_Home.setVisibility(View.INVISIBLE);


        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.branches, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        B_choic = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(),"يرجى اختيار الفرع ",Toast.LENGTH_LONG).show();
    }


    public void Submit(){

        if (number_choice == 0){
            Toast.makeText(getBaseContext(), "يرجى اختيار طريقة الإستلام", Toast.LENGTH_LONG).show();
        }
        else {

            if (number_choice == 1){
                Subject = "طلب جديد : التوصيل إلى المنزل";

                String info = "الحي :" + add1.getText().toString() + " الوصف : " + add2.getText().toString()+ " Total "+ Total;
                Message(Subject, info);

           //     Move_to_home();


            }
            else if (number_choice == 2){

                Subject="طلب جديد : الإستلام من الفرع";

                info = "استلام من فرع :" + B_choic + "في تاريخ" + date +" Total "+ Total;
                Message(Subject, info);



            }
            else {
                Toast.makeText(getApplicationContext(), "يرجى اختيار طريقة الإستلام", Toast.LENGTH_LONG).show();
            }

            String Add = add1 + " ," + add2;


            Request request = new Request(
                 //   mAuth.getCurrentUser().getPhoneNumber(),
                 //   mAuth.getCurrentUser().getDisplayName(),
                    //addres,
                    "0","sara",
                    Add,
                    Total,
                    cart

            );

            // Submite To firebase
            //We will using System.CurrentMilli to key
            requests.child(String.valueOf(System.currentTimeMillis()))
                    .setValue(request);

            //Delete Cart:

            new Database(getBaseContext()).CleanCart();
            // new Database(this).CleanCart();


            //
            //




        }

    }

    public void Message(String sub , String Body ){

        String toEmails = "amal.fr33@gmail.com";
        List<String> toEmailList = Arrays.asList(toEmails
                .split("\\s*,\\s*"));
        String emailSubject = sub;
        String emailBody = Body;
        new SendMailTask(this).execute(fromEmail,
                fromPassword, toEmailList, emailSubject, emailBody);

        Move_to_home();

    }

    public void Move_to_home()
    {
        Intent initen = new Intent(Payment.this,Invoice.class);
        startActivity(initen);

    }



}
