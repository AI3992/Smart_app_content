package com.example.myapplication;

import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Firebase {

    public List<List<String>> getData(FirebaseFirestore db, String collection_name) {
        List<List<String>> collections = new ArrayList<>();
        List<String> fieldData = new ArrayList<>();

        db.collection(collection_name)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        int i = 0;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            fieldData.add("" + document.getData());
                            Log.d("reading",  "" + document.getData());
                        }
                        collections.add(fieldData);
                    } else {
                        Log.w("reading", "Error getting documents.", task.getException());
                    }
                }
            });

        return collections;
    }
    public void setData(FirebaseFirestore db, Map user, String collection_name){

        db.collection(collection_name)
            .add(user)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("MEKA", "DocumentSnapshot added with ID: " + documentReference.getId());
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("MEKA", "Error adding document", e);
                }
            });
    }
}
