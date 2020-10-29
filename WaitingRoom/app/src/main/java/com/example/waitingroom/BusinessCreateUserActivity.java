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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_create_user);
    }


    public void createUser(View v) {
        String usernameString;
        String passwordString;

        username = findViewById(R.id.usernameTextField);
        password = findViewById(R.id.passwordTextField);

        usernameString = username.getText().toString();
        passwordString = password.getText().toString();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Customers");

        final Customer customer = new Customer(myRef.push().getKey(), usernameString, passwordString, "6049119111", Business.test_businesses);

//        Map<String, Customer> taskMap = new HashMap<>();
//        taskMap.put(customer.getID(), customer);

        myRef.child(customer.getID()).setValue(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(BusinessCreateUserActivity.this, BusinessesOwnedActivity.class);
                intent.putExtra("username", customer.getName());
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