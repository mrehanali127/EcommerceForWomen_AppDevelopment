package com.example.ecommerceforwomen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Product_detail extends AppCompatActivity {

    ImageView product_img;
    TextView p_name, p_price, p_description, artist_name, artist_categories;
    Button other_products;
    BottomNavigationView bottomNavigationView;
    String product_name, product_price, product_desc, artist_id, prod_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            product_name = bundle.getString("p_name");
            product_price = bundle.getString("p_price");
            product_desc = bundle.getString("p_description");
            artist_id = bundle.getString("artist_id");
            prod_img = bundle.getString("p_image");
        }


        product_img = findViewById(R.id.product_detailes_image);
        p_name = findViewById(R.id.product_details_title);
        p_price = findViewById(R.id.product_details_price);
        p_description = findViewById(R.id.product_detail_description);
        artist_name = findViewById(R.id.artist_name_card);
        artist_categories = findViewById(R.id.artist_categories_card);
        other_products = findViewById(R.id.artist_btn_card);

        Glide.with(this).load(prod_img)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(product_img);
        p_name.setText(product_name);
        p_price.setText("RS." + product_price);
        p_description.setText(product_desc);
        artist_name.setText("0"+artist_id);
        //artist_categories.setText(product.getCategory());


        bottomNavigationView = findViewById(R.id.bottomNavigation3);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_whatsapp:
                        String url = "https://api.whatsapp.com/send?phone=0"+artist_id;
                        Intent whatsapp_intent= new Intent(Intent.ACTION_VIEW);
                        whatsapp_intent.setData(Uri.parse(url));
                        startActivity(whatsapp_intent);
                        break;
                    case R.id.menu_Message:
                        Intent msg_intent = new Intent(Intent.ACTION_VIEW);
                        msg_intent.setData(Uri.parse("sms:0"+artist_id));
                        startActivity(msg_intent);
                        break;
                    case R.id.menu_call:
                        Intent call_intent = new Intent(Intent.ACTION_DIAL);
                        call_intent.setData(Uri.parse("tel:0" + artist_id));
                        startActivity(call_intent);
                        break;
                }
                return true;
            }

        });
    }
}