package com.example.nouraalrossiny.androidbottomnav.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nouraalrossiny.androidbottomnav.MainActivity;
import com.example.nouraalrossiny.androidbottomnav.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity  implements View.OnClickListener{
    private static final int CHOOSE_IMAGE = 101;

    private static final String TAG = "ProfileActivity";
    TextView UserNameDisplay;
    ProgressBar progressBar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "OnCreate: start");

        init();
        loadUserInformation();
    }

    private void loadUserInformation() {
        Log.d(TAG, "LoadInfo: start");
        final FirebaseUser  user = mAuth.getCurrentUser();

        if(user!=null){
            if(user.getDisplayName() != null){
                UserNameDisplay.setText(user.getDisplayName());
            }
            UserNameDisplay.setText("");
        }
    }


    @Override
    protected void onStart() {
        Log.d(TAG, "onStar: start");
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }


    private void delete() {
        Log.d(TAG, "Delete: start");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid= mAuth.getCurrentUser().getUid();
            DatabaseReference us = FirebaseDatabase.getInstance().getReference("Users").child(uid);
            us.removeValue();
            progressBar.setVisibility(View.VISIBLE);
            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "تم حذف الحساب بنجاح", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.deleteAccount_Profile:
                delete();
                finish();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                break;
            case R.id.user_Logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));

            case R.id.BacktoHome:
                startActivity(new Intent(this, MainActivity.class));

        }
    }

    private void init(){
        mAuth = FirebaseAuth.getInstance();
        UserNameDisplay =findViewById(R.id.userName_prfile);
        progressBar = findViewById(R.id.profile_progressbar);
        progressBar.setVisibility(View.GONE);

        findViewById(R.id.deleteAccount_Profile).setOnClickListener(this);
        findViewById(R.id.BacktoHome).setOnClickListener(this);
        findViewById(R.id.user_Logout).setOnClickListener(this);
    }
}
