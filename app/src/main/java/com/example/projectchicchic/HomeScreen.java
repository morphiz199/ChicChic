package com.example.projectchicchic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {

    ImageView imageView30,imageView31,imageView32,imageView34,imageView33,imageView35,imageView36,imageView37,imageView38,imageView39,imageView41,imageView47,imageView48,imageView49,imageView50,imageView51;
    TextView textView63,textView62,textView611,textView61,textView600,textView60,textView51,textView500,textView49,textView499,textView46,textView48
            ,textView47,textView50,textView45,textView444,textView44,textView433,textView43,textView422,textView42,textView41,textView39,textView40,textView38;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        imageView30 = findViewById(R.id.imageView30);
        imageView31 = findViewById(R.id.imageView31);
        imageView32 = findViewById(R.id.imageView32);
        imageView34 = findViewById(R.id.imageView34);
        imageView33 = findViewById(R.id.imageView33);
        imageView35 = findViewById(R.id.imageView35);
        imageView36 = findViewById(R.id.imageView36);
        imageView37 = findViewById(R.id.imageView37);
        imageView38 = findViewById(R.id.imageView38);
        imageView39 = findViewById(R.id.imageView39);
        imageView41 = findViewById(R.id.imageView41);
        imageView47 = findViewById(R.id.imageView47);
        imageView48 = findViewById(R.id.imageView48);
        imageView49 = findViewById(R.id.imageView49);
        imageView50 = findViewById(R.id.imageView50);
        imageView51 = findViewById(R.id.imageView51);

        textView63 = findViewById(R.id.textView63);
        textView62 = findViewById(R.id.textView62);
        textView611 = findViewById(R.id.textView611);
        textView61 = findViewById(R.id.textView61);
        textView600 = findViewById(R.id.textView600);
        textView60 = findViewById(R.id.textView60);
        textView51 = findViewById(R.id.textView51);
        textView49 = findViewById(R.id.textView49);
        textView46 = findViewById(R.id.textView46);
        textView48 = findViewById(R.id.textView48);
        textView47 = findViewById(R.id.textView47);
        textView50 = findViewById(R.id.textView50);
        textView45 = findViewById(R.id.textView45);
        textView44 = findViewById(R.id.textView44);
        textView43 = findViewById(R.id.textView43);
        textView42 = findViewById(R.id.textView42);
        textView41 = findViewById(R.id.textView41);
        textView39 = findViewById(R.id.textView39);
        textView40 = findViewById(R.id.textView40);
        textView38 = findViewById(R.id.textView38);



        imageView49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToMain = new Intent(HomeScreen.this,
                        imageUpload.class);
                startActivity(intToMain);
            }
        });

        imageView48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intTobooking = new Intent(HomeScreen.this,
                        wrapper.class);
                startActivity(intTobooking);
            }
        });
        imageView51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToMain = new Intent(HomeScreen.this,
                        bookingSuccess.class);
                startActivity(intToMain);
            }
        });
        imageView50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToMain = new Intent(HomeScreen.this,
                        MainActivity.class);
                startActivity(intToMain);
            }
        });
    }
}