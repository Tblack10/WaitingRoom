package com.example.waitingroom.administration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.waitingroom.customer.CreateRequestActivity;
import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;

import java.util.ArrayList;

/**
 * BusinessListActivity displays all businesses contained in the intent
 * in a list format. Businesses are clickable to go to their respective
 * request call page.
 */
public class BusinessListActivity extends AppCompatActivity {
    ListView listBusinesses;
    ArrayList<Business> businesses;
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
        businesses = (ArrayList<Business>) getIntent().getExtras().get("businesses");

        businessListAdapter = new ArrayAdapter<Business>(
                BusinessListActivity.this, android.R.layout.simple_list_item_1, businesses
        );

        listBusinesses.setAdapter(businessListAdapter);
    }

    private void setListItemClickListener() {
        listBusinesses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BusinessListActivity.this, CreateRequestActivity.class);
                intent.putExtra("businessName", businesses.get(i).getName());

                startActivity(intent);
            }
        });
    }
}