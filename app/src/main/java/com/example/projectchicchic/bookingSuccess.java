package com.example.projectchicchic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectchicchic.Login.Login_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

public class bookingSuccess extends AppCompatActivity {

    TextView textView53,textView54,textView55,textView566,textView56,textView57,textView58,textView588,textView59,textView60,textView61,textView611,textView62,btnCalenda;
    ImageView profileSuccess,imageView40,imageView42,imageView43,imageView45,img01,img02,img03,imageView47,imageView49,imageView48,imageView50;
    Button logout;

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);

        textView53 = findViewById(R.id.textView53);
        textView54 = findViewById(R.id.textView54);
        textView55 = findViewById(R.id.textView55);
        textView566 = findViewById(R.id.textView566);
        textView56 = findViewById(R.id.textView56);
        textView57 = findViewById(R.id.textView57);
        textView58 = findViewById(R.id.textView58);
        textView588 = findViewById(R.id.textView588);
        textView59 = findViewById(R.id.textView59);
        textView60 = findViewById(R.id.textView60);
        textView61 = findViewById(R.id.textView61);
        textView611 = findViewById(R.id.textView611);
        textView62 = findViewById(R.id.textView62);
        btnCalenda = findViewById(R.id.btnCalenda);

        profileSuccess = findViewById(R.id.profileSuccess);
        imageView40 = findViewById(R.id.imageView40);
        imageView42 = findViewById(R.id.imageView42);
        imageView43 = findViewById(R.id.imageView43);
        imageView45 = findViewById(R.id.imageView45);
        imageView47 = findViewById(R.id.imageView47);
        imageView49 = findViewById(R.id.imageView49);
        imageView48 = findViewById(R.id.imageView48);
        imageView50 = findViewById(R.id.imageView50);

        logout = findViewById(R.id.button);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        textView54.setText(firebaseUser.getEmail());
        textView53.setText("Most");


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(bookingSuccess.this, Login_Activity.class));
            }
        });







    }
}