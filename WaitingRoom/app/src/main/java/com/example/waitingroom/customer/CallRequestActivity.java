package com.example.waitingroom.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Request;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CallRequestActivity shows the businesses details and prompts
 * for input of name, phone, and reason for call before requesting a
 * call from the business.
 */
public class CallRequestActivity extends AppCompatActivity {
    Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_request);

        business = (Business) getIntent().getSerializableExtra("business");
        TextView businessNameTextView = findViewById(R.id.businessNameTextView);
        businessNameTextView.setText(business.getName());
    }

    public void createCall(View view) {
        String requestName = ((TextView) findViewById(R.id.requestName)).getText().toString();
        String requestPhoneNumber = ((TextView) findViewById(R.id.requestPhoneNumber)).getText().toString();
        String requestDescription = ((TextView) findViewById(R.id.requestDescription)).getText().toString();

        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(requestPhoneNumber);
        if (matcher.matches() && !requestName.isEmpty() && !requestDescription.isEmpty()) {
            Request request = new Request(requestName, requestPhoneNumber, requestDescription, new Timestamp(System.currentTimeMillis()).toString());

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Businesses").child(business.getName().toLowerCase()).child("requests");
            DatabaseReference requestReference = myRef.push();
            requestReference.setValue(request);

            Intent intent = new Intent(CallRequestActivity.this, PositionInLIneActivity.class);
            intent.putExtra("request", request);

            startActivity(intent);
        } else {
            Toast.makeText(CallRequestActivity.this, R.string.invalidCall, Toast.LENGTH_SHORT).show();
        }


    }

}