package com.example.projectchicchic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class LoginBusinesssTabFagment extends Fragment {

    EditText email,password;
    TextView forgetPass;
    Button login,regis;
    float v = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_business_fragment, container,false);
        email = root.findViewById(R.id.email_1);
        password = root.findViewById(R.id.password_1);
        forgetPass = root.findViewById(R.id.forgetpass_1);
        login = root.findViewById(R.id.button_1);
        regis = root.findViewById(R.id.regis_1);

        email.setTranslationX(800);
        password.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);
        regis.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);
        regis.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        regis.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();



        return root;

    }
}
