package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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