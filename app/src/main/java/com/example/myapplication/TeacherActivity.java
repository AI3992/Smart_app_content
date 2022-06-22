package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class TeacherActivity extends MainActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    public String Select_order;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_teacher);

        GridLayout mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }
    private void setSingleEvent(GridLayout mainGrid){
        for(int i = 0; i <mainGrid.getChildCount();i++){
            CardView CardView =(CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            CardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View view){

                    if(finalI == 0){
                        Select_order = "아이스 아메리카노";
                        createNewDialog();
                        //Intent intent = new Intent(TeacherActivity.this, Popup.class);
                        //startActivity(intent);
                    }
                    else if (finalI == 1){
                        Select_order = "바닐라 라떼";
                        Intent intent = new Intent(TeacherActivity.this,Popup.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2){
                        Select_order = "카라멜 마끼아또";
                        Intent intent = new Intent(TeacherActivity.this,Popup.class);
                        startActivity(intent);
                    }
                    else if (finalI == 3){
                        Select_order = "카푸치노";
                        Intent intent = new Intent(TeacherActivity.this,Popup.class);
                    startActivity(intent);
                    }
                }
            });
        }
    }

    public void createNewDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contectpopupView = getLayoutInflater().inflate(R.layout.dialog,null);

        Button orderbtn = (Button) contectpopupView.findViewById(R.id.Order_button);
        Button upbtn = (Button) contectpopupView.findViewById(R.id.up_button);
        Button downbtn = (Button) contectpopupView.findViewById(R.id.down_button);
        final int[] count = {0};
        TextView textcount = (TextView) contectpopupView.findViewById(R.id.count_view);

        dialogBuilder.setView(contectpopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count[0]++;
                textcount.setText(count[0] +"");
            }
        });
        downbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count[0]--;
                textcount.setText(count[0]+"");
            }
        });
        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> user = new HashMap<>();
                user.put("order_name", Select_order);
                user.put("order_int", count);
                user.put("born", 1815);

                db.collection("users")
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
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }


}