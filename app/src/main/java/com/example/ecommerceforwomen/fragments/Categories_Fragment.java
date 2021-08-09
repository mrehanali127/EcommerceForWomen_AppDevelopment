package com.example.ecommerceforwomen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecommerceforwomen.Category2;
import com.example.ecommerceforwomen.Product;
import com.example.ecommerceforwomen.R;
import com.example.ecommerceforwomen.adapters.CategoryAdapter2;
import com.example.ecommerceforwomen.adapters.ProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Categories_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Category2> categories2;
    private FirebaseDatabase db;
    private DatabaseReference root;
    private CategoryAdapter2 adapter;


    public Categories_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_categories_, container, false);

        recyclerView = view.findViewById(R.id.ProductsRecyclerview3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        db = FirebaseDatabase.getInstance();
        root = db.getReference("categories2");



        categories2 = new ArrayList<>();
        adapter=new CategoryAdapter2(getContext(),categories2);
        recyclerView.setAdapter(adapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //products.clear();
                Log.i("Rehan", snapshot.toString());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Category2 category=dataSnapshot.getValue(Category2.class);
                    //Log.i("Rehan",category.getCategoryName());
                    categories2.add(category);
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
}