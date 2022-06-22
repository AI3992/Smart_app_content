package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Popup extends AppCompatActivity {

    public Button upbtn, downbtn;
    public int count = 0;
    public TextView tvcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.dialog);

        /*
        tvcount = findViewById(R.id.count_view);
        tvcount.setText(count+"");
        upbtn = findViewById(R.id.up_button);
        downbtn = findViewById(R.id.down_button);



        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                tvcount.setText(count+"");
            }
        });
        downbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                tvcount.setText(count+"");
            }
        });*/
    }

}
