package com.example.projectchicchic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginUser extends AppCompatActivity {

    EditText emailID,passwordID;
    TextView forgetPass;
    Button signin;
    TextView Signup;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        emailID = findViewById(R.id.email);
        passwordID = findViewById(R.id.password);
        forgetPass = findViewById(R.id.forgetpass);
        signin = findViewById(R.id.button);
        Signup = findViewById(R.id.signup);
        mFirebaseAuth = FirebaseAuth.getInstance();



        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseeUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseeUser != null){
                    Toast.makeText( LoginUser.this,"Your are longed in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(new Login_Activity(),HomeUserActivity.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(LoginUser.this,"Please longed in", Toast.LENGTH_SHORT).show();
                }

            }
        };
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String password = passwordID.getText().toString();
                if (email.isEmpty()){
                    emailID.setError("Please Enter email ID");
                    emailID.requestFocus();

                }
                else if (password.isEmpty()){
                    passwordID.setError("Please Enter your password");
                    passwordID.requestFocus();
                }
                else if (email.isEmpty() && password.isEmpty()){
                    Toast.makeText(LoginUser.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();

                }
                else if (!email.isEmpty() && password.isEmpty()){
                    mFirebaseAuth.signInWithEmailAndPassword(email, password) .addOnCompleteListener(LoginUser.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){

                                Toast.makeText(LoginUser.this, "Login Error Please Login Again", Toast.LENGTH_SHORT).show();


                            }
                            else{
                                Intent inToHome = new Intent(LoginUser.this,HomeUserActivity.class);
                                startActivity(inToHome);

                            }

                        }
                    });
                }
                else {
                    Toast.makeText(LoginUser.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inSignUp = new Intent( LoginUser.this, register_user.class);
                startActivity(inSignUp);
            }
        });


        emailID.setTranslationX(800);
        passwordID.setTranslationX(800);
        forgetPass.setTranslationX(800);
        signin.setTranslationX(800);
        Signup.setTranslationX(800);

        emailID.setAlpha(v);
        passwordID.setAlpha(v);
        forgetPass.setAlpha(v);
        signin.setAlpha(v);
        Signup.setAlpha(v);

        emailID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        passwordID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        Signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        return;

    }
    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}