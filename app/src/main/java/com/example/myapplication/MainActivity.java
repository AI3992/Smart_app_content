package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {



    Intent thisIntent;
    EditText Email_Edit, Password_Edit;

    FirebaseAuth mAtuh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.thisIntent = getIntent();

        //edittext 리소스 지정
        Email_Edit = findViewById(R.id.input_User_id);
        Password_Edit = findViewById(R.id.input_User_PW);
        mAtuh = FirebaseAuth.getInstance();

        //선택된 라디오 버튼에 맞는 뷰로 이동
        Button Login_button = (Button) findViewById(R.id.Login_button);
        Login_button.setOnClickListener(view -> {

            loginUser();

        });

    }

    private void loginUser(){
        String email = Email_Edit.getText().toString();
        String password = Password_Edit.getText().toString();

        if(TextUtils.isEmpty(email)){
            Email_Edit.setError("Plese enter your email");
            Email_Edit.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            Password_Edit.setError("Plese enter your password");
            Password_Edit.requestFocus();
        }else{
            mAtuh.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //라디오 버튼 값 지정
            RadioButton teacherjob = (RadioButton) findViewById(R.id.Teacherjob);
            RadioButton baristajob = (RadioButton) findViewById(R.id.Baristajob);

            //선택된 라디오가 바리스타일 때 실행
            if(baristajob.isChecked())
            {
                Intent nextIntent = new Intent(this, BaristarActivity.class);
                startActivity(nextIntent);
                //finish();
                overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            }
            //선택된 라디오가 선생님일 때 실행
            else if(teacherjob.isChecked())
            {
                Intent nextIntent = new Intent(this, TeacherActivity.class);
                //Intent nextIntent = new Intent(this, TeacherActivity.class);
                startActivity(nextIntent);
                finish();
                overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            }


        }
    }
}