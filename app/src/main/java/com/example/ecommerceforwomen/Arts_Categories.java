package com.example.ecommerceforwomen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceforwomen.adapters.CategoryAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Arts_Categories extends AppCompatActivity {


    Button artist_verification;

    private RecyclerView recyclerView;
    private ArrayList<Category> categories;
    private FirebaseDatabase db;
    private DatabaseReference root;
    private CategoryAdapter adapter;

    /*
    CheckBox stiching,jwelery,decoration,knitting,embroidery,paperwork;
    String fname=null;
    String lname=null;
    Long phoneNo=null;
    String DOB=null;
    String gender=null;
    String city=null;
    String tehsil=null;
    String address=null;
    String cat_id;
    private ArrayList<String> skills;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Rehan","Entered");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arts__categories);

        recyclerView=findViewById(R.id.categories_recycler);
        db=FirebaseDatabase.getInstance();
        root=db.getReference("categories");
        categories=new ArrayList<>();


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter=new CategoryAdapter(this,categories);
        recyclerView.setAdapter(adapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //categories.clear();
                Log.i("Rehan","Entered2");
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Category category=dataSnapshot.getValue(Category.class);
                    categories.add(category);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Arts_Categories.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        });


        artist_verification=findViewById(R.id.verify_artist);

        /*
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fname = bundle.getString("fname");
            lname = bundle.getString("lname");
            phoneNo= bundle.getLong("phone");
            DOB=bundle.getString("DOB");
            gender=bundle.getString("gender");
            city=bundle.getString("city");
            tehsil=bundle.getString("tehsil");
            address=bundle.getString("address");
        }*/

        /*
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                cat_id=snapshot.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };*/

        artist_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*
                if(stiching.isChecked())
                    skills.add("101");
                if(jwelery.isChecked())
                    skills.add("102");
                if(paperwork.isChecked())
                    skills.add("103");
                if(embroidery.isChecked())
                    skills.add("104");
                if(knitting.isChecked())
                    skills.add("105");
                if(decoration.isChecked())
                    skills.add("106");

                Bundle bundle = new Bundle();
                bundle.putString("fname", fname);
                bundle.putString("lname", lname);
                bundle.putLong("phone",phoneNo);
                bundle.putString("DOB",DOB);
                bundle.putString("gender",gender);
                bundle.putString("city",city);
                bundle.putString("tehsil",tehsil);
                bundle.putString("address",address);
                bundle.putStringArrayList("skills",skills);
                Intent intent = new Intent(Arts_Categories.this, Verify_signup.class);
                intent.putExtras(bundle);
                Log.i("Rehan","Move to verification activity");
                startActivity(intent);*/

            }
        });


    }

}