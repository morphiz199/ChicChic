package com.example.projectchicchic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("ALL")
public class imageUpload extends AppCompatActivity {

     private ImageView  ImageAdd;
     private TextView  textViewProgress;
     private EditText  inputImageName,InputImageNameStore,InputImagePrice,InputBranch,select_type;
     private Button  btnUpLoad,btnChangeImage;
     String rgbcolor,hexcolor;

    private static final int REQUEST_CODE_IMAGE = 101;
    Uri imageUri;
    boolean isImageAdded=false;

    DatabaseReference Dataref;
    StorageReference StorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        select_type= findViewById(R.id.select_type);
        ImageAdd= findViewById(R.id.ViewImage);
        inputImageName=findViewById(R.id.InputImageName);
        InputImageNameStore=findViewById(R.id.InputImageNameStore);
        InputImagePrice=findViewById(R.id.InputImagePrice);
        InputBranch=findViewById(R.id.InputImageOpenClose);
        btnUpLoad=findViewById(R.id.btnUpload);
        btnChangeImage=findViewById(R.id.btnChangeImg);
        //        textViewProgress=findViewById(R.id.TextViewProgress);
        //        ProgressBar=findViewById(R.id.progressBar);

//        textViewProgress.setVisibility(View.GONE);
//        ProgressBar.setVisibility(View.GONE);
        Dataref = FirebaseDatabase.getInstance().getReference().child("Nail");
        StorageRef = FirebaseStorage.getInstance().getReference().child("Nail");

        final Random random = new Random();


        ImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });
        btnUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String imageName = inputImageName.getText().toString();
               final String NameStore = InputImageNameStore.getText().toString();
               final String PriceNail = InputImagePrice.getText().toString();
               final String Branch = InputBranch.getText().toString();
                final String Type = select_type.getText().toString();
               if (isImageAdded!=false && imageName != null && NameStore != null && PriceNail != null && Branch != null && Type != null)
               {
                   uploadImage(imageName,NameStore,PriceNail,Branch,Type);
               }
            }
        });


    }


    private void uploadImage(final String imageName,final String NameStore,final String PriceNail,final String Branch,final String Type) {
//        textViewProgress.setVisibility(View.VISIBLE);
//        ProgressBar.setVisibility(View.VISIBLE);

        final String key = Dataref.push().getKey();
        StorageRef.child(key+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageRef.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("Time",imageName);
                        hashMap.put("NameStore",NameStore);
                        hashMap.put("PriceNail",PriceNail);
                        hashMap.put("Branch",Branch);
                        hashMap.put("Type",Type);
                        hashMap.put("ImageUrl",uri.toString());
                        Dataref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                                Toast.makeText(imageUpload.this,"Data Successfully Upload", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
//                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                double progress = (taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
//                ProgressBar.setProgress((int) progress);
//                textViewProgress.setText(progress +" %");
//            }
//        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_IMAGE && data!=null){
            imageUri = data.getData();
            isImageAdded=true;
            ImageAdd.setImageURI(imageUri);

        }
    }
}