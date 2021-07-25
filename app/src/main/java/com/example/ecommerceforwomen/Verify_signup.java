package com.example.ecommerceforwomen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Verify_signup extends AppCompatActivity {
    EditText OTP;
    Button verify,getotp;
    ProgressBar progressBar;
    TextView login;
    String phone;
    String verificationCodeBySystem;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    User new_user;

    String fname=null;
    String lname=null;
    Long phoneNo=null;
    String DOB=null;
    String gender=null;
    String city=null;
    String tehsil=null;
    String address=null;
    String client_id;
    String artist_id;
    ArrayList<String> skills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_signup);

        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("users");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.size()==8) {
            fname = bundle.getString("fname");
            lname = bundle.getString("lname");
            phoneNo= bundle.getLong("phone");
            DOB=bundle.getString("DOB");
            gender=bundle.getString("gender");
            city=bundle.getString("city");
            tehsil=bundle.getString("tehsil");
            address=bundle.getString("address");
            client_id=mAuth.getInstance().getCurrentUser().getUid();
            new_user=new User(client_id,fname,lname,phoneNo,DOB,gender,city,tehsil,address);
        }

        else if(bundle!=null && bundle.size()>8){
            fname = bundle.getString("fname");
            lname = bundle.getString("lname");
            phoneNo= bundle.getLong("phone");
            DOB=bundle.getString("DOB");
            gender=bundle.getString("gender");
            city=bundle.getString("city");
            tehsil=bundle.getString("tehsil");
            address=bundle.getString("address");
            skills=bundle.getStringArrayList("skills");
            artist_id=mAuth.getInstance().getCurrentUser().getUid();
            new_user=new User(artist_id,fname,lname,phoneNo,DOB,gender,city,tehsil,address,skills);
        }


        phone=phoneNo.toString();
        OTP=findViewById(R.id.otpForVerification);
        progressBar=findViewById(R.id.progress_verify);
       // getotp=findViewById(R.id.getOTP2);
        verify=findViewById(R.id.verifyOTP);
        login=findViewById(R.id.login2);

        sendVerificationCodeToUser(phone);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Verify_signup.this,LoginActivity.class);
                startActivity(intent);
                finish();
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


    private void sendVerificationCodeToUser(String phone) {
        progressBar.setVisibility(View.VISIBLE);
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
            Toast.makeText(Verify_signup.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
                .addOnCompleteListener(Verify_signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            reference.child(mAuth.getInstance().getCurrentUser().getUid()).setValue(new_user);
                            Log.i("Rehan","Data Inserted");
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(Verify_signup.this,"Account Created Successfully",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Verify_signup.this,Splash_Activity.class);
                            startActivity(intent);
                            finish();

                        }
                        else{
                            Toast.makeText(Verify_signup.this,task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}