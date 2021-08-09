package com.example.ecommerceforwomen.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.ecommerceforwomen.Category;
import com.example.ecommerceforwomen.Product;
import com.example.ecommerceforwomen.Product_detail;
import com.example.ecommerceforwomen.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>  {

    private ArrayList<Product> products;
    private Context context;

    public ProductAdapter(Context context,ArrayList<Product> products) {
        this.products = products;
        this.context = context;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        Product product=products.get(position);
       // Log.i("Rehan",product.getImageurl());
       Glide.with(context).load(product.getImageurl())
               .apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(holder.product_img);
       // Picasso.get().load("http://i.imgur.com/DvpvklR.png").dontAnimate().into(holder.product_img);
        holder.productName.setText(product.getTitle());
        holder.productPrice.setText(product.getPrice().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Product_detail.class);
                Bundle bundle=new Bundle();
                bundle.putString("p_name", product.getTitle());
                bundle.putString("p_price",product.getPrice().toString());
                bundle.putString("p_image",product.getImageurl());
                bundle.putString("p_description",product.getDescription());
                bundle.putString("artist_id",product.getArtist_id());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    // Inner class for holder
    public static class ProductHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView productName,productPrice;
        public ProductHolder(View itemView) {
            super(itemView);
            product_img=itemView.findViewById(R.id.product_image2);
            productName=itemView.findViewById(R.id.product_title2);
            productPrice=itemView.findViewById(R.id.product_price2);


        }
    }
}
