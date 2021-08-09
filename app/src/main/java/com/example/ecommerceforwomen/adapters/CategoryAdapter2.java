package com.example.ecommerceforwomen.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommerceforwomen.Category;
import com.example.ecommerceforwomen.Category2;
import com.example.ecommerceforwomen.R;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class CategoryAdapter2 extends RecyclerView.Adapter<CategoryAdapter2.CategoryHolder2> {
    ArrayList<Category2> categories2;
    Context context;

    public CategoryAdapter2(Context context,ArrayList<Category2> categories2) {

        this.context = context;
        this.categories2 = categories2;
    }


    @Override
    public CategoryHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.category2,parent,false);
        return new CategoryHolder2(v);
    }


    @Override
    public void onBindViewHolder(CategoryHolder2 holder, int position) {

        Category2 cat=categories2.get(position);
        //Log.i("Rehan",cat.getCategoryName()+"");
        Glide.with(context).load(cat.getImageUrl())
                .circleCrop().into(holder.imageView);
        //Log.i("Rehan",cat.getCategoryName());
        holder.cat_name.setText(cat.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
               // Code
               /*
                Intent intent= new Intent(v.getContext(), Upload_product.class);
                Log.i("Rehan","chcke2");
                v.getContext().startActivity(intent);*/
            }
        });
    }


    @Override
    public int getItemCount() {
        return categories2.size();
    }


    // Inner class for holder
    public static class CategoryHolder2 extends RecyclerView.ViewHolder{
        TextView cat_name;
        ImageView imageView;
        public CategoryHolder2(View itemView) {
            super(itemView);
            cat_name=itemView.findViewById(R.id.category_name3);
            imageView=itemView.findViewById(R.id.category_img2);


        }
    }
}


