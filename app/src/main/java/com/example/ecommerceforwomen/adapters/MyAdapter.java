package com.example.ecommerceforwomen.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceforwomen.Category;
import com.example.ecommerceforwomen.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdapter extends FirebaseRecyclerAdapter<Category,MyAdapter.myHolder> {


    public MyAdapter(FirebaseRecyclerOptions<Category> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(myHolder holder, int position,Category model) {

    holder.categoryName.setText(model.getCategoryName());

    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category,parent,false);
        return new myHolder(view);
    }

    class myHolder extends RecyclerView.ViewHolder
    {
        TextView categoryName;
        public myHolder(View itemView) {
            super(itemView);
            categoryName=itemView.findViewById(R.id.category_name);

        }
    }
}
