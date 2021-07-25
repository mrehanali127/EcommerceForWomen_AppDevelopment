package com.example.ecommerceforwomen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class Personal_Info extends AppCompatActivity {

    EditText fname,lname,phone,dob;
    CheckBox male,female;
    Button next_button ;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__info);

        fname=findViewById(R.id.Fname);
        lname=findViewById(R.id.Lname);
        phone=findViewById(R.id.Phone);
        dob=findViewById(R.id.DOB);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        next_button=findViewById(R.id.next_pinfo);
        login=findViewById(R.id.btnlogin);


        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = null;
                    String Fname=fname.getText().toString().trim();
                    String Lname=lname.getText().toString().trim();
                    Long Phone= Long.parseLong(phone.getText().toString().trim());
                    String DOB=dob.getText().toString().trim();

                    if(male.isChecked())
                        gender="male";
                    if(female.isChecked())
                       gender="female";

                Bundle bundle = new Bundle();
                bundle.putString("fname", Fname);
                bundle.putString("lname", Lname);
                bundle.putLong("phone",Phone);
                bundle.putString("DOB",DOB);
                bundle.putString("gender",gender);
                Intent intent = new Intent(Personal_Info.this, Address_Info.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Personal_Info.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}