package com.example.waitingroom.administration;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.waitingroom.MainActivity;
import com.example.waitingroom.MyCallback;
import com.example.waitingroom.NetworkManager;
import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Employee;
import com.example.waitingroom.types.Request;
import com.example.waitingroom.types.RequestWrapper;
import com.google.firebase.database.DataSnapshot;

/**
 * BusinessDetailActivity displays a business's details such as name, and location.
 */
public class BusinessDetailActivity extends AppCompatActivity {
    private Business business;
    private Employee employee;
    @Override
    /**
     * shows the businesses details
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_business_detail);
        employee = (Employee) getIntent().getSerializableExtra("user");
        NetworkManager.getBusiness(employee.getEmployer(), new MyCallback() {
            @Override
            public void onCallback(DataSnapshot ds) {
                business = new Business(ds.child("name").getValue().toString(), ds.child("location").getValue().toString());
                EditText businessLocation = findViewById(R.id.businessLocation);
                businessLocation.setText(business.getLocation());
            }
        });
    }

    /**
     * update business changes the location of the business to the input location
     * @param view the view
     */
    public void updateBusiness(View view) {
        if (business != null) {
            NetworkManager.getBusiness(employee.getEmployer(), new MyCallback() {
                @Override
                public void onCallback(DataSnapshot ds) {
                    EditText businessLocation = findViewById(R.id.businessLocation);
                    ds.child("location").getRef().setValue(businessLocation.getText().toString());
                }
            });
        }
    }
}