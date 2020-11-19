package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.waitingroom.business.BusinessLoginActivity;
import com.example.waitingroom.customer.CallRequestActivity;
import com.example.waitingroom.types.Business;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * BusinessSearchActivity is our main activity, used to either
 * search for businesses to call or to login/register businesses
 */
public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Business> businessList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_search);
        lv = findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Business value = (Business) adapter.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, CallRequestActivity.class);
                intent.putExtra("business", value);
                startActivity(intent);
            }
        });
    }

    public void searchForBusiness(View v) {
        EditText searchBar = findViewById(R.id.businessInfoTextEdit);
        final String searchText = searchBar.getText().toString().toLowerCase();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Businesses");
        businessList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getKey().toLowerCase().contains(searchText)) {
                        businessList.add(new Business(ds.getKey()));
                    }
                }
                ArrayAdapter<Business> businessListAdapter;
                businessListAdapter = new ArrayAdapter<Business>(
                        MainActivity.this, android.R.layout.simple_list_item_1, businessList
                );
                lv.setAdapter(businessListAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

//        Intent intent = new Intent(BusinessSearchActivity.this, BusinessListActivity.class);
//        intent.putExtra("businesses", filteredBusiness);
//        intent.putExtra("query", searchText);
//        startActivity(intent);
    }

    public void goToBusinessLogin(View v) {
        Intent intent = new Intent(MainActivity.this, BusinessLoginActivity.class);
        startActivity(intent);
    }
}