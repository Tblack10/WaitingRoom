package com.example.waitingroom.administration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waitingroom.MainActivity;
import com.example.waitingroom.MyCallback;
import com.example.waitingroom.NetworkManager;
import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Employee;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * BusinessCreateUserActivity is for registering new employees to a business
 */
public class CreateEmployeeActivity extends AppCompatActivity {
    TextView username;
    TextView password;
    TextView passwordConfirm;
    Employee user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_create_employee);
        user = (Employee) getIntent().getSerializableExtra("user");

    }


    public void createUser(View v) {
        final String usernameString;
        final String passwordString;
        String confirmPasswordString;

        username = findViewById(R.id.usernameTextField);
        password = findViewById(R.id.passwordTextField);
        passwordConfirm = findViewById(R.id.confirmPasswordTextField);

        usernameString = username.getText().toString();
        passwordString = password.getText().toString();
        confirmPasswordString = passwordConfirm.getText().toString();
        //Validation

        if(!passwordString.equals(confirmPasswordString)){
            Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG).show();
            return;
        }
        NetworkManager.getEmployees(new MyCallback() {
            @Override
            public void onCallback(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getKey().toLowerCase().contains(usernameString)) {
                        Toast.makeText(getApplicationContext(), "Username already taken!", Toast.LENGTH_LONG).show();
                        return;
                    } else{
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final DatabaseReference myRef = database.getReference("Employees");

                        final Employee employee = new Employee(usernameString, user.getEmployer().toLowerCase(), false);
                        Map<String, Object> empMap = new HashMap<>();
                        empMap.put(employee.getName(), employee);
                        Log.e("a", empMap.toString());
                        myRef.child(employee.getName()).updateChildren(empMap).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CreateEmployeeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            };
                        });

                        myRef.updateChildren(empMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                myRef.child(employee.getName()).child("password").setValue(passwordString);
                                Intent intent = new Intent(CreateEmployeeActivity.this, AdministrationActivity.class);
                                intent.putExtra("user", user);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CreateEmployeeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            };
                        });
                    }
                }
            }
        });
    }
}