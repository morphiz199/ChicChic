package com.example.projectchicchic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UploadActivity extends AppCompatActivity {

     ImageView ImageAdd;
     EditText inputImageName;
     Button btnUpLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        ImageAdd=findViewById(R.id.ViewImage1);
        inputImageName=findViewById(R.id.InputImageName1);
        btnUpLoad=findViewById(R.id.btnUpload1);
    }
}