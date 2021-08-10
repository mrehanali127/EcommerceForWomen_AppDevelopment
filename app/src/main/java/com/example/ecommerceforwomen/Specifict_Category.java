package com.example.ecommerceforwomen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ecommerceforwomen.adapters.ProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Specifict_Category extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Product> products;
    private FirebaseDatabase db;
    private DatabaseReference root;
    private ProductAdapter adapter;
    String category_name;
    Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifict__category);


        Bundle bundle = getIntent().getExtras();
        category_name=bundle.getString("cat_name");
        recyclerView =findViewById(R.id.CategoryRecycler);
        back_button=findViewById(R.id.back_button);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(Specifict_Category.this,2));

        db = FirebaseDatabase.getInstance();
        root = db.getReference("products");



        products = new ArrayList<>();
        adapter=new ProductAdapter(Specifict_Category.this,products);
        recyclerView.setAdapter(adapter);
        Query query=root.orderByChild("category").equalTo(category_name);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //products.clear();
                Log.i("Rehan", snapshot.toString());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product product=dataSnapshot.getValue(Product.class);
                    products.add(product);
                }
                //setRecyclerView();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Specifict_Category.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Specifict_Category.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}