package com.example.farmerapmcportal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Admin_addProductsActivity extends AppCompatActivity {

    EditText farmer_name, address, product_type, product_name, price, avail;
    DBHelper DB;
    Button BSelectImage, Btn_add_product, Logout;
    ImageView ProductImage;
    String img_path;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_addproducts);

        //Store product data into database
        farmer_name = (EditText) findViewById(R.id.farmer_name);
        address = (EditText) findViewById(R.id.address);
        product_type = (EditText) findViewById(R.id.product_type);
        product_name = (EditText) findViewById(R.id.product_name);
        price = (EditText) findViewById(R.id.price);
        avail = (EditText) findViewById(R.id.availability);
        // register the UI widgets with their appropriate IDs
        BSelectImage = findViewById(R.id.upload_product_img);
        ProductImage = findViewById(R.id.product_image);
        DB = new DBHelper(this);

        Logout = findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_addProductsActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        Btn_add_product = findViewById(R.id.add_product);
        Btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String farmer = farmer_name.getText().toString();
                String add = address.getText().toString();
                String p_type = product_type.getText().toString();
                String p_name = product_name.getText().toString();
                String value = price.getText().toString();
                String available = avail.getText().toString();
                byte[] bytesPP = convertImageViewToByteArray(ProductImage);

                if (farmer.equals("") || add.equals("") || p_type.equals("") || p_name.equals("") || value.equals("")|| available.equals(""))
                    Toast.makeText(Admin_addProductsActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                else {
                    Boolean insert = DB.insertProductData(farmer, add, p_type, p_name, value, bytesPP, available);
                    if (insert == true) {
                        Toast.makeText(Admin_addProductsActivity.this, "Products added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Admin_addProductsActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Admin_addProductsActivity.this, "Not added", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        // handle the Choose Image button to trigger
        // the image chooser function
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });


    }

    private byte[] convertImageViewToByteArray(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    ProductImage.setImageURI(selectedImageUri);
                }
                img_path = selectedImageUri.toString();
            }
        }
    }

    public String getPath(Uri imageUri){
        if (imageUri==null) return null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(imageUri,projection,null,null,null);
        if (cursor!=null){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return imageUri.getPath();
    }

}



