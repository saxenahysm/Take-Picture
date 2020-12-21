package com.saloj.pickimagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MultiImagePick extends AppCompatActivity {
    private static final int PERMISSION_TO_SELECT_IMAGE_FROM_GALLERY = 100;
    private static final int PICK_IMAGE_MULTIPLE = 200;

    String imageEncoded,imageEncoded2;
    List<String> imagesEncodedList;


    private ImageView latest_img,aadhar_img,work1_img,work2_img,work3_img,work4_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_image_pick);

        work1_img =  findViewById(R.id.img_work1);
        work2_img = findViewById(R.id.img_work2);
        work3_img =  findViewById(R.id.img_work3);
        work4_img =  findViewById(R.id.img_work4);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_TO_SELECT_IMAGE_FROM_GALLERY);
        } else {

            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }

        work1_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickGall();
            }
        });
        work2_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickGall();

            }
        });
        work3_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickGall();

            }
        });
        work4_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickGall();

            }
        });


    }


    private void PickGall(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
                // Get the Image from data

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();

                if (data.getData() != null) {         //on Single image selected

                    Uri mImageUri = data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();

                } else {                              //on multiple image selected
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();

                        for (int i = 0; i < 4; i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            Uri uri2 = item.getUri();
                            Uri uri3 = item.getUri();
                            Uri uri4= item.getUri();

                            work1_img.setImageURI(uri);
                            work2_img.setImageURI(uri2);
                            work3_img.setImageURI(uri3);
                            work4_img.setImageURI(uri4);

                            mArrayUri.add(0,uri);

                            mArrayUri.add(1,uri);

                            mArrayUri.add(2,uri);

                            mArrayUri.add(3,uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                        }
                      //  Log.v("MultipleImage", "Selected Images" + mArrayUri.size());
                        Log.d("MultipleImage", "Selected Images" + mArrayUri.size());
                        Log.d("MultipleImage", "Selected Images" + mArrayUri.toString());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}