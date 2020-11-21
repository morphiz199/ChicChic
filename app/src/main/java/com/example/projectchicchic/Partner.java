package com.example.projectchicchic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Partner extends AppCompatActivity {
    Button btnLogout,update;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);

        btnLogout = findViewById(R.id.back_login_Partner);
        update = findViewById(R.id.Upload_Partner);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Partner.this, Login_Activity.class);
                startActivity(intToMain);
            }
        });
    }
}