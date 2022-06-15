package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Base64_test_Activity extends MainActivity{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_test);

        Firebase firebase = new Firebase();
        List<List<String>> list = firebase.getData(db, "Product");
        Log.d("MEKA", "list size : " + list.size());

        for(int i=0; i<list.size(); i++)
        {
            Log.d("MEKA", "image base 64 : " + list.get(i).get(0));
        }
    }
}
