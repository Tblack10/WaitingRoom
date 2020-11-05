package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * BusinessLoginActivity is a login page for employees, also contains
 * register button for businesses.
 */
public class BusinessLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);
    }

    public void login(View v) {
        final TextView nameField = findViewById(R.id.name);
        final TextView passwordField = findViewById(R.id.password);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Employees");
        Log.d("ayyyyy", "LMAO");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ayyyyy", "LMAO");
                HashMap<String, Object> empMap = (HashMap<String, Object>) dataSnapshot.getValue();
                if (empMap.containsKey(passwordField.getText().toString())) {
                    Caller check = new Caller((HashMap<String,String>)empMap.get(passwordField.getText().toString()));
                    Log.d("heyo", check.getName());
                    if(check.getPassword().equals(passwordField.getText().toString())) {
                        Intent intent = new Intent(BusinessLoginActivity.this, CallQueueActivity.class);
                        intent.putExtra("NAME_ID", "" + nameField.getText().toString());
                        intent.putExtra("PASSWORD_ID", "" + passwordField.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(BusinessLoginActivity.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void register(View v) {
        Intent intent = new Intent(BusinessLoginActivity.this, BusinessRegistrationActivity.class);
        startActivity(intent);
    }
}