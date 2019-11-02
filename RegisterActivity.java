package com.example.job_moo;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class RegisterActivity extends AppCompatActivity {

    private EditText email,password;
    private Button btRegister;
    private TextView backToLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //각 객체 생성
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();


        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        btRegister=(Button)findViewById(R.id.register);
        backToLogin=(TextView)findViewById(R.id.textViewLogin);

        email.setPrivateImeOptions("defaultInputmode=english;");
        password.setPrivateImeOptions("defaultInputmode=english;");


        //클릭 시 이벤트 처리
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerData();
            }

        });

        //클릭 시 이벤트 처리
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void registerData(){

        String Email=email.getText().toString().trim(); //trim()앞뒤 공백이 자동으로 사라짐
        String Password=password.getText().toString().trim();  //trim()앞뒤 공백이 자동으로 사라짐

        if (TextUtils.isEmpty(Email)){  //if(Email == null || Email == "")랑 비슷, 비어있는지 검사
            Toast.makeText(this, "이메일",Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(Password)){ //위와 같음
            Toast.makeText(this, "비밀번호",Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("조금만 기다려주세요..."); //로딩시 화면 처리
        progressDialog.show();


        //신규 사용자의 이메일 주소와 비밀번호를 createUserWithEmailAndPassword에 전달하여 신규 계정을 생성합니다.
        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){ //성공시
                    Toast.makeText(RegisterActivity.this,"성공하였습니다",Toast.LENGTH_SHORT).show();
                    if (firebaseAuth.getCurrentUser()!=null){ //유저가 firebaseauth상에 존재하면 ㄱㄱ
                        finish();
                        backToLogin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                    }
                }

                else{
                    Toast.makeText(RegisterActivity.this,"실패하였습니다",Toast.LENGTH_SHORT).show(); //실패시 (다시)
                }

            }

        });


    }


}
