package com.example.waitingroom.business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.waitingroom.R;
import com.example.waitingroom.administration.CreateEmployeeActivity;
import com.example.waitingroom.types.Employee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Employee List Activity handles the list of employees accessible to admin accounts
 */
public class EmployeeListActivity extends AppCompatActivity {
    Employee user;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration_employee_list);
        user = (Employee) getIntent().getSerializableExtra("user");
        lv = findViewById(R.id.employeeList);
        // setting the onclick listeners for the list
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Employee emp = (Employee) parent.getAdapter().getItem(position);

                Intent intent = new Intent(EmployeeListActivity.this, EmployeeDetailActivity.class);
                intent.putExtra("employee", emp);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Employees");
        Query myQuery = myRef.orderByChild("employer").equalTo(user.getEmployer().toLowerCase());
        myQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Employee> employeeList = new ArrayList<Employee>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Employee temp = ds.getValue(Employee.class);
                    employeeList.add(temp);
                }
                ArrayAdapter<Employee> itemsAdapter = new ArrayAdapter<Employee>(EmployeeListActivity.this, android.R.layout.simple_list_item_1, employeeList);
                lv.setAdapter(itemsAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     * create employee redirects to the createEmployeeActivity
     * @param view the view
     */
    public void createEmployee(View view) {
        Intent intent = new Intent(EmployeeListActivity.this, CreateEmployeeActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}