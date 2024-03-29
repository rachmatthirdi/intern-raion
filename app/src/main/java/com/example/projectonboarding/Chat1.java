package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Chat1 extends AppCompatActivity {
    private ImageView back;
    private TextView guru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat1);
       back = findViewById(R.id.bckSiwa);
       guru = findViewById(R.id.txtSiwa);
       String namaGuru = getIntent().getStringExtra("Guru");
       guru.setText(namaGuru);

       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), Chat.class);
               startActivity(intent);
           }
       });
    }
}