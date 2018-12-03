package com.example.nouraalrossiny.androidbottomnav.Cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nouraalrossiny.androidbottomnav.MainActivity;
import com.example.nouraalrossiny.androidbottomnav.R;

public class Invoice extends AppCompatActivity {

    Button back_home ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        // https://www.youtube.com/watch?v=plQIpqBcdQE
        back_home = (Button)findViewById(R.id.home_app);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
