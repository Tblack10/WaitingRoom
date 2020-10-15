package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CallerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller_detail);
        Intent i = getIntent();
        Customer customer = (Customer)i.getSerializableExtra("Customer");
        TextView customerName = findViewById(R.id.callerName);
        customerName.setText(customer.getName());
    }
}