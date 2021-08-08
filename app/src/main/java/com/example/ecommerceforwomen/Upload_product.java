package com.example.ecommerceforwomen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Upload_product extends AppCompatActivity {

    Button upload_Btn,back_btn;
    ImageView imageView;
    EditText title,price,category,description;
    ProgressBar progressBar;
    String temp_phone;
    Product new_product;
    private Uri imageUri;

    DatabaseReference root= FirebaseDatabase.getInstance().getReference().child("products");
    StorageReference reference= FirebaseStorage.getInstance().getReference();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);

        title=findViewById(R.id.product_title);
        price=findViewById(R.id.product_price);
        category=findViewById(R.id.product_cat);
        description=findViewById(R.id.product_disc);
        upload_Btn=findViewById(R.id.upload_button);
        back_btn=findViewById(R.id.show_all);
        imageView=findViewById(R.id.add_image);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);
            }
        });

        upload_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if image uir not null
                if(imageUri!=null){
                    // Image uri can be get here
                    uploadToFireBase(imageUri);
                }else{
                    Toast.makeText(Upload_product.this,"Please Select Image",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && resultCode== RESULT_OK && data!= null){
            imageUri=data.getData();
            // set this uri to our image view
            imageView.setImageURI(imageUri);
        }
    }

    private void uploadToFireBase(Uri uri){
        // creat another storage reference
        StorageReference fileRef=reference.child(System.currentTimeMillis()+"."+ getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                progressBar.setVisibility(View.INVISIBLE);
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(@NonNull Uri uri) {

                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if(currentUser!=null) {
                            String phone_no = currentUser.getPhoneNumber();
                            temp_phone = phone_no.substring(3);
                        }
                        Image new_image=new Image(uri.toString());
                        // Here we have to add our other data
                        String Product_id= root.push().getKey();
                        String Artist_id= temp_phone;
                        String Category=category.getText().toString().trim();
                        String Title=title.getText().toString().trim();
                        Long Price= Long.parseLong(price.getText().toString().trim());
                        String Imageurl=uri.toString();
                        String Description=description.getText().toString();
                        new_product=new Product(Product_id,Artist_id,Category,Title,Price,Imageurl,Description);
                        root.child(Product_id).setValue(new_product);
                        Toast.makeText(Upload_product.this,"Product Uploaded Successfully",
                                Toast.LENGTH_SHORT).show();
                        /*
                        Intent intent= new Intent( Upload_product.this,Arts_Categories.class);
                        Log.i("Rehan","chcke2");
                        startActivity(intent);*/
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Upload_product.this,"Uploading Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // It return extension of image
    private String getFileExtension(Uri mUri){

        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}