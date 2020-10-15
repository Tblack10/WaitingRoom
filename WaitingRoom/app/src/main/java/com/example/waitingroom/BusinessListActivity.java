package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BusinessListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<Business> businessListAdapter;
        ArrayList<Business> businesses = (ArrayList<Business>) getIntent().getExtras().get("businesses");

        businessListAdapter = new ArrayAdapter<Business>(
                this, android.R.layout.simple_list_item_1, businesses
        );

        ListView listBusinesses = getListView();
        listBusinesses.setAdapter(businessListAdapter);
    }
}