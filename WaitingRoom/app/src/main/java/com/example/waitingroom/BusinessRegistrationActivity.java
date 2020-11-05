package com.example.waitingroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BusinessRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_registration);
    }

    public void registerBusiness(View view) {
        TextView businessName = findViewById(R.id.businessNameInput);
        TextView businessLocation = findViewById(R.id.businessLocationInput);
        TextView adminUsername = findViewById(R.id.adminUsernameInput);
        TextView adminPassword = findViewById(R.id.adminPasswordInput);
        TextView adminPasswordConfirm = findViewById(R.id.adminPasswordConfirmInput);

        if(!adminPassword.getText().toString().equals(adminPasswordConfirm.getText().toString())){
            Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG).show();
            return;
        }

        Caller admin = new Caller(adminUsername.getText().toString(), adminPassword.getText().toString(), businessName.getText().toString());
        Business business = new Business(businessName.getText().toString(), businessLocation.getText().toString(), admin);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Businesses").push();
        myRef.setValue(business).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BusinessRegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        DatabaseReference myRef2 = database.getReference("Employees").push();
        myRef2.setValue(admin).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BusinessRegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}