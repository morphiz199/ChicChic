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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register_user extends AppCompatActivity {

    EditText nameID,emailID,phoneID,passwordID,confirm;
    Button signup;
    TextView back;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseFirestore;
    boolean vaild = true;
    float v=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        nameID = findViewById(R.id.name_regis);
        emailID = findViewById(R.id.email_regis);
        passwordID = findViewById(R.id.password_regis);
        phoneID = findViewById(R.id.phone_regis);
        confirm = findViewById(R.id.password_regis_confirm);
        signup = findViewById(R.id.button_ok);
        back = findViewById(R.id.back_signin);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        checkField(nameID);
        checkField(emailID);
        checkField(passwordID);
        checkField(phoneID);
        checkField(confirm);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vaild){
                    mFirebaseAuth.createUserWithEmailAndPassword(emailID.getText().toString(),passwordID.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Toast.makeText(register_user.this,"Account Create",Toast.LENGTH_SHORT ).show();
                            DocumentReference df = mFirebaseFirestore.collection("Users").document(user.getUid());
                            Map<String,Object> userInfo = new HashMap<>();
                            userInfo.put("Fullname",nameID.getText().toString());
                            userInfo.put("UserEmail",emailID.getText().toString());
                            userInfo.put("PhoneNumber",phoneID.getText().toString());

                            userInfo.put("isUser","1");

                            df.set(userInfo);

                            startActivity(new Intent(getApplicationContext(),Login_Activity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(register_user.this,"Failed to Create Account ",Toast.LENGTH_SHORT ).show();
                        }
                    });
                }
            }
        });


//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailID.getText().toString();
//                String password = passwordID.getText().toString();
//                String confirmpass = confirm.getText().toString();
//                if (email.isEmpty()){
//                    emailID.setError("Please Enter email ID");
//                    emailID.requestFocus();
//
//                }
//                else if (password.isEmpty()){
//                    passwordID.setError("Please Enter your password");
//                    passwordID.requestFocus();
//                }
//                else if (email.isEmpty() && password.isEmpty() && confirmpass.isEmpty()){
//                    Toast.makeText(register_user.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
//
//                }
//                else if(password != confirmpass){
//                    Toast.makeText(register_user.this, "Error!, Please Check your password or confirm password", Toast.LENGTH_SHORT).show();
//                }
//                else if (! (email.isEmpty() && password.isEmpty())){
//                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(register_user.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (!task.isSuccessful()){
//                                Toast.makeText(register_user.this, "SignUp Unsuccessful,Please Try Again", Toast.LENGTH_SHORT).show();
//                            }else {
//                                startActivity(new Intent(register_user.this,HomeUserActivity.class));
//                            }
//                        }
//                    });
//                }
//                else {
//                    Toast.makeText(register_user.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register_user.this,Login_Activity.class);
                startActivity(i);
            }
        });

        emailID.setTranslationX(800);
        passwordID.setTranslationX(800);
        confirm.setTranslationX(800);
        signup.setTranslationX(800);
        back.setTranslationX(800);

        emailID.setAlpha(v);
        passwordID.setAlpha(v);
        confirm.setAlpha(v);
        signup.setAlpha(v);
        back.setAlpha(v);

        emailID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        passwordID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confirm.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        back.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
    }

    private boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()){
            textField.setError("error");
            vaild = false;
        }else {
            vaild = true;
        }
        return vaild;
    }


}