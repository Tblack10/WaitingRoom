package com.example.waitingroom.business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.waitingroom.MainActivity;
import com.example.waitingroom.MyCallback;
import com.example.waitingroom.NetworkManager;
import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.RequestAdapter;
import com.example.waitingroom.types.Employee;
import com.example.waitingroom.types.Request;
import com.example.waitingroom.types.RequestWrapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * CallQueueActivity displays the list of customers requesting calls
 * clicking on a customer will display their details.
 */
public class RequestQueueActivity extends AppCompatActivity {
    private ListView lv;
    Employee employee;
    Query myQuery;

    /**
     * Generates a list of request objects from the firebase database.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_call_queue);
        employee = (Employee) getIntent().getSerializableExtra("user");
        lv = findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                final RequestWrapper temp = (RequestWrapper) parent.getAdapter().getItem(position);
                NetworkManager.getRequest(temp, employee, new MyCallback() {
                    /**
                     * Deletes the selected request, then redirects to RequestDetailActivity with the request.
                     * @param dataSnapshot
                     */
                    @Override
                    public void onCallback(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().removeValue();
                        Intent intent = new Intent(RequestQueueActivity.this, RequestDetailActivity.class);
                        intent.putExtra("Request", temp);
                        intent.putExtra("user", employee);
                        startActivity(intent);
                    }
                });

            }
        });
        /**
         * Populates the request list.
         */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Businesses").child(employee.getEmployer().toLowerCase()).child("requests");
        myQuery = myRef.orderByChild("date");
        myQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<RequestWrapper> requestList = new ArrayList<RequestWrapper>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Request temp = ds.getValue(Request.class);
                    requestList.add(new RequestWrapper(ds.getKey(), temp));
                }
                RequestAdapter arrayAdapter = new RequestAdapter(RequestQueueActivity.this, requestList);
                lv.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}