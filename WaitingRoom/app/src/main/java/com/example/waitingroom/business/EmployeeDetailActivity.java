package com.example.waitingroom.business;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waitingroom.MainActivity;
import com.example.waitingroom.MyCallback;
import com.example.waitingroom.NetworkManager;
import com.example.waitingroom.R;

import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Employee;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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
        final String empPassString = empPass.getText().toString();
        if(!empPassString.equals(empPassConf.getText().toString())){
            Toast.makeText(EmployeeDetailActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Employees");

        NetworkManager.getEmployees(new MyCallback() {
            @Override
            public void onCallback(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getKey().toLowerCase().contains(empNameET.getText().toString())) {
                        Toast.makeText(EmployeeDetailActivity.this, "Username already taken!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                myRef.child(employee.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = empPassString;
                        if (empPassString.trim().isEmpty()) {
                            temp = dataSnapshot.child("password").getValue(String.class);
                        }
                        final String finalTemp = temp;
                        myRef.child(employee.getName()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                employee.setName(empNameET.getText().toString());
                                myRef.child(employee.getName()).setValue(employee);
                                myRef.child(employee.getName()).child("password").setValue(finalTemp);
                                empName.setText(employee.getName());
                                Intent intent = new Intent(EmployeeDetailActivity.this, EmployeeListActivity.class);
                                intent.putExtra("user", user);
                                startActivity(intent);
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
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