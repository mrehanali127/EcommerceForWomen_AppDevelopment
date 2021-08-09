package com.example.ecommerceforwomen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    EditText enteredPhoneNo,OTP;
    Button getOTP,verify;
    ProgressBar progressBar;
    TextView signup;
    String TAG="Rehan";
    String temp_phone;
    String phone;
    String verificationCodeBySystem;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        enteredPhoneNo=findViewById(R.id.phone1);
        OTP=findViewById(R.id.otp);
        getOTP=findViewById(R.id.btngetotp);
        verify=findViewById(R.id.verify);
        progressBar=findViewById(R.id.progress1);
        signup=findViewById(R.id.btnsignup1);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Personal_Info.class);
                startActivity(intent);
            }
        });

        getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long phoneNo=Long.parseLong(enteredPhoneNo.getText().toString().trim());
                phone=phoneNo.toString();
                Log.i("Rehan",phone);
                sendVerificationCodeToUser(phone);
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=OTP.getText().toString();

                if(code.isEmpty() || code.length()<6){
                    OTP.setError("Wrong OTP");
                    OTP.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            String phone_no=currentUser.getPhoneNumber();
            temp_phone=phone_no.substring(3);

        }
    }

    private void sendVerificationCodeToUser(String phone) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+92"+phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            // this function executed automatically
            // code entered by user or automatically
            String code=phoneAuthCredential.getSmsCode();
            if(code!=null){
                progressBar.setVisibility(View.VISIBLE);
                // Now lets verify  code automatically
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            Log.e("Rehan",e.getMessage());
        }

        // What if Code is sent to other device user have to put it manually
        @Override
        public void onCodeSent(String s,PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodeBySystem=s;

        }
    };


    private void verifyCode(String verificationCode){
        // verification code ---> code by user
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationCodeBySystem,verificationCode);
        loginByCredentials(credential);
    }

    // call when verification is done
    private void loginByCredentials(PhoneAuthCredential credential){

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this,"Login Successfully",
                                    Toast.LENGTH_SHORT).show();
                            db = FirebaseDatabase.getInstance();
                            root = db.getReference("users").child(temp_phone);
                            root.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    if (snapshot.hasChild("client_id")) {
                                        Log.i(TAG,"HAS Child");
                                        // run some code
                                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    else if(snapshot.hasChild("artist_id")){
                                        Log.i(TAG,"HAS Artist");
                                        // run some code
                                        Intent intent=new Intent(LoginActivity.this,Main_for_Artist.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });

                            /*
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();*/

                        }
                        else{
                            Toast.makeText(LoginActivity.this,task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}