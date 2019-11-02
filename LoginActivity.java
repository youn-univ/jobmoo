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


public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button btLogin;
    private TextView txtRegister;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //firebase authentication에 있는 data를 가져와 처리
        firebaseAuth=FirebaseAuth.getInstance();

        //만약 firebaseauth에 있는 유저가 null이 아니면(즉 있으면(로그인 하여 메인 액티비티로 이동)
        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }


        progressDialog=new ProgressDialog(this);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        btLogin=(Button)findViewById(R.id.blogin);
        txtRegister=(TextView)findViewById(R.id.textViewRegister);

        email.setPrivateImeOptions("defaultInputmode=english;");
        password.setPrivateImeOptions("defaultInputmode=english;");


        //클릭 시 이벤트 처리
        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                userLogin();
            }
        });


        //이벤트 처리
        txtRegister.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }

        });
    }


    public void  userLogin() {

        String Email = email.getText().toString().trim();       //trim()앞뒤 공백이 자동으로 사라짐
        String Password = password.getText().toString().trim(); //trim()앞뒤 공백이 자동으로 사라짐

        if (TextUtils.isEmpty(Email)) {         //if(Email == null || Email == "")랑 비슷, 비어있는지 검사
            Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(Password)) {          //if(Password == null || Password == "")랑 비슷, 비어있는지 검사
            Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("조금만 기다려주세요..."); //로딩 화면 set
        progressDialog.show(); //로딩 화면 show


        //사용자의 이메일 주소와 비밀번호를 signInWithEmailAndPassword에 전달하여 로그인 처리합니다.
        firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss(); //로딩화면 사라짐

                if(task.isSuccessful()){   // task 성공 시
                    finish();
                    Toast.makeText(LoginActivity.this,"성공하였습니다",Toast.LENGTH_SHORT).show();

                    Intent intent1 = new Intent(getApplicationContext(), zMyService.class);
                    startService(intent1);

                    startActivity(new Intent(LoginActivity.this,MainActivity.class));  //메인액티비티로 넘김
                    finish();
                }

                else{
                    Toast.makeText(LoginActivity.this,"이메일이나 비밀번호가 틀렸습니다",Toast.LENGTH_SHORT).show(); //실패
                }
            }

        });
    }

}
