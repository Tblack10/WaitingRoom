package com.example.waitingroom.business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.waitingroom.R;
import com.example.waitingroom.types.Caller;
import com.example.waitingroom.types.Request;

/**
 * CallerDetailActivity displays a customer's details such as name, number,
 * and reason for call.
 */
public class CallerDetailActivity extends AppCompatActivity {
    private Request request;
    private Caller caller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller_detail);
        Intent i = getIntent();
        request = (Request)i.getSerializableExtra("Request");
        caller = (Caller) getIntent().getSerializableExtra("user");
        TextView header = findViewById(R.id.Header);
        header.setText(caller.getEmployer());
        TextView requestDescription = findViewById(R.id.requestDescription);
        requestDescription.setText(request.getDescription());
        TextView customerName = findViewById(R.id.customerName);
        customerName.setText(request.getName());
        TextView customerPhoneNumber = findViewById(R.id.customerPhoneNumber);
        customerPhoneNumber.setText(request.getPhoneNumber().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"));
    }

    public void onCall(View v){
        //TODO: remove this customer from queue

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + request.getPhoneNumber()));
        startActivity(phoneIntent);
    }
}