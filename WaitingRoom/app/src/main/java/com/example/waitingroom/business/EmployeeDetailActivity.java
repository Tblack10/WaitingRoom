package com.example.waitingroom.business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waitingroom.R;
import com.example.waitingroom.administration.AdministrationActivity;
import com.example.waitingroom.administration.BusinessRegistrationActivity;
import com.example.waitingroom.administration.CreateEmployeeActivity;
import com.example.waitingroom.types.Employee;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeeDetailActivity extends AppCompatActivity {
    Employee user;
    Employee employee;
    TextView empName;
    EditText empNameET;
    EditText empPass;
    EditText empPassConf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        user = (Employee) getIntent().getSerializableExtra("user");
        employee = (Employee) getIntent().getSerializableExtra("employee");
        empNameET = findViewById(R.id.editTextName);
        empName = findViewById(R.id.empName);
        empPass = findViewById(R.id.editTextPass);
        empPassConf = findViewById(R.id.editTextPassConf);
        empName.setText(employee.getName());
        empNameET.setText(employee.getName());
    }

    public void update(View view) {

    }

    public void delete(View view) {
        if(employee.isAdmin()){
            Toast.makeText(EmployeeDetailActivity.this, "Cannot delete admin", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Employees");
        myRef.child(employee.getName()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(EmployeeDetailActivity.this, EmployeeListActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}