package com.example.waitingroom.business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.waitingroom.MyCallback;
import com.example.waitingroom.NetworkManager;
import com.example.waitingroom.R;
import com.example.waitingroom.administration.AdministrationActivity;
import com.example.waitingroom.administration.CreateEmployeeActivity;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Employee;
import com.google.firebase.database.DataSnapshot;

public class EmployeeListActivity extends AppCompatActivity {
    Employee user;
    Business business;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_employee_list);
        user = (Employee) getIntent().getSerializableExtra("user");
        NetworkManager.getBusinesses(new MyCallback() {
            @Override
            public void onCallback(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getKey().toLowerCase().contains(user.getEmployer())) {
                        business = new Business(ds.child("name").getValue(String.class));
                        Log.e("a", business.toString());
                    }
                }
            }
        });
    }

    public void createEmployee(View view) {
        Intent intent = new Intent(EmployeeListActivity.this, CreateEmployeeActivity.class);
        intent.putExtra("business", business);
        startActivity(intent);
    }
}