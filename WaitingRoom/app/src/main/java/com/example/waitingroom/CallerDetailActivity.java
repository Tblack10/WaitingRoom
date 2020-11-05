package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * CallerDetailActivity displays a customer's details such as name, number,
 * and reason for call.
 */
public class CallerDetailActivity extends AppCompatActivity {
    private Request request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller_detail);
        Intent i = getIntent();
        request = (Request)i.getSerializableExtra("Request");
        TextView requestTitle = findViewById(R.id.requestTitle);
        requestTitle.setText(request.getTitle());
        TextView requestDescription = findViewById(R.id.requestDescription);
        requestDescription.setText(request.getDescription());
        TextView customerName = findViewById(R.id.customerName);
        customerName.setText(request.getCustomer().getName());
        TextView customerPhoneNumber = findViewById(R.id.customerPhoneNumber);
        customerPhoneNumber.setText(request.getCustomer().getPhoneNumber());
    }

    public void onCall(View v){
        //TODO: remove this customer from queue

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + request.getCustomer().getPhoneNumber()));
        startActivity(phoneIntent);
    }
}