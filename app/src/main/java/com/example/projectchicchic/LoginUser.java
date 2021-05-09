package com.example.projectchicchic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectchicchic.Login.Login_Activity;
import com.example.projectchicchic.Login.register_business;
import com.example.projectchicchic.Login.register_user;

public class LoginUser extends AppCompatActivity {


    Button User,Partner;
    TextView Login;
    float v=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        User = findViewById(R.id.button_User);
        Partner = findViewById(R.id.button_Partner);
        Login = findViewById(R.id.back_login);

        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToUser = new Intent(LoginUser.this, register_user.class);
                startActivity(intToUser);
            }
        });
        Partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToPartner = new Intent(LoginUser.this, register_business.class);
                startActivity(intToPartner);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToLogin = new Intent(LoginUser.this, Login_Activity.class);
                startActivity(intToLogin);

            }
        });





        User.setTranslationX(800);
        Partner.setTranslationX(800);
        Login.setTranslationX(800);


        User.setAlpha(v);
        Partner.setAlpha(v);
        Login.setAlpha(v);


        User.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        Partner.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        Login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        return;

    }

}