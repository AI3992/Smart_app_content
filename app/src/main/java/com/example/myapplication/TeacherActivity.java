package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class TeacherActivity extends MainActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    public String Select_order;
    Write_Order_List write_order_list;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    DatabaseReference databaseReference;

    FirebaseDatabase firebaseDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_teacher);

        GridLayout mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("WriteOrderList");


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
                        createNewDialog();

                        //Intent intent = new Intent(TeacherActivity.this,Popup.class);
                        //startActivity(intent);
                    }
                    else if (finalI == 2){
                        Select_order = "카라멜 마끼아또";
                        createNewDialog();
                        //Intent intent = new Intent(TeacherActivity.this,Popup.class);
                        //startActivity(intent);
                    }
                    else if (finalI == 3){
                        Select_order = "카푸치노";
                        createNewDialog();
                        //Intent intent = new Intent(TeacherActivity.this,Popup.class);
                        //startActivity(intent);
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
        final int[] count = {1};
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

                /*Map<String, Object> Order = new HashMap<>();
                Order.put("order_name", Select_order);
                Order.put("order_int", count);
                Order.put("User_name", "송한새");

                db.collection("Order")
                        .add(Order)
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
                db.collection("Order")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("reading", document.getId() + " => " + document.getData());
                                    }
                                } else {
                                    Log.w("reading", "Error getting documents.", task.getException());
                                }
                            }
                        });*/

                Intent intent = getIntent();
                String user_name = intent.getStringExtra("usre");

                write_order_list = new Write_Order_List();
                String order_int = count.toString();
                String order_name = Select_order;
                String User_name = user_name;

                addDataFirebase(order_name, order_int, User_name);

                dialog.dismiss();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }

    private void addDataFirebase(String Order_name, String Order_int, String User_name){
        write_order_list.setOrder_name(Order_name);
        write_order_list.setOrder_int(Order_int);
        write_order_list.setUser_name(User_name);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(write_order_list);

                Toast.makeText(TeacherActivity.this, "Data added", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TeacherActivity.this, "Fail to add data"+error, Toast.LENGTH_SHORT ).show();
            }
        });
    }

}