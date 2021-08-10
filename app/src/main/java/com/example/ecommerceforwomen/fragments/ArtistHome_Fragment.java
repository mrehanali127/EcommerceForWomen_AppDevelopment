package com.example.ecommerceforwomen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecommerceforwomen.Arts_Categories;
import com.example.ecommerceforwomen.Category;
import com.example.ecommerceforwomen.LoginActivity;
import com.example.ecommerceforwomen.Main_for_Artist;
import com.example.ecommerceforwomen.Product;
import com.example.ecommerceforwomen.R;
import com.example.ecommerceforwomen.Upload_product;
import com.example.ecommerceforwomen.adapters.CategoryAdapter;
import com.example.ecommerceforwomen.adapters.ProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ArtistHome_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Product> products;
    private FirebaseDatabase db;
    private DatabaseReference root;
    private ProductAdapter adapter;


    public ArtistHome_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_artist_home_, container, false);

        recyclerView = view.findViewById(R.id.ProductsRecyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));



        db = FirebaseDatabase.getInstance();
        root = db.getReference("products");



        products = new ArrayList<>();
        adapter=new ProductAdapter(getContext(),products);
        recyclerView.setAdapter(adapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                products.clear();
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
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    private void setRecyclerView() {

        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(manager);
        adapter = new ProductAdapter(getContext(), products);
        recyclerView.setAdapter(adapter);
    }


}