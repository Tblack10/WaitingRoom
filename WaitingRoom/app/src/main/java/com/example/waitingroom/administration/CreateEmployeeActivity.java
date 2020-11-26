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
    Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_create_employee);
        business = (Business) getIntent().getSerializableExtra("business");
        Log.e("a", business.toString());
    }


    public void createUser(View v) {
        final String usernameString;
        String passwordString;
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
                    if (Objects.requireNonNull(ds.getKey()).toLowerCase().contains(usernameString)) {
                        Toast.makeText(getApplicationContext(), "Username already taken!", Toast.LENGTH_LONG).show();
                        return;
                    } else{
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Businesses").child(business.getName()).child("Employees");

                        final Employee employee = new Employee(usernameString, business.getName(), false);

                        Map<String, Object> empMap = new HashMap<>();
                        empMap.put(employee.getName(), employee);

                        myRef.child(business.getName()).child("Employees").child(employee.getName()).updateChildren(empMap).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CreateEmployeeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            };
                        });

                        DatabaseReference myRef2 = database.getReference("Employees");
                        myRef2.updateChildren(empMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent(CreateEmployeeActivity.this, AdministrationActivity.class);
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