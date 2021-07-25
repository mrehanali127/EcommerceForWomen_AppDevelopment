package com.example.ecommerceforwomen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class Address_Info extends AppCompatActivity {
    String TAG="Rehan";
    EditText City,Tehsil,complete_Address;
    Button login_btn,register_artist;

    String fname=null;
    String lname=null;
    Long phone=null;
    String DOB=null;
    String gender=null;
    String city=null;
    String tehsil=null;
    String address=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address__info);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fname = bundle.getString("fname");
            lname = bundle.getString("lname");
            phone= bundle.getLong("phone");
            DOB=bundle.getString("DOB");
            gender=bundle.getString("gender");

        }

        City=findViewById(R.id.city);
        Tehsil=findViewById(R.id.tehsil);
        complete_Address=findViewById(R.id.completeAddress);
        login_btn=findViewById(R.id.go_login);
        register_artist=findViewById(R.id.register_artist);

        register_artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city=City.getText().toString();
                Log.i("Rehan",city);
                tehsil=Tehsil.getText().toString();
                address=complete_Address.getText().toString();
                Log.i("Rehan",address);

                Bundle bundle = new Bundle();
                bundle.putString("fname", fname);
                bundle.putString("lname", lname);
                bundle.putLong("phone",phone);
                bundle.putString("DOB",DOB);
                bundle.putString("gender",gender);
                bundle.putString("city",city);
                bundle.putString("tehsil",tehsil);
                bundle.putString("address",address);
                Intent intent = new Intent(Address_Info.this, Arts_Categories.class);
                intent.putExtras(bundle);
                Log.i(TAG,"Move to categories activity");
                startActivity(intent);

            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                city=City.getText().toString();
                Log.i("Rehan",city);
                tehsil=Tehsil.getText().toString();
                address=complete_Address.getText().toString();
                Log.i("Rehan",address);

                Bundle bundle = new Bundle();
                bundle.putString("fname", fname);
                bundle.putString("lname", lname);
                bundle.putLong("phone",phone);
                bundle.putString("DOB",DOB);
                bundle.putString("gender",gender);
                bundle.putString("city",city);
                bundle.putString("tehsil",tehsil);
                bundle.putString("address",address);

                //User new_user=new User(fname,lname,phone,DOB,gender,city,tehsil,address);
                Intent intent = new Intent(Address_Info.this, Verify_signup.class);
                intent.putExtras(bundle);
                Log.i(TAG,"Move to verification activity");
                startActivity(intent);
                //reference.push().setValue(new_user);
                // Log.i("Rehan","Data Inserted");

            }
        });


    }
}