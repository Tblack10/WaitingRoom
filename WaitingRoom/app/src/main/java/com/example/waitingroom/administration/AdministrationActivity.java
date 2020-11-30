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

/**
 * Admin activity is the landing page for admin accounts
 */
public class AdministrationActivity extends AppCompatActivity {
    Employee user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_administration);
        user = (Employee) getIntent().getSerializableExtra("user");
        setWelcomeMessage(user.getName());
    }

    /**
     * Sets the welcome message to the specified string
     * @param name the welcome message
     */
    private void setWelcomeMessage(String name) {
        TextView greetingTextView = findViewById(R.id.greetingTextView);
        greetingTextView.setText("Welcome, " + name + "!");
    }

    /**
     * businessDetail is called to send the user to the businessDetail activity
     * @param view the view
     */
    public void businessDetail(View view) {
        Intent intent = new Intent(AdministrationActivity.this, BusinessDetailActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * Manage Employees sends the user to the manage Employees page
     * @param view the view
     */
    public void manageEmployees(View view) {
        Intent intent = new Intent(AdministrationActivity.this, EmployeeListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * View Queue sends the user to the requestQueueActivity
     * @param view the view
     */
    public void viewQueue(View view) {
        Intent intent = new Intent(AdministrationActivity.this, RequestQueueActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * GoToMainMenu logs the user out and sends them back to the main activity
     * @param view the view
     */
    public void goToMainMenu(View view) {
        Intent intent = new Intent(AdministrationActivity.this, MainActivity.class);
        startActivity(intent);
    }
}