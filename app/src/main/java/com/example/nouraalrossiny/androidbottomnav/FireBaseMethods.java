package com.example.nouraalrossiny.androidbottomnav;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseMethods {

    private static final String TAG = "FirebaseMethods";

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    private Context mContext;
    public FireBaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mfirebaseDatabase=FirebaseDatabase.getInstance(); //authintication to DB
        myRef=mfirebaseDatabase.getReference();
        mContext = context;

        if(mAuth.getCurrentUser() != null){
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public void addNewUser(String email , String userName,long phone){
        Users user= new Users(email,userName,phone);

        myRef.child("Users").child(userID)
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Log.d(TAG, "Details Added: " );
                Log.d(TAG, "Details not Added: " );
            }
        });
    }
}
