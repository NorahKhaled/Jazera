package com.example.nouraalrossiny.androidbottomnav.Cart;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.nouraalrossiny.androidbottomnav.R;
import com.example.nouraalrossiny.androidbottomnav.model.Order;
import com.example.nouraalrossiny.androidbottomnav.model.Request;
import com.example.nouraalrossiny.androidbottomnav.viewHolder.CartAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Payment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , AdapterView.OnItemSelectedListener{

    static final String fromEmail = "aljazeraha602@gmail.com";
    static final String fromPassword = "Jj123456789";
    //********************
    FirebaseAuth mAuth;

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
    String Total, user_id;
    String currentDateStrint;

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
            user_id = (String) bundle.get("user_id");
            Log.d("TAGReem", "Bundle :" + user_id);
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
        currentDateStrint = DateFormat.getDateInstance().format(c.getTime());
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


            if (number_choice == 1){
                String Addreess1 = add1.getText().toString();
                String Addreess2 = add2.getText().toString();

                if(TextUtils.isEmpty(Addreess1)) {
                    add1.setError("الرجاء إدخال الحي");
                    return;}
                else if(TextUtils.isEmpty(Addreess2)){ add2.setError("الرجاء إدخال الوصف");
                    return;
                }
                else {
                    Subject = "طلب جديد : التوصيل إلى المنزل";
                    String addres1 = add1.getText().toString();
                    String addres2 = add2.getText().toString();
                    info = "الحي :" + addres1 + " الوصف : " + addres2 + " Total " + Total;
                    Message(Subject, info);
                    String Add = addres1  + " ," + addres2;
                    submitReq(Add);
                }
            }
            else if (number_choice == 2){
                if( date == null){
                    Toast.makeText(getBaseContext(), "يرجى تحديد تاريخ الإستلام", Toast.LENGTH_LONG).show();
                }
                else {
                    Subject = "طلب جديد : الإستلام من الفرع";
                    info = "استلام من فرع :" + B_choic + "في تاريخ" + date + " Total " + Total;
                    Log.d("TAGReem", "sub");
                    Message(Subject, info);
                    Log.d("TAGReem", "sub 2");
                    submitReq("");
                    Log.d("TAGReem", "sub 3");
                }

            }
            else {
                Toast.makeText(getApplicationContext(), "يرجى اختيار طريقة الإستلام", Toast.LENGTH_LONG).show();
            }
            new Database(getBaseContext()).CleanCart();
        }



    public void submitReq(final String add){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("Users").child(user_id);
        Log.d("TAGReem", "ID: " + user_id);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Code
                Log.d("TAGReem", "start ");
                String phone= dataSnapshot.child("phone").getValue().toString();
                Log.d("TAGReem", "phone:: " + phone);
                String name= dataSnapshot.child("name").getValue().toString();
                Log.d("TAGReem", "name:: " + name);

                Request request = new Request(phone ,name , add, Total, cart);
                requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAGReem", "onCancelled", databaseError.toException());
            }
        });
    }

    public void Message(String sub , String Body ){

        String toEmails = "amal.fr33@gmail.com";
        List<String> toEmailList = Arrays.asList(toEmails
                .split("\\s*,\\s*"));
        String emailSubject = sub;
        String emailBody = Body;
        new SendMailTask(this).execute(fromEmail,
                fromPassword, toEmailList, emailSubject, emailBody);

        Move_to_Invoice();

    }

    public void Move_to_Invoice()
    {
        Intent initen = new Intent(Payment.this,Invoice.class);
        startActivity(initen);

    }



}