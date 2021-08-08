package com.example.ecommerceforwomen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Splash_Activity extends AppCompatActivity {
    Button started;
    String TAG="Rehan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        started=findViewById(R.id.btngetstarted);

        started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect("LOGIN");
            }
        });

    }

    /* Redirect To othter activities according to need */
    public void redirect(String name){
        Intent intent=null;
        if (name=="MAIN") {
            intent = new Intent(Splash_Activity.this, MainActivity.class);
        }
        else if(name=="Personal_Info"){
            intent=new Intent(Splash_Activity.this,Personal_Info.class);
        }

        else if(name=="LOGIN"){
            intent=new Intent(Splash_Activity.this, LoginActivity.class);
        }
        else{
            Log.i(TAG,"No Path Exists");
        }
        // starting that activity
        startActivity(intent);
        finish();
    }
}