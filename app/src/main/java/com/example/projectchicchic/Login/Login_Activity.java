package com.example.projectchicchic.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectchicchic.HomePartner;
import com.example.projectchicchic.HomeScreen;
import com.example.projectchicchic.LoginUser;
import com.example.projectchicchic.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class Login_Activity extends AppCompatActivity {

    private CallbackManager mCallbackManage;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private LoginButton loginbutton;
    private static final String TAG = "FacebookAuthentication";
    private AccessTokenTracker accessTokenTracker;


    TabLayout tabLayout;
    ViewPager viewPager;
    EditText emailID,passwordID;
    TextView forgetPass;
    Button signin;
    TextView Signup;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseFirestore mFirebaseFirestore;
    boolean vaild = true;
    float v=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        tabLayout = findViewById(R.id. tab_layout);
        viewPager = findViewById(R.id. view_paper);
        loginbutton = findViewById(R.id.login_button);
        emailID = findViewById(R.id.email);
        passwordID = findViewById(R.id.password);
        forgetPass = findViewById(R.id.forgetpass);
        signin = findViewById(R.id.button);
        Signup = findViewById(R.id.signup);
        loginbutton.setReadPermissions("email","public_profile");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());

        mCallbackManage = CallbackManager.Factory.create();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null){
                    Toast.makeText(Login_Activity.this,"Your are longed in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login_Activity.this, HomeScreen.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Login_Activity.this,"Please longed in", Toast.LENGTH_SHORT).show();
                }

            }
        };

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(emailID);
                checkField(passwordID);

                Log.d("TAG","onClick: " + emailID.getText().toString());

                if (vaild){
                    mFirebaseAuth.signInWithEmailAndPassword(emailID.getText().toString(),passwordID.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login_Activity.this,"Login Successfully ",Toast.LENGTH_SHORT ).show();
                            checkUserAccessLevel(authResult.getUser().getUid());


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    });
                }
            }
        });
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailID.getText().toString();
//                String password = passwordID.getText().toString();
//                if (email.isEmpty()){
//                    emailID.setError("Please Enter email ID");
//                    emailID.requestFocus();
//
//                }
//                else if (password.isEmpty()){
//                    passwordID.setError("Please Enter your password");
//                    passwordID.requestFocus();
//                }
//                else if (email.isEmpty() && password.isEmpty()){
//                    Toast.makeText(Login_Activity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
//
//                }
//                else if (!(email.isEmpty() && password.isEmpty())){
//                    mFirebaseAuth.signInWithEmailAndPassword(email, password) .addOnCompleteListener(new Login_Activity(), new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (!task.isSuccessful()){
//
//                                Toast.makeText(Login_Activity.this, "Login Error Please Login Again", Toast.LENGTH_SHORT).show();
//                            }
//                            else{
//                                Intent inToHome = new Intent(Login_Activity.this,HomeUserActivity.class);
//                                startActivity(inToHome);
//
//                            }
//
//                        }
//                    });
//                }
//                else {
//                    Toast.makeText(Login_Activity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inSignUp = new Intent( Login_Activity.this, LoginUser.class);
                startActivity(inSignUp);
            }
        });


        loginbutton.registerCallback(mCallbackManage, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"onSuccess" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG,"onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"onError" + error);

            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    updateUI(user);
                }
                else {
                    updateUI(null);
                }
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null){
                    mFirebaseAuth.signOut();

                }
            }
        };







        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        emailID.setTranslationX(800);
        passwordID.setTranslationX(800);
        forgetPass.setTranslationX(800);
        signin.setTranslationX(800);
        Signup.setTranslationX(800);
        loginbutton.setTranslationY(300);

        emailID.setAlpha(v);
        passwordID.setAlpha(v);
        forgetPass.setAlpha(v);
        signin.setAlpha(v);
        Signup.setAlpha(v);
        loginbutton.setAlpha(v);

        emailID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        passwordID.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        Signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        loginbutton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();


    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = mFirebaseFirestore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess: " + documentSnapshot.getData());

                if (documentSnapshot.getString("isPartner") != null){
                    startActivity(new Intent(getApplicationContext(), HomePartner.class));
                    finish();
                }
                else if (documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                    finish();

                }

            }
        });
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

    private void handleFacebookToken(AccessToken token){
        Log.d(TAG, "handleFacebookToken" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credential) .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "sign in with credential : success");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    updateUI(user);

                }
                else {
                    Log.d(TAG, "sign in with credential : failuser", task.getException());
                    Toast.makeText(Login_Activity.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                    updateUI(null);

                }
            }
        });
    }
    private void updateUI(FirebaseUser user){
        if (user != null){
            if (user.getPhotoUrl() != null){
                String photoUrl = user.getPhotoUrl().toString();
                photoUrl = photoUrl + "?type=large";
            }

        }
        else {

        }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManage.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (authStateListener != null){
            mFirebaseAuth.removeAuthStateListener(authStateListener);

        }

    }

}

