package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        Map<String, Customer> taskMap = new HashMap<>();
        taskMap.put(usernameString, new Customer(usernameString, passwordString, "6049119111", Business.test_businesses));
        myRef.child(usernameString).setValue(taskMap);
    }

}