package com.example.projectchicchic.Booking;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectchicchic.Model.model;
import com.example.projectchicchic.R;
import com.example.projectchicchic.myAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class recfragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    myAdapter adapter;
    ImageView backUpload;
    EditText editText;
    Button searchList;
    ArrayList<model> arrayList;
    boolean isClick = false;

    TextView textView33,textView34,textView35,cleanNail,type;

    DatabaseReference mUserDatabase;


    private String mParam1;
    private String mParam2;

    public recfragment() {

    }


    public static recfragment newInstance(String param1, String param2) {
        recfragment fragment = new recfragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recfragment, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        backUpload = (ImageView)view.findViewById(R.id.backUpload);
        editText = (EditText)view.findViewById(R.id.editText);
        searchList = (Button) view.findViewById(R.id.searchList);


        textView33 = (TextView)view.findViewById(R.id.textView33);
        textView34 = (TextView)view.findViewById(R.id.textView34);
        textView35 = (TextView)view.findViewById(R.id.textView35);
        cleanNail = (TextView)view.findViewById(R.id.cleanNail);

        textView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textView34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cleanNail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        arrayList = new ArrayList<>();

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Nail");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()){
                    search(s.toString());

                }else {
                    search("");
                }

            }
        });


        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nail"), model.class)
                        .build();

        adapter = new myAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
    }
    private void search(String toString) {
        adapter.getFilter().filter(toString);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}