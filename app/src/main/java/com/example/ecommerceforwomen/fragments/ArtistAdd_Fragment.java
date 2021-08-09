package com.example.ecommerceforwomen.fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ecommerceforwomen.Image;
import com.example.ecommerceforwomen.LoginActivity;
import com.example.ecommerceforwomen.Product;
import com.example.ecommerceforwomen.R;
import com.example.ecommerceforwomen.Upload_product;
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

import static android.app.Activity.RESULT_OK;


public class ArtistAdd_Fragment extends Fragment {

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


    public ArtistAdd_Fragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_artist_add_, container, false);

        title=view.findViewById(R.id.product_title);
        price=view.findViewById(R.id.product_price);
        category=view.findViewById(R.id.product_cat);
        description=view.findViewById(R.id.product_disc);
        upload_Btn=view.findViewById(R.id.upload_button);
        back_btn=view.findViewById(R.id.show_all);
        imageView=view.findViewById(R.id.add_image);
        progressBar=view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();
        //Bundle bundle=new Bundle();
        //Intent intent=new Intent(getContext(), LoginActivity.class);
        //startActivity(intent);

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
                    Toast.makeText(getContext(),"Please Select Image",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                        String Imageurl= new_image.getImageUrl();
                        String Description=description.getText().toString();
                        new_product=new Product(Product_id,Artist_id,Category,Title,Price,Imageurl,Description);
                        root.child(Product_id).setValue(new_product);
                        Toast.makeText(getContext(),"Product Uploaded Successfully",
                                Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(),"Uploading Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // It return extension of image
    private String getFileExtension(Uri mUri){

        ContentResolver cr=getActivity().getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }




}