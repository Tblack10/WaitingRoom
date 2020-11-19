package com.example.waitingroom.business;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.waitingroom.MyCallback;
import com.example.waitingroom.NetworkManager;
import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Customer;

import java.util.ArrayList;

public class BusinessesOwnedActivity extends AppCompatActivity {
    String username;
    ArrayList<Business> businesses;
    ListView listBusinesses;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businesses_owned);

        username = (String) getIntent().getExtras().get("username");

        NetworkManager.queryDatabaseForCustomer(username, new MyCallback() {
            @Override
            public void onCallback(Customer customer) {
                //businesses = customer.getBusinesses();
                configureListAdapter();
                setWelcomeMessage(customer.getName());
            }
        });

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