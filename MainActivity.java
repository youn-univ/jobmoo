package com.example.job_moo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class MainActivity extends AppCompatActivity {




    private Button btnlogout;
    private FirebaseAuth firebaseAuth;




    TextView monClass1;
    TextView monClass2;
    TextView monClass3;
    TextView monClass4;

    TextView tueClass1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }

        btnlogout=(Button)findViewById(R.id.btlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });




        monClass1 = (TextView)findViewById(R.id.button01);
        monClass2 = (TextView)findViewById(R.id.button06);
        monClass3 = (TextView)findViewById(R.id.button11);
        monClass4 = (TextView)findViewById(R.id.button16);

        tueClass1 = (TextView)findViewById(R.id.button02);


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference rMon1 = database.getReference("class").child("mon").child("1");
        rMon1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String result = snapshot.getValue().toString();
                    monClass1.setText(result);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference rMon2 = database.getReference("class").child("mon").child("2");
        rMon2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String result = snapshot.getValue().toString();
                    monClass2.setText(result);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference rMon3 = database.getReference("class").child("mon").child("3");
        rMon3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String result = snapshot.getValue().toString();
                    monClass3.setText(result);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference rMon4 = database.getReference("class").child("mon").child("4");
        rMon4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String result = snapshot.getValue().toString();
                    monClass4.setText(result);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void questClick(View view){
        Intent intent = new Intent(this, QuestActivity.class);
        startActivity(intent);
    }


    public void mOnPopup1 (View v) {
        Intent intent = new Intent(this, Popup1Activity.class);
        intent.putExtra("class", "1교시");
        startActivityForResult(intent, 1);
    }

    public void mOnPopup2 (View v) {
        Intent intent = new Intent(this, Popup2Activity.class);
        startActivityForResult(intent, 1);
    }

    public void mOnPopup6 (View v) {
        Intent intent = new Intent(this, Popup6Activity.class);
        intent.putExtra("class", "2교시");
        startActivityForResult(intent, 1);
    }

    public void mOnPopup11 (View v) {
        Intent intent = new Intent(this, Popup11Activity.class);
        intent.putExtra("class", "3교시");
        startActivityForResult(intent, 1);
    }

    public void mOnPopup16 (View v) {
        Intent intent = new Intent(this, Popup16Activity.class);
        intent.putExtra("class", "4교시");
        startActivityForResult(intent, 1);
    }



}