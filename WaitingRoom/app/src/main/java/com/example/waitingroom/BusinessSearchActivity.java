package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BusinessSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_search);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    public void searchForBusiness(View v) {
        ArrayList<Business> filteredBusiness = new ArrayList<Business>();
        EditText searchBar = findViewById(R.id.businessInfoTextEdit);
        String searchText = searchBar.getText().toString();

        for (Business b: Business.test_businesses) {
            if (b.getName().toUpperCase().contains(searchText.toUpperCase())) {
                filteredBusiness.add(b);
            }
        }

        Intent intent = new Intent(BusinessSearchActivity.this, BusinessListActivity.class);
        intent.putExtra("businesses", filteredBusiness);
        intent.putExtra("query", searchText);
        startActivity(intent);
    }

    public void goToBusinessLogin(View v) {
        Intent intent = new Intent(BusinessSearchActivity.this, BusinessLoginActivity.class);
        startActivity(intent);
    }
}