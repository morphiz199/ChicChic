package com.example.projectchicchic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectchicchic.Login.Login_Activity;
import com.example.projectchicchic.Model.loadSuccess;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.facebook.FacebookSdk.getApplicationContext;


public class ProfileParthner extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Button logout;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    TextView textView53,textView54;
    RecyclerView recyclerView;
    myAdapterSuccess adapter;
    ArrayList<loadSuccess> arrayList;
    boolean isClick = false;


    DatabaseReference mUserDatabase;

    public ProfileParthner() {
        // Required empty public constructor
    }


    public static ProfileParthner newInstance(String param1, String param2) {
        ProfileParthner fragment = new ProfileParthner();
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
        View view = inflater.inflate(R.layout.fragment_profile_parthner, container, false);
        textView53 = view.findViewById(R.id.textView53);
        textView54 = view.findViewById(R.id.textView54);
        logout = view.findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        textView53.setText("Salon");
        textView54.setText(firebaseUser.getEmail());
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        arrayList = new ArrayList<>();

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Success");

        FirebaseRecyclerOptions<loadSuccess> options =
                new FirebaseRecyclerOptions.Builder<loadSuccess>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Success"), loadSuccess.class)
                        .build();

        adapter = new myAdapterSuccess(options);
        recyclerView.setAdapter(adapter);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login_Activity.class));
            }
        });

        return view;
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