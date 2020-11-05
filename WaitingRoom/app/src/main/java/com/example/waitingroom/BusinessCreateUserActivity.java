package com.example.waitingroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * BusinessCreateUserActivity is for registering new employees to a business
 */
public class BusinessCreateUserActivity extends AppCompatActivity {
    TextView username;
    TextView password;
    TextView passwordConfirm;
    Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_create_user);
        business = (Business) getIntent().getSerializableExtra("business");
    }


    public void createUser(View v) {
        String usernameString;
        String passwordString;
        String confirmPasswordString;

        username = findViewById(R.id.usernameTextField);
        password = findViewById(R.id.passwordTextField);
        passwordConfirm = findViewById(R.id.confirmPasswordTextField);

        usernameString = username.getText().toString();
        passwordString = password.getText().toString();
        confirmPasswordString = passwordConfirm.getText().toString();

        //Validation
        //TODO make sure username is unique in db
        if(!passwordString.equals(confirmPasswordString)){
            Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Businesses").child(business.getName()).child("Employees");

        final Caller employee = new Caller(usernameString, passwordString, business.getName());

        Map<String, Object> empMap = new HashMap<>();
        empMap.put(employee.getName(), employee);

        myRef.child(business.getName()).child("Employees").child(employee.getName()).updateChildren(empMap).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BusinessCreateUserActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            };
        });

        DatabaseReference myRef2 = database.getReference("Employees");
        myRef2.updateChildren(empMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(BusinessCreateUserActivity.this, BusinessesOwnedActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BusinessCreateUserActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            };
        });
    }

}