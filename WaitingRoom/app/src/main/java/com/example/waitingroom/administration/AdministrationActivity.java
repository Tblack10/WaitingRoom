package com.example.waitingroom.administration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.waitingroom.R;
import com.example.waitingroom.types.Employee;

public class AdministrationActivity extends AppCompatActivity {
    Employee user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_administration);
        user = (Employee) getIntent().getSerializableExtra("user");
        setWelcomeMessage(user.getName());
    }

    private void setWelcomeMessage(String name) {
        TextView greetingTextView = findViewById(R.id.greetingTextView);
        greetingTextView.setText("Welcome, " + name + "!");
    }

    private void configureListAdapter() {

    }




}