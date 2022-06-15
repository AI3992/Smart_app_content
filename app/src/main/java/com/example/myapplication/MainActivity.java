package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent thisIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.thisIntent = getIntent();

        //EditText cell_number = (EditText) findViewById(R.id.cell_number);
        //EditText Certification_Number = (EditText) findViewById(R.id.Certification_Number);

        //라디오 버튼 값 지정
        RadioButton teacherjob = (RadioButton)findViewById(R.id.teacher);
        RadioButton baristajob = (RadioButton) findViewById(R.id.Barista);

        //선택된 라디오 버튼에 맞는 뷰로 이동
        Button button = (Button) findViewById(R.id.Certification_Number_button);
        button.setOnClickListener(view -> {
            //선택된 라디오가 바리스타일 때 실행
            if(baristajob.isChecked())
            {
                Intent nextIntent = new Intent(this, BaristarActivity.class);
                startActivity(nextIntent);
                finish();
                overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            }
            //선택된 라디오가 선생님일 때 실행
            else if(teacherjob.isChecked())
            {
                Intent nextIntent = new Intent(this, TeacherActivity.class);
                //Intent nextIntent = new Intent(this, TeacherActivity.class);
                startActivity(nextIntent);
                //finish();
                //overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            }

        });
    }
}