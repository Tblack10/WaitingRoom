package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BusinessListActivity extends AppCompatActivity {
    ListView listBusinesses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        //ListView
        configureListAdapter();
        setListItemClickListener();

        //Title
        configureSearchTextView();
    }

    private void configureSearchTextView() {
        String queryParam = (String) getIntent().getExtras().get("query");
        TextView titleText = findViewById(R.id.queryTextView);
        titleText.setText(queryParam);
    }

    private void configureListAdapter() {
        listBusinesses = findViewById(R.id.businessListView);

        ArrayAdapter<Business> businessListAdapter;
        ArrayList<Business> businesses = (ArrayList<Business>) getIntent().getExtras().get("businesses");

        businessListAdapter = new ArrayAdapter<Business>(
                BusinessListActivity.this, android.R.layout.simple_list_item_1, businesses
        );

        listBusinesses.setAdapter(businessListAdapter);
    }

    private void setListItemClickListener() {
        listBusinesses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BusinessListActivity.this, CallRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}