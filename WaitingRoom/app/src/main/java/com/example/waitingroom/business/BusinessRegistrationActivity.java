package com.example.waitingroom.business;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Caller;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

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

        Caller admin = new Caller(adminUsername.getText().toString(), businessName.getText().toString(), false);
        Business business = new Business(businessName.getText().toString().toLowerCase(), businessLocation.getText().toString(), admin);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        HashMap<String, Object> busMap= new HashMap<>();
        busMap.put(business.getName(), business);
        DatabaseReference myRef = database.getReference("Businesses");
        myRef.updateChildren(busMap).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BusinessRegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        DatabaseReference myRef2 = database.getReference("Employees");
        HashMap<String, Object> empMap= new HashMap<>();
        empMap.put(admin.getName(), admin);
        myRef2.updateChildren(empMap).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BusinessRegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}