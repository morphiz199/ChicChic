package com.example.projectchicchic.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectchicchic.LoginUser;
import com.example.projectchicchic.Model.User;
import com.example.projectchicchic.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    DatabaseReference databaseReference;
    CheckBox isUser;
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
        isUser = findViewById(R.id.checkBox_User);



        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFirestore = FirebaseFirestore.getInstance();




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(nameID);
                checkField(emailID);
                checkField(passwordID);
                checkField(phoneID);
                checkField(confirm);


                if (!isUser.isChecked()){
                    Toast.makeText(register_user.this,"Select The Account Type", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (vaild){
                    mFirebaseAuth.createUserWithEmailAndPassword(emailID.getText().toString(),passwordID.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Toast.makeText(register_user.this,"Account Create",Toast.LENGTH_SHORT ).show();
                            DocumentReference df = mFirebaseFirestore.collection("Users").document(user.getUid());
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

                            User user_object = new User(nameID.getText().toString(),emailID.getText().toString(),passwordID.getText().toString(),phoneID.getText().toString());

                            databaseReference.child(user.getUid()).setValue(user_object).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"User data saved",Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"User data could not be saved",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            Map<String,Object> userInfo = new HashMap<>();
                            userInfo.put("Fullname",nameID.getText().toString());
                            userInfo.put("UserEmail",emailID.getText().toString());
                            userInfo.put("PhoneNumber",phoneID.getText().toString());

                            if (isUser.isChecked()){
                                userInfo.put("isUser","1");

                            }



                            df.set(userInfo);

                            startActivity(new Intent(getApplicationContext(), Login_Activity.class));
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



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register_user.this, LoginUser.class);
                startActivity(i);
            }
        });

        emailID.setTranslationX(800);
        passwordID.setTranslationX(800);
        confirm.setTranslationX(800);
        signup.setTranslationX(800);
        back.setTranslationX(800);
        nameID.setTranslationX(800);
        phoneID.setTranslationX(800);
        isUser.setTranslationX(800);

        emailID.setAlpha(v);
        passwordID.setAlpha(v);
        confirm.setAlpha(v);
        signup.setAlpha(v);
        back.setAlpha(v);
        nameID.setAlpha(v);
        phoneID.setAlpha(v);
        isUser.setAlpha(v);

        emailID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        passwordID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confirm.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        back.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        nameID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        phoneID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        isUser.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
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