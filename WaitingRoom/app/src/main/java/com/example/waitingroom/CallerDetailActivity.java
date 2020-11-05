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
    private Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller_detail);
        Intent i = getIntent();
        customer = (Customer)i.getSerializableExtra("Customer");
        TextView customerName = findViewById(R.id.customerName);
        customerName.setText(customer.getName());
        TextView customerReason = findViewById(R.id.customerReason);
        customerReason.setText(customer.getReason());
    }

    public void onCall(View v){
        //TODO: remove this customer from queue

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + customer.getPhoneNumber()));
        startActivity(phoneIntent);
    }
}