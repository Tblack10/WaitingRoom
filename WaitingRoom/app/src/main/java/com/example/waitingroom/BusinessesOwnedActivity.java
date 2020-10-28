package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businesses_owned);

        username = (String) getIntent().getExtras().get("username");
        NetworkManager.queryDatabaseForCustomer(username, new MyCallback() {
            @Override
            public void onCallback(Customer customer) {
                System.out.println(customer.getID());
            }
        });
    }




}