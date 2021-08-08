package com.example.ecommerceforwomen.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceforwomen.Arts_Categories;
import com.example.ecommerceforwomen.Category;
import com.example.ecommerceforwomen.R;
import com.example.ecommerceforwomen.Upload_product;
import com.example.ecommerceforwomen.Verify_signup;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private ArrayList<Category> categories;
    private Context context;
    Category sel_cat;
    private int rowIndex=-1;
    private ArrayList<String> skills;

    public CategoryAdapter(Context context,ArrayList<Category> categories,ArrayList<String> skills) {

        this.context = context;
        this.categories = categories;
        this.skills=skills;
    }


    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.category,parent,false);
        return new CategoryHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {

        Category cat=categories.get(position);
        Log.i("Rehan",cat.getCategoryName()+"");
        holder.categoryName.setText(cat.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                rowIndex=position;
                Category sel_cat=categories.get(position);
                String skill= sel_cat.getCategoryName();
                skills.add(skill);
                Log.i("Rehan", String.valueOf(skills));
               notifyDataSetChanged();
               Log.i("Rehan","check1");
               /*
                Intent intent= new Intent(v.getContext(), Upload_product.class);
                Log.i("Rehan","chcke2");
                v.getContext().startActivity(intent);*/
            }
        });
            if (rowIndex == position) {
                holder.itemView.setBackgroundColor(Color.parseColor("#1a237e"));
                holder.categoryName.setTextColor(Color.parseColor("#ffffff"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.categoryName.setTextColor(Color.parseColor("#12005e"));
            }

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

