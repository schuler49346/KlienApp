package com.example.klienapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName,regEmail,regPw;
    private Button btnreg;
    private DatabaseReference rootdbref;
    private TextView textview,textview2;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regName = findViewById(R.id.namereg);
        regEmail = findViewById(R.id.emailreg);
        regPw = findViewById(R.id.pwreg);
        btnreg = findViewById(R.id.reg_btn);

        mAuth = FirebaseAuth.getInstance();

        rootdbref = FirebaseDatabase.getInstance().getReference().child("Users");

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regName.getText().toString();
                String name = regEmail.getText().toString();
                String password = regPw.getText().toString();
                registerUser(email,name,password);
            }
        });
    }

    private void registerUser(String email, String name, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful())
            {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String UID = currentUser.getUid();

                HashMap hashMap = new HashMap();
                hashMap.put("Email",email);
                hashMap.put("Name",name);

                rootdbref.child(UID).setValue(hashMap).addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        Intent main_intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(main_intent);
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this,"data add failure",Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(RegisterActivity.this, "firebase user creation failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}