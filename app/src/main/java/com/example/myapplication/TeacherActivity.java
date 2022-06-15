package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.zip.Inflater;

public class TeacherActivity extends MainActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_teacher);
/*
        Toast toast = Toast.makeText(this,"shit",Toast.LENGTH_SHORT);

        LinearLayout iceLinearLayout = findViewById(R.id.ice);
        View.OnClickListener clickListener =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case  R.id.ice:
                        Log.d("MEKA","ice layout click");
                        break;
                }
            }
        };
*/
        GridLayout mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }
    //private void setToggleEvent(GridLayout mainGrid){}
    private void setSingleEvent(GridLayout mainGrid){
        for(int i = 0; i <mainGrid.getChildCount();i++){
            CardView CardView =(CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            CardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View view){

                    if(finalI == 0){
                        Intent intent = new Intent(TeacherActivity.this, Popup.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1){
                        Intent intent = new Intent(TeacherActivity.this,Popup.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2){
                        Intent intent = new Intent(TeacherActivity.this,Popup.class);
                        startActivity(intent);
                    }
                    else if (finalI == 3){
                        Intent intent = new Intent(TeacherActivity.this,Popup.class);
                    startActivity(intent);
                    }
                    else{
                        Toast.makeText(TeacherActivity.this,"fuck bois",Toast.LENGTH_SHORT);
                    }
                }
            });
        }
    }


        /*
        Li
        nearLayout menu_photo = (LinearLayout) findViewById(R.id.menu);
        */

        /*View.OnClickListener cilckListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.ice :
                        Log.e("MEKA","Clicked");
                        Intent menu_intent = new Intent(TeacherActivity.this,Popup.class);
                        startActivity(menu_intent);
                        break;

                }
            }
        };

        menu_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent menu_intent = new Intent(TeacherActivity.this,Popup.class);
                startActivity(menu_intent);
            }
        });
    }
    */
    /*
    View view = Inflater.inflate(R.layout.activity_teacher, menu, false);
    LinearLayout layout = view.findViewById(R.id.menu);
    Layout.se
    */


    //final ImageView imageView = (ImageView) findViewById(R.id.select_imageView);
    //ImageButton imagebuttin = (ImageButton) findViewById(R.id.);

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }


}