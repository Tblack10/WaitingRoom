package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * CallQueueActivity displays the list of customers requesting calls
 * clicking on a customer will display their details.
 */
public class CallQueueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_queue);
        int i = 1;

        RequestAdapter arrayAdapter;

        ArrayList<Request> requestList = new ArrayList<Request>();
        Collections.addAll(requestList, Request.requests_test);
        arrayAdapter = new RequestAdapter(this, requestList);

        ListView list = findViewById(R.id.list);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Request temp = (Request) parent.getAdapter().getItem(position);
                Intent intent = new Intent(CallQueueActivity.this, CallerDetailActivity.class);
                intent.putExtra("Request", temp);
                startActivity(intent);
            }
        });

    }
}