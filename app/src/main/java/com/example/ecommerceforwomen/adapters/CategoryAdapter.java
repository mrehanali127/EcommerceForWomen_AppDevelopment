package com.example.ecommerceforwomen.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceforwomen.Category;
import com.example.ecommerceforwomen.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private ArrayList<Category> categories;
    private Context context;

    public CategoryAdapter(Context context,ArrayList<Category> categories) {

        this.context = context;
        this.categories = categories;
    }


    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.category,parent,false);
        return new CategoryHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {

        Category cat=categories.get(position);
        holder.categoryName.setText(cat.getCategoryName());
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }


    // Inner class for holder
    public static class CategoryHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        public CategoryHolder(View itemView) {
            super(itemView);
            categoryName=itemView.findViewById(R.id.category_name);


        }
    }
}

