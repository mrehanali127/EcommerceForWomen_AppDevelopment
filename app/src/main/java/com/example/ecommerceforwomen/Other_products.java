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

public class Other_products extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Product> products;
    private FirebaseDatabase db;
    private DatabaseReference root;
    private ProductAdapter adapter;
    String artist_id;
    Button back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_products);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            artist_id = bundle.getString("artist_id");
            Log.i("Rehan","Artist_id"+artist_id);
        }

        recyclerView =findViewById(R.id.ProductsRecyclerview4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(Other_products.this,2));

        db = FirebaseDatabase.getInstance();
        root = db.getReference("products");



        products = new ArrayList<>();
        adapter=new ProductAdapter(Other_products.this,products);
        recyclerView.setAdapter(adapter);
        Query query=root.orderByChild("artist_id").equalTo(artist_id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.i("Rehan", snapshot.toString());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product product=dataSnapshot.getValue(Product.class);
                    products.add(product);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Other_products.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}