package com.example.waitingroom.business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.waitingroom.R;
import com.example.waitingroom.types.RequestAdapter;
import com.example.waitingroom.types.Caller;
import com.example.waitingroom.types.Request;
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
public class CallQueueActivity extends AppCompatActivity {
    private ListView lv;
    Caller caller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_queue);
        caller = (Caller) getIntent().getSerializableExtra("user");
        lv = findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Request temp = (Request) parent.getAdapter().getItem(position);
                Intent intent = new Intent(CallQueueActivity.this, CallerDetailActivity.class);
                intent.putExtra("Request", temp);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Businesses").child(caller.getEmployer().toLowerCase()).child("requests");
        Query myQuery = myRef.orderByChild("date");
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Request> requestList = new ArrayList<Request>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Request temp = new Request(ds.child("name").getValue(String.class), ds.child("phoneNumber").getValue(String.class), ds.child("description").getValue(String.class), ds.child("date").getValue(String.class));
                    requestList.add(temp);
                    Log.d("heyo", ds.child("name").getValue(String.class));
                }
                RequestAdapter arrayAdapter = new RequestAdapter(CallQueueActivity.this, requestList);
                lv.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

    }
}