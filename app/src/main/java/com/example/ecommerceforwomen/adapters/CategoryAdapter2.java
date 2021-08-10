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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommerceforwomen.Category;
import com.example.ecommerceforwomen.Category2;
import com.example.ecommerceforwomen.Product_detail;
import com.example.ecommerceforwomen.R;
import com.example.ecommerceforwomen.Specifict_Category;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class CategoryAdapter2 extends RecyclerView.Adapter<CategoryAdapter2.CategoryHolder2>{

    private ArrayList<Category2> categories;
    private Context context;

    public CategoryAdapter2(Context context,ArrayList<Category2> categories) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public CategoryHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.category2,parent,false);
        return new CategoryHolder2(v);
    }

    @Override
    public void onBindViewHolder(CategoryHolder2 holder, int position) {

        Category2 cat=categories.get(position);
        holder.categoryName.setText(cat.getName());
        Glide.with(context).load(cat.getImageUrl())
                .circleCrop().into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Specifict_Category.class);
                Bundle bundle=new Bundle();
                bundle.putString("cat_name",cat.getName());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    // Inner class for holder
    public static class CategoryHolder2 extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView imageView;
        public CategoryHolder2(View itemView) {
            super(itemView);
            categoryName=itemView.findViewById(R.id.category_name3);
            imageView=itemView.findViewById(R.id.category_img2);


        }
    }
}