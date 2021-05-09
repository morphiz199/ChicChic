package com.example.projectchicchic.Booking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projectchicchic.Model.UserSucces;
import com.example.projectchicchic.R;
import com.example.projectchicchic.bookingConfirm;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

import static com.facebook.FacebookSdk.getApplicationContext;


public class descfragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String NameStore,TypeNail,PriceNail,ImageUrl,Branch;

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timePickerDia;
    TextView Date_Pick,tv_time;
    ImageView imageHolder,select_time;
    Button success;
    TextView FinitPrice,branch;
    boolean isSuccess=false;
    DatabaseReference reference;
    FirebaseDatabase  rootnode;
    StorageReference StorageRef;
    private static final int REQUEST_CODE_IMAGE = 101;
    Uri imageUri;

    public descfragment() {

    }
    public descfragment(String NameStore,String TypeNail,String PriceNail,String ImageUrl,String Branch) {
        this.NameStore = NameStore;
        this.TypeNail = TypeNail;
        this.PriceNail = PriceNail;
        this.ImageUrl = ImageUrl;
        this.Branch = Branch;


    }


    public static descfragment newInstance(String param1, String param2) {
        descfragment fragment = new descfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_descfragment, container, false);
        imageHolder = view.findViewById(R.id.imgOrder);
        final TextView Typenail = view.findViewById(R.id.res_name);
        TextView Pricenail = view.findViewById(R.id.priceNail);
        TextView Pricenail2 = view.findViewById(R.id.priceNail2);
        FinitPrice = view.findViewById(R.id.finitPrice);
        branch = view.findViewById(R.id.branch);



        success = view.findViewById(R.id.success);



        Typenail.setText(TypeNail);
        Pricenail.setText(PriceNail);
        Pricenail2.setText(PriceNail);
        FinitPrice.setText(PriceNail);

        Glide.with(getContext()).load(ImageUrl).into(imageHolder);

        select_time = view.findViewById(R.id.select_time);
        tv_time = view.findViewById(R.id.tv_time);
        ImageView Select_Date = view.findViewById(R.id.Select_Date);
        Date_Pick = view.findViewById(R.id.Date_Pick);

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("Success");


                String NameType = Typenail.getText().toString();
                String Date = Date_Pick.getText().toString();
                String Time = tv_time.getText().toString();
                String Price = FinitPrice.getText().toString();
                String Branch = branch.getText().toString();

                UserSucces userSucces = new UserSucces(NameType,Date,Time,Price,Branch);
                reference.child(NameType).setValue(userSucces);

                startActivity(new Intent(getApplicationContext(), bookingConfirm.class));
                Toast.makeText(descfragment.this.getContext(),"Data Successfully Upload", Toast.LENGTH_SHORT).show();
            }

        });



        Select_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int Date = calendar.get(Calendar.DAY_OF_MONTH);
                int Month = calendar.get(Calendar.MONTH);
                int Year = calendar.get(Calendar.YEAR);


                DatePickerDialog dialog = new DatePickerDialog(descfragment.this.getContext(),
                        dateSetListener,
                        Year,
                        Month,
                        Date);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }

        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;

                String date = month + "/" + dayOfMonth + "/" + year;
                Date_Pick.setText(date);
            }
        };

        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePicker;
                timePicker = new TimePickerDialog(descfragment.this.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv_time.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,false);
                timePicker.show();
            }
        });



        return view;
    }




    }
