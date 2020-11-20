package com.example.waitingroom.business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waitingroom.R;
import com.example.waitingroom.administration.AdministrationActivity;
import com.example.waitingroom.administration.BusinessRegistrationActivity;
import com.example.waitingroom.types.Employee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * BusinessLoginActivity is a login page for employees, also contains
 * register button for businesses.
 */
public class EmployeeLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_employee_login);
    }
    public void auth(String userName, String password) {

    }

    public void login(View v) {
        TextView nameField = findViewById(R.id.name);
        TextView passwordField = findViewById(R.id.password);
        final String username = nameField.getText().toString();
        final String password = passwordField.getText().toString();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Employees");
        Query myQuery = myRef.orderByChild("name").equalTo(username);
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot datasnapshot) {
                DataSnapshot user = datasnapshot.child(username);
                if(user.getValue() == null) {
                    Toast.makeText(EmployeeLoginActivity.this, "No Such User!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (user.child("name").getValue(String.class).equals(username) && user.child("password").getValue(String.class).equals(password)) {
                    Employee employee = user.getValue(Employee.class);
                    Intent intent = (employee.isAdmin() ? new Intent(EmployeeLoginActivity.this, AdministrationActivity.class) : new Intent(EmployeeLoginActivity.this, RequestQueueActivity.class));
                    intent.putExtra("user", employee);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

    }

    public void register(View v) {
        Intent intent = new Intent(EmployeeLoginActivity.this, BusinessRegistrationActivity.class);
        startActivity(intent);
    }
}