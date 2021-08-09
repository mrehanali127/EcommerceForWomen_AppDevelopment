package com.example.ecommerceforwomen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash_Activity extends AppCompatActivity {
    Button started;
    String TAG="Rehan";
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference root;
    String temp_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        started=findViewById(R.id.btngetstarted);
        mAuth = FirebaseAuth.getInstance();


        started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    String phone_no = currentUser.getPhoneNumber();
                    temp_phone = phone_no.substring(3);
                    //Log.i("REHAN",temp_phone);
                    //Log.i("REHAN",phone_no);
                    db = FirebaseDatabase.getInstance();
                    root = db.getReference("users").child(temp_phone);

                    root.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.hasChild("client_id")) {
                                //.i(TAG,"HAS Child");
                                // run some code
                                Intent intent=new Intent(Splash_Activity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            else if(snapshot.hasChild("artist_id")){
                                //Log.i(TAG,"HAS Artist");
                                // run some code
                                Intent intent=new Intent(Splash_Activity.this,Main_for_Artist.class);
                                startActivity(intent);
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Splash_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                Intent intent=new Intent(Splash_Activity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}