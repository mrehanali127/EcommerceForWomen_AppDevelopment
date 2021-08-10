package com.example.ecommerceforwomen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ecommerceforwomen.fragments.Account_Fragment;
import com.example.ecommerceforwomen.fragments.ArtistHome_Fragment;
import com.example.ecommerceforwomen.fragments.Categories_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameContainer1,new ArtistHome_Fragment());
        fragmentTransaction.commit();


        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                // Temprory Fragment
                Fragment temp=null;

                switch (item.getItemId()){
                    case R.id.menu_home : temp = new ArtistHome_Fragment();
                    break;
                    case R.id.menu_categories : temp = new Categories_Fragment();
                    break;
                    case R.id.menu_account : temp = new Account_Fragment();
                    break;
                }
                // Replace temprory Fragement with acutal fragment
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.FrameContainer1,temp);
                fragmentTransaction.commit();

                return true;
            }
        });

    }


}