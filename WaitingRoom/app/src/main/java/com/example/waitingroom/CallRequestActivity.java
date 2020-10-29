package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * CallRequestActivity shows the businesses details and prompts
 * for input of name, phone, and reason for call before requesting a
 * call from the business.
 */
public class CallRequestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_request);

        String bName = (String) getIntent().getExtras().get("businessName");
        TextView businessNameTextView = findViewById(R.id.businessNameTextView);
        businessNameTextView.setText(bName);
    }

    public void createCall(View view) {
        Intent intent = new Intent(CallRequestActivity.this, PositionInLIneActivity.class);
        startActivity(intent);
    }

}