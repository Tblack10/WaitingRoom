package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.waitingroom.business.EmployeeLoginActivity;
import com.example.waitingroom.customer.CreateRequestActivity;
import com.example.waitingroom.types.Business;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 * BusinessSearchActivity is our main activity, used to either
 * search for businesses to call or to login/register businesses
 */
public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Business> businessList;
    private ArrayAdapter<Business> businessListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Business value = (Business) adapter.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, CreateRequestActivity.class);
                intent.putExtra("business", value);
                startActivity(intent);
            }
        });
    }

    public void searchForBusiness(View v) {
        EditText searchBar = findViewById(R.id.businessInfoTextEdit);
        final String searchText = searchBar.getText().toString().toLowerCase();
        businessList = new ArrayList<>();
        NetworkManager.getBusinesses(new MyCallback() {
            @Override
            public void onCallback(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getKey().toLowerCase().contains(searchText)) {
                        businessList.add(new Business(ds.child("name").getValue(String.class), ds.child("location").getValue(String.class)));
                    }
                }
                businessListAdapter = new ArrayAdapter<Business>(
                        MainActivity.this, android.R.layout.simple_list_item_1, businessList
                );
                lv.setAdapter(businessListAdapter);
            }
        });



    }

    public void goToBusinessLogin(View v) {
        Intent intent = new Intent(MainActivity.this, EmployeeLoginActivity.class);
        startActivity(intent);
    }
}