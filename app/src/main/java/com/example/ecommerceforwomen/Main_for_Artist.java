package com.example.ecommerceforwomen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ecommerceforwomen.fragments.Account_Fragment;
import com.example.ecommerceforwomen.fragments.ArtistAccount_Fragment;
import com.example.ecommerceforwomen.fragments.ArtistAdd_Fragment;
import com.example.ecommerceforwomen.fragments.ArtistHome_Fragment;
import com.example.ecommerceforwomen.fragments.Favourite_Fragment;
import com.example.ecommerceforwomen.fragments.Home_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main_for_Artist extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameContainer2,new Home_Fragment());
        fragmentTransaction.commit();


        bottomNavigationView=findViewById(R.id.bottomNavigation2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                // Temprory Fragment
                Fragment temp=null;

                switch (item.getItemId()){
                    case R.id.menu_home2 : temp = new ArtistHome_Fragment();
                        break;
                    case R.id.menu_add2 : temp = new ArtistAdd_Fragment();
                        break;
                    case R.id.menu_account2 : temp = new ArtistAccount_Fragment();
                        break;
                }
                // Replace temprory Fragement with acutal fragment
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.FrameContainer2,temp);
                fragmentTransaction.commit();

                return true;
            }
        });
    }
}