package com.example.ecommerceforwomen;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import java.util.stream.Collectors;

public class Arts_Categories extends AppCompatActivity {


    Button artist_verification;

    private RecyclerView recyclerView;
    private ArrayList<Category> categories;
    private FirebaseDatabase db;
    private DatabaseReference root;
    private CategoryAdapter adapter;
    String fname,lname,DOB,gender,city,tehsil,address;
    Long phoneNo;
    ArrayList<String> skills;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Rehan", "Entered");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arts__categories);

        recyclerView = findViewById(R.id.categories_recycler);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("categories");
        categories = new ArrayList<>();
        skills = new ArrayList<>();


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                categories.clear();
                Log.i("Rehan", snapshot.toString());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Log.i("TAG-Data", "onDataChange: -->  " + ds.toString());
                        String temp = (String) ds.getValue();
                        Category category = new Category();
                        category.categoryName = temp;
                        categories.add(category);
                    }
                }
                setRecyclerView();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Arts_Categories.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        artist_verification = findViewById(R.id.verify_artist);
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
        }

        artist_verification.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("fname", fname);
                bundle.putString("lname", lname);
                bundle.putLong("phone",phoneNo);
                bundle.putString("DOB",DOB);
                bundle.putString("gender",gender);
                bundle.putString("city",city);
                bundle.putString("tehsil",tehsil);
                bundle.putString("address",address);
                skills = (ArrayList<String>) skills.stream()
                        .distinct()
                        .collect(Collectors.toList());
                bundle.putStringArrayList("skills",skills);
                Intent intent = new Intent(Arts_Categories.this, Verify_signup.class);
                intent.putExtras(bundle);
                Log.i("Rehan","Move to verification activity");
                startActivity(intent);

            }
        });


    }

    private void setRecyclerView() {

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new CategoryAdapter(this, categories,skills);
        recyclerView.setAdapter(adapter);
    }

}