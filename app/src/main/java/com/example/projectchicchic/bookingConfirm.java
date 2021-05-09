package com.example.projectchicchic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class bookingConfirm extends AppCompatActivity {

    ImageView imgBG,back_fragment;
    CheckBox checkbox;
    TextView pay;
    Button button_confirm;
    boolean vaild = true;
    public DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);
        button_confirm = findViewById(R.id.button_confirm);
        imgBG = findViewById(R.id.imgBG);
        back_fragment = findViewById(R.id.back_fragment);
        checkbox = findViewById(R.id.checkbox);
        pay = findViewById(R.id.pay);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map map = (Map)snapshot.getValue();
                String value = String.valueOf(map.get("pay"));
                pay.setText(value);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button_confirm.setOnClickListener(v -> {

                    Intent intToMain = new Intent(bookingConfirm.this,
                            pay_success.class);
                    startActivity(intToMain);



        });
        back_fragment.setOnClickListener(v -> {
            Intent intToMain = new Intent(bookingConfirm.this,
                    payment.class);
            startActivity(intToMain);
        });


//        button_confirm.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//
//        });

//        Intent intent = getIntent();
//        int payment = intent.getIntExtra("Pay",0);
//        pay.setText(payment);
    }
}