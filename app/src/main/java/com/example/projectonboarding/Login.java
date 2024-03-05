package com.example.projectonboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button  btnLogin, btnLoginAdmin;
    private TextView btnRegist,btnForgot;
    private DatabaseReference database, databaseAdmin;
    private FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user!=null){
            user.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmailLog);
        etPassword = findViewById(R.id.etPassword);
        btnRegist = findViewById(R.id.btnRegistLog1);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgot = findViewById(R.id.tvForgot);
        auth = FirebaseAuth.getInstance();

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  keRegist = new Intent(getApplicationContext(), Register.class);
                startActivity(keRegist);
            }
        });
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(getApplicationContext(), Forgot.class);
                startActivity(forgot);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                database = FirebaseDatabase.getInstance().getReference("users");
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Login Berhasil",Toast.LENGTH_SHORT).show();
                                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(main);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Login Gagal",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }
}