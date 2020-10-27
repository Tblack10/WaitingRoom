package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BusinessLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);
    }

    public void login(View v) {
        TextView nameField = findViewById(R.id.name);
        TextView passwordField = findViewById(R.id.password);
        Intent intent = new Intent(BusinessLoginActivity.this, CallQueueActivity.class);
        intent.putExtra("NAME_ID", "" + nameField.getText().toString());
        intent.putExtra("PASSWORD_ID", "" + passwordField.getText().toString());
        startActivity(intent);
    }

    public void register(View v) {
        Intent intent = new Intent(BusinessLoginActivity.this, BusinessCreateUserActivity.class);
        startActivity(intent);
    }
}