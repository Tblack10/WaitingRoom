package com.example.waitingroom.administration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.waitingroom.MainActivity;
import com.example.waitingroom.MyCallback;
import com.example.waitingroom.NetworkManager;
import com.example.waitingroom.R;
import com.example.waitingroom.business.EmployeeListActivity;
import com.example.waitingroom.business.EmployeeLoginActivity;
import com.example.waitingroom.business.RequestDetailActivity;
import com.example.waitingroom.business.RequestQueueActivity;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Employee;
import com.google.firebase.database.DataSnapshot;

public class AdministrationActivity extends AppCompatActivity {
    Employee user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_administration);
        user = (Employee) getIntent().getSerializableExtra("user");
        setWelcomeMessage(user.getName());
    }

    private void setWelcomeMessage(String name) {
        TextView greetingTextView = findViewById(R.id.greetingTextView);
        greetingTextView.setText("Welcome, " + name + "!");
    }

    public void manageEmployees(View view) {
        Intent intent = new Intent(AdministrationActivity.this, EmployeeListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void viewQueue(View view) {
        Intent intent = new Intent(AdministrationActivity.this, RequestQueueActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}