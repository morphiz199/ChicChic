package com.example.projectchicchic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pay_success extends AppCompatActivity {
    Button goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);

        goHome = findViewById(R.id.goHome);

        goHome.setOnClickListener(v -> {
            Intent intToMain = new Intent(pay_success.this,
                    HomeScreen.class);
            startActivity(intToMain);
        });
    }
}