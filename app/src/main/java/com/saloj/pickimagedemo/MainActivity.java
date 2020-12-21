package com.saloj.pickimagedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PICTURE = 1;
    private static final int GALLERY_PICTURE = 2;

    private static final int AADHAAR_PDF = 3;
    private static final int CAMERA_PICTURE_AADHAAR = 4;

    private static final int CAMERA_PIC_WORK_SAMPLE1 = 5;
    private static final int GALLERY_PIC_WORK_SAMPLE1 = 6;

    private static final int CAMERA_PIC_WORK_SAMPLE2 = 7;
    private static final int GALLERY_PIC_WORK_SAMPLE2 = 8;


    private static final int CAMERA_PIC_WORK_SAMPLE3 = 9;
    private static final int GALLERY_PIC_WORK_SAMPLE3 = 10;

    private static final int CAMERA_PIC_WORK_SAMPLE4 = 11;
    private static final int GALLERY_PIC_WORK_SAMPLE4 = 12;

    List<File> mediaFilesList;

    private ImageView latest_img, aadhar_img, work1_img, work2_img, work3_img, work4_img;
    private Button next_btn;
    private String latestPhotoPath, aadhaarPhotoPath, work1Path, work2Path, work3Path, work4Path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latest_img = findViewById(R.id.img_latest);
        aadhar_img = findViewById(R.id.img_aadhar);
        work1_img = findViewById(R.id.img_work1);
        work2_img = findViewById(R.id.img_work2);
        work3_img = findViewById(R.id.img_work3);
        work4_img = findViewById(R.id.img_work4);
        next_btn = findViewById(R.id.btn_next);

        List<File> mediaFilesList = new ArrayList<File>();

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, MultiImagePick.class));
            }
        });

        latest_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choosePictureAction();
            }
        });
        aadhar_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseAadhaarAction();
            }
        });

        work1_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseWorkSampleAction1();
            }
        });

        work2_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseWorkSampleAction2();
            }

        });

        work3_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseWorkSampleAction3();
            }
        });

        work4_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseWorkSampleAction4();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {

                case 1:
                    if (requestCode == CAMERA_PICTURE && resultCode == RESULT_OK) {

                        Bitmap bitmap = BitmapFactory.decodeFile(latestPhotoPath);
                        latest_img.setImageBitmap(bitmap);
                    }
                    break;

                case 2:
                    if (requestCode == GALLERY_PICTURE && resultCode == RESULT_OK && data != null) {

                        Uri uri = null;

                        try {
                            uri = data.getData();

                            Log.d("pathG: ", uri.toString());

                            InputStream inputStream = getContentResolver().openInputStream(uri);

                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        //    Log.d("pathG: ", bitmap.toString());
                            latest_img.setImageBitmap(bitmap);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }

                    }
                    break;

                case 3:
                    if (requestCode == AADHAAR_PDF && resultCode == RESULT_OK && data != null) {

                        Uri uri = null;
                        if (data != null) {

                            uri = data.getData();

                            Log.d("aadhaar", "aadhaar :" + uri);

                            aadhar_img.setImageResource(R.drawable.pdf);
                        }

                    }
                    break;

                case 4:
                    if (requestCode == CAMERA_PICTURE_AADHAAR && resultCode == RESULT_OK) {

                        Bitmap bitmap = BitmapFactory.decodeFile(aadhaarPhotoPath);
                        aadhar_img.setImageBitmap(bitmap);
                    }
                    break;

                case 5:
                    if (requestCode == CAMERA_PIC_WORK_SAMPLE1 && resultCode == RESULT_OK) {

                        Bitmap bitmap1 = BitmapFactory.decodeFile(work1Path);
                        work1_img.setImageBitmap(bitmap1);
                    }
                    break;

                case 6:
                    if (requestCode == GALLERY_PIC_WORK_SAMPLE1 && resultCode == RESULT_OK && data != null) {

                        Uri uri1 = null;

                        try {
                            uri1 = data.getData();

                            Log.d("pathGW1: ", uri1.toString());

                            // Get the file instance
                        //    File file = new File(uri1.toString());
                        //    mediaFilesList.add(file);

                            InputStream inputStream1 = getContentResolver().openInputStream(uri1);

                            Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream1);

                        //    Log.d("pathGW: ", bitmap1.toString());
                            work1_img.setImageBitmap(bitmap1);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }

                    }
                    break;

                case 7:

                    if (requestCode == CAMERA_PIC_WORK_SAMPLE2 && resultCode == RESULT_OK) {

                        Bitmap bitmap2 = BitmapFactory.decodeFile(work2Path);
                        work2_img.setImageBitmap(bitmap2);
                    }

                    break;

                case 8:
                    if (requestCode == GALLERY_PIC_WORK_SAMPLE2 && resultCode == RESULT_OK && data != null) {

                        Uri uri2 = null;

                        try {
                            uri2 = data.getData();

                            Log.d("pathGW2: ", uri2.toString());


                            InputStream inputStream2 = getContentResolver().openInputStream(uri2);

                            Bitmap bitmap2 = BitmapFactory.decodeStream(inputStream2);

                        //    Log.d("pathGW2: ", bitmap2.toString());
                            work2_img.setImageBitmap(bitmap2);

                            // Get the file instance
                         //   File file = new File(uri2.toString());
                           // mediaFilesList.add(file);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }

                    }

                    break;

                case 9:

                    if (requestCode == CAMERA_PIC_WORK_SAMPLE3 && resultCode == RESULT_OK) {

                        Bitmap bitmap3 = BitmapFactory.decodeFile(work3Path);
                        work3_img.setImageBitmap(bitmap3);
                    }

                    break;

                case 10:
                    if (requestCode == GALLERY_PIC_WORK_SAMPLE3 && resultCode == RESULT_OK && data != null) {

                        Uri uri3 = null;

                        try {
                            uri3 = data.getData();

                            Log.d("pathGW3: ", uri3.toString());


                            InputStream inputStream3 = getContentResolver().openInputStream(uri3);

                            Bitmap bitmap3 = BitmapFactory.decodeStream(inputStream3);

                         //   Log.d("pathGW3: ", bitmap3.toString());
                            work3_img.setImageBitmap(bitmap3);

                            // Get the file instance
                          //  File file = new File(uri3.toString());
                           // mediaFilesList.add(file);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }

                    }

                    break;

                case 11:
                    if (requestCode == CAMERA_PIC_WORK_SAMPLE4 && resultCode == RESULT_OK) {

                        Bitmap bitmap4 = BitmapFactory.decodeFile(work4Path);
                        work4_img.setImageBitmap(bitmap4);
                    }

                    break;

                case 12:

                    if (requestCode == GALLERY_PIC_WORK_SAMPLE4 && resultCode == RESULT_OK && data != null) {

                        Uri uri4 = null;

                        try {
                            uri4 = data.getData();

                            Log.d("pathGW4: ", uri4.toString());

                            InputStream inputStream4 = getContentResolver().openInputStream(uri4);

                            Bitmap bitmap4 = BitmapFactory.decodeStream(inputStream4);

                          //  Log.d("pathGW4: ", bitmap4.toString());
                            work4_img.setImageBitmap(bitmap4);

                            // Get the file instance
                         //   File file = new File(uri4.toString());
                          //  mediaFilesList.add(file);


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }

                    }

                    break;

            }

        }

    }


    public void choosePictureAction() {

        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (items[which].equals("Camera")) {

                    String filename = "photo";

                    File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    try {
                        File imageFile = File.createTempFile(filename, ".jpg", storageDirectory);
                   /*     File latestImgFile = File.createTempFile(filename,".jpg",storageDirectory);
                        File aadhaarImgFile = File.createTempFile(filename,".jpg",storageDirectory);
                        File work1ImgFile = File.createTempFile(filename,".jpg",storageDirectory);
                        File work2ImgFile = File.createTempFile(filename,".jpg",storageDirectory);
                        File work3ImgFile = File.createTempFile(filename,".jpg",storageDirectory);
                        File work4ImgFile = File.createTempFile(filename,".jpg",storageDirectory);
*/
                        latestPhotoPath = imageFile.getAbsolutePath();

          /*              latestPhotoPath = latestImgFile.getAbsolutePath();
                        aadhaarPhotoPath = aadhaarImgFile.getAbsolutePath();
                        work1Path = work1ImgFile.getAbsolutePath();
                        work2Path = work2ImgFile.getAbsolutePath();
                        work3Path = work3ImgFile.getAbsolutePath();
                        work4Path = work4ImgFile.getAbsolutePath();*/

                        Log.d("pathC: ", latestPhotoPath);

                /*        Log.d("pathCL: ",latestPhotoPath);
                        Log.d("pathCA: ",aadhaarPhotoPath);
                        Log.d("pathCW1: ",work1Path);
                        Log.d("pathCW2: ",work2Path);
                        Log.d("pathCW3: ",work3Path);
                        Log.d("pathCW4: ",work4Path);*/

                        Uri imageuri = FileProvider.getUriForFile(MainActivity.this, "com.saloj.pickimagedemo.fileprovider", imageFile);

/*
                        Uri latestImguri1 =  FileProvider.getUriForFile(MainActivity.this,"com.saloj.pickimagedemo.fileprovider",latestImgFile);
                        Uri aadhaarImguri2 =  FileProvider.getUriForFile(MainActivity.this,"com.saloj.pickimagedemo.fileprovider",aadhaarImgFile);
                        Uri work1Imguri3 =  FileProvider.getUriForFile(MainActivity.this,"com.saloj.pickimagedemo.fileprovider",work1ImgFile);
                        Uri work2Imguri4 =  FileProvider.getUriForFile(MainActivity.this,"com.saloj.pickimagedemo.fileprovider",work2ImgFile);
                        Uri work3Imguri5 =  FileProvider.getUriForFile(MainActivity.this,"com.saloj.pickimagedemo.fileprovider",work3ImgFile);
                        Uri work4Imguri6 =  FileProvider.getUriForFile(MainActivity.this,"com.saloj.pickimagedemo.fileprovider",work4ImgFile);

                        Log.d("pathCL: ",latestImguri1.toString());
                        Log.d("pathCA: ",aadhaarImguri2.toString());
                        Log.d("pathCW1: ",work1Imguri3.toString());
                        Log.d("pathCW2: ",work2Imguri4.toString());
                        Log.d("pathCW3: ",work3Imguri5.toString());
                        Log.d("pathCW4: ",work4Imguri6.toString());
*/

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                        startActivityForResult(intent, CAMERA_PICTURE);


                     /*   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,latestImguri1);
                        startActivityForResult(intent,CAMERA_PICTURE);

                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,aadhaarImguri2);
                        startActivityForResult(intent2,CAMERA_PICTURE);

                        Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,work1Imguri3);
                        startActivityForResult(intent3,CAMERA_PICTURE);

                        Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,work2Imguri4);
                        startActivityForResult(intent4,CAMERA_PICTURE);

                        Intent intent5 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,work3Imguri5);
                        startActivityForResult(intent5,CAMERA_PICTURE);

                        Intent intent6 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,work4Imguri6);
                        startActivityForResult(intent6,CAMERA_PICTURE);
*/
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (items[which].equals("Gallery")) {

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Pick Image From Gallery"), GALLERY_PICTURE);

                } else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }

            }
        });

        builder.show();

    }


    public void chooseAadhaarAction() {

        final CharSequence[] items = {"Camera", "PDF", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Aadhaar!");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (items[which].equals("Camera")) {

                    String filename = "photo";

                    File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                    try {

                        File imageFile = File.createTempFile(filename, ".jpg", storageDirectory);

                        aadhaarPhotoPath = imageFile.getAbsolutePath();

                        Log.d("pathCA: ", aadhaarPhotoPath);

                        Uri imageuri = FileProvider.getUriForFile(MainActivity.this, "com.saloj.pickimagedemo.fileprovider", imageFile);

                        Log.d("pathCA: ", imageuri.toString());

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                        startActivityForResult(intent, CAMERA_PICTURE_AADHAAR);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (items[which].equals("PDF")) {

                    Intent intent = new Intent();
                    intent.setType("application/pdf");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select PDF"), AADHAAR_PDF);

                } else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }

            }
        });

        builder.show();
    }

    public void chooseWorkSampleAction1() {

        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (items[which].equals("Camera")) {

                    String filename = "photo";

                    File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                    try {

                        File work1ImgFile = File.createTempFile(filename,".jpg",storageDirectory);

                        work1Path = work1ImgFile.getAbsolutePath();

                        Log.d("pathCW1: ", work1Path);

                        // Get the file instance
                     //   File file = new File(work1Path);
                     //   mediaFilesList.add(file);

                        Uri imageUri1 = FileProvider.getUriForFile(MainActivity.this, "com.saloj.pickimagedemo.fileprovider", work1ImgFile);

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri1);
                        startActivityForResult(intent, CAMERA_PIC_WORK_SAMPLE1);



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (items[which].equals("Gallery")) {

                    Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent1.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent1, "Pick Image From Gallery"), GALLERY_PIC_WORK_SAMPLE1);

                } else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }

            }
        });

        builder.show();

    }
    public void chooseWorkSampleAction2() {

        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (items[which].equals("Camera")) {

                    String filename = "photo";

                    File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                    try {

                        File work2ImgFile = File.createTempFile(filename,".jpg",storageDirectory);

                        work2Path = work2ImgFile.getAbsolutePath();

                        Log.d("pathCW2: ", work2Path);

                        // Get the file instance
                     //   File file = new File(work2Path);
                     //   mediaFilesList.add(file);

                        Uri work2Imguri4 =  FileProvider.getUriForFile(MainActivity.this,"com.saloj.pickimagedemo.fileprovider",work2ImgFile);

                        Log.d("pathCW2: ",work2Imguri4.toString());

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, work2Imguri4);
                        startActivityForResult(intent, CAMERA_PIC_WORK_SAMPLE2);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (items[which].equals("Gallery")) {

                    Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent2.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent2, "Pick Image From Gallery"), GALLERY_PIC_WORK_SAMPLE2);

                } else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }

            }
        });

        builder.show();

    }
    public void chooseWorkSampleAction3() {

        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (items[which].equals("Camera")) {
                    String filename = "photo";
                    File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    try {

                        File work3ImgFile = File.createTempFile(filename,".jpg",storageDirectory);
                        work3Path = work3ImgFile.getAbsolutePath();

                        Log.d("pathCW3: ", work3Path);

                        // Get the file instance
                      //  File file = new File(work3Path);
                       // mediaFilesList.add(file);


                        Uri imageUri3 = FileProvider.getUriForFile(MainActivity.this, "com.saloj.pickimagedemo.fileprovider", work3ImgFile);

                        Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent3.putExtra(MediaStore.EXTRA_OUTPUT, imageUri3);
                        startActivityForResult(intent3, CAMERA_PIC_WORK_SAMPLE3);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (items[which].equals("Gallery")) {

                    Intent intent3 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent3.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent3, "Pick Image From Gallery"), GALLERY_PIC_WORK_SAMPLE3);

                } else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }

            }
        });

        builder.show();

    }
    public void chooseWorkSampleAction4() {

        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (items[which].equals("Camera")) {

                    String filename = "photo";

                    File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    try {

                        File work4ImgFile = File.createTempFile(filename,".jpg",storageDirectory);

                        work4Path = work4ImgFile.getAbsolutePath();

                        Log.d("pathCW4: ", work4Path);

                        // Get the file instance
                       // File file = new File(work4Path);
                       // mediaFilesList.add(file);


                        Uri imageUri4 = FileProvider.getUriForFile(MainActivity.this, "com.saloj.pickimagedemo.fileprovider",work4ImgFile );

                        Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent4.putExtra(MediaStore.EXTRA_OUTPUT, imageUri4);
                        startActivityForResult(intent4, CAMERA_PIC_WORK_SAMPLE4);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if (items[which].equals("Gallery")) {

                    Intent intent4 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent4.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent4, "Pick Image From Gallery"), GALLERY_PIC_WORK_SAMPLE4);

                }

                else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }

            }
        });

        builder.show();

    }



}