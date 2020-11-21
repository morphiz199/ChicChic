package com.example.projectchicchic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeUserActivity extends AppCompatActivity {
    Button btnLogout,update,search;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        btnLogout = findViewById(R.id.back_login);
        update = findViewById(R.id.Upload);
        search = findViewById(R.id.Search);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeUserActivity.this, Login_Activity.class);
                startActivity(intToMain);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToUpload = new Intent(HomeUserActivity.this, imageUpload.class);
                startActivity(intToUpload);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToSearch = new Intent(HomeUserActivity.this, UploadActivity.class);
                startActivity(intToSearch);
            }
        });
    }
}