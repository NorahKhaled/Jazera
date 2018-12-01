package com.example.nouraalrossiny.androidbottomnav;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    // private static final String TAG = "SignUpActivity";

    private Context mContext;
    private EditText editTextName, editTextEmail, editTextPassword, editTextPhone;
    String email,name,password, phone;
    private ProgressBar progressBar;
    Button btnSignUp;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference myRef;

    private FirebaseAuth.AuthStateListener mAuthListener;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mContext = SignUpActivity.this;

        editTextName = findViewById(R.id.nameSignUp);
        editTextEmail = findViewById(R.id.emailSignUp);
        editTextPassword = findViewById(R.id.passwordSignUp);
        editTextPhone = findViewById(R.id.phoneSignUp);
        btnSignUp = findViewById(R.id.buttonSignUp);
        Log.d("TAGReem", "onCreate: started.");


        progressBar = findViewById(R.id.progressbarSignUp);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
        setupFirebaseAuth();
    }

    public void registerUser(){
        name = editTextName.getText().toString().trim();
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        phone = editTextPhone.getText().toString().trim();

        if (checkInputs(email, name, password)) {

            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("TAGReem", "createUserWithEmail:onComplete:" + task.isSuccessful());


                            if (task.isSuccessful()) {
                                uid= mAuth.getCurrentUser().getUid();
                                Log.d("TAGReem", "onComplete: Authstate changed: " + uid);
                                progressBar.setVisibility(View.INVISIBLE);
                                Users user= new Users(email, name ,phone);
                                Log.d("TAGReem", "onAuthStateChanged:Add phone:");
                                myRef.child("Users").child(uid).setValue(user);
                            }
                            else {
                                Log.w("TAGReem", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }//end Account
                    });

        }
    }


    private boolean checkInputs(String email, String username, String password){
        Log.d("TAGReem", "checkInputs: checking inputs for null values.");
        if(email.equals("") || username.equals("") || password.equals("")){
            Toast.makeText(mContext, "All fields must be filled out.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getCurrentUser().getUid();
        }
    }


     /*
    ------------------------------------ Firebase ---------------------------------------------
     */

    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth() {
        Log.d("TAGReem", "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();
        //mfirebaseDatabase=FirebaseDatabase.getInstance(); //authintication to DB
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                registerUser();
                String action;
                Intent i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;

            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
