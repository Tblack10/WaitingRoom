package com.example.waitingroom.administration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Employee;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 *  BusinessRegistrationActivity is used to register a new business within the app
 */
public class BusinessRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_registration);
    }

    /**
     * register business validates the inputs and creates a new business in our database
     * also handles creation and validation of an admin account
     * @param view the view
     */
    public void registerBusiness(View view) {
        TextView businessName = findViewById(R.id.businessNameInput);
        TextView businessLocation = findViewById(R.id.businessLocationInput);
        TextView adminUsername = findViewById(R.id.adminUsernameInput);
        final TextView adminPassword = findViewById(R.id.adminPasswordInput);
        TextView adminPasswordConfirm = findViewById(R.id.adminPasswordConfirmInput);

        if(!adminPassword.getText().toString().equals(adminPasswordConfirm.getText().toString())){
            Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG).show();
            return;
        }

        final Employee admin = new Employee(adminUsername.getText().toString(), businessName.getText().toString().toLowerCase(), true);
        Business business = new Business(businessName.getText().toString().toLowerCase(), businessLocation.getText().toString());
        HashMap<String, Object> busMap = new HashMap<>();
        busMap.put(business.getName(), business);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Businesses");
        myRef.updateChildren(busMap).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("a", "failed to update");
                Toast.makeText(BusinessRegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        final DatabaseReference myRef2 = database.getReference("Employees");
        HashMap<String, Object> empMap= new HashMap<>();
        empMap.put(admin.getName(), admin);
        myRef2.updateChildren(empMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                myRef2.child(admin.getName()).child("password").setValue(adminPassword.getText().toString());
                Intent intent = new Intent(BusinessRegistrationActivity.this, AdministrationActivity.class);
                intent.putExtra("user", admin);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BusinessRegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}