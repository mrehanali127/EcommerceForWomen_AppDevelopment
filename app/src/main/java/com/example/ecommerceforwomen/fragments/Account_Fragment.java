package com.example.ecommerceforwomen.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerceforwomen.LoginActivity;
import com.example.ecommerceforwomen.MainActivity;
import com.example.ecommerceforwomen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Account_Fragment extends Fragment {


    TextView logout_btn,username,phone;
    Long number;
    private FirebaseAuth mAuth;


    public Account_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_account_, container, false);
        logout_btn=view.findViewById(R.id.logout_button);
        username=view.findViewById(R.id.user_name);
        phone=view.findViewById(R.id.user_phone);
        mAuth= FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize Firebase Auth
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            String phone_no=currentUser.getPhoneNumber();
            Log.i("REHAN",phone_no);
            phone.setText(phone_no);
        }


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("REHAN","Button clicked");
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null){
                    Log.i("REHAN","Entered");
                    mAuth.signOut();
                    Intent intent=new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

}