package com.example.waitingroom.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
public class CreateRequestActivity extends AppCompatActivity {
    Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_create_request);

        business = (Business) getIntent().getSerializableExtra("business");
        TextView businessNameTextView = findViewById(R.id.businessNameTextView);
        businessNameTextView.setText(business.getName());
    }

    public void createCall(View view) {
        TextView requestNameField = findViewById(R.id.requestName);
        TextView requestPhoneField =findViewById(R.id.requestPhoneNumber);
        TextView requestDescriptionField = findViewById(R.id.requestDescription);
        String requestName = requestNameField.getText().toString();
        String requestPhoneNumber = requestPhoneField.getText().toString();
        String requestDescription = requestDescriptionField.getText().toString();
        boolean valid = true;
        if (requestNameField.getText().toString().trim().equalsIgnoreCase("")) {
            requestNameField.setError(getString(R.string.invalidField));
            valid = false;
        }
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(requestPhoneNumber);
        if (!matcher.matches()) {
            requestPhoneField.setError(getString(R.string.invalidNumber));
            valid = false;
        }
        if (requestDescriptionField.getText().toString().trim().equalsIgnoreCase("")) {
            requestDescriptionField.setError(getString(R.string.invalidField));
            valid = false;
        }
        if (valid) {
            Request request = new Request(requestName, requestPhoneNumber, requestDescription, new Timestamp(System.currentTimeMillis()).toString());
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Businesses").child(business.getName().toLowerCase()).child("requests");
            DatabaseReference requestReference = myRef.push();
            requestReference.setValue(request);
            Intent intent = new Intent(CreateRequestActivity.this, PositionInLIneActivity.class);
            intent.putExtra("request", request);

            startActivity(intent);
        }

    }

}