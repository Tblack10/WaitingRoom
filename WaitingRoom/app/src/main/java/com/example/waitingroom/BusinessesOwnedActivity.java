package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class BusinessesOwnedActivity extends AppCompatActivity {
    String username;
    Caller admin;
    ArrayList<Business> businesses;
    ListView listBusinesses;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businesses_owned);
        admin = (Caller) getIntent().getSerializableExtra("USER");
        username = admin.getName();
        setWelcomeMessage(username);
//        NetworkManager.queryDatabaseForCustomer(username, new MyCallback() {
//            @Override
//            public void onCallback(Customer customer) {
//                //businesses = customer.getBusinesses();
//                configureListAdapter();
//                setWelcomeMessage(customer.getName());
//            }
//        });

    }

    private void setWelcomeMessage(String name) {
        TextView greetingTextView = findViewById(R.id.greetingTextView);
        greetingTextView.setText("Welcome, " + name + "!");
    }

    private void configureListAdapter() {
        listBusinesses = findViewById(R.id.businessListView);

        ArrayAdapter<Business> businessListAdapter;

        businessListAdapter = new ArrayAdapter<Business>(
                BusinessesOwnedActivity.this, android.R.layout.simple_list_item_1, businesses
        );

        listBusinesses.setAdapter(businessListAdapter);
    }




}