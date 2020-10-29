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

/**
 * CallQueueActivity displays the list of customers requesting calls
 * clicking on a customer will display their details.
 */
public class CallQueueActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i = 1;
        ArrayList<String> tempNames = new ArrayList<String>();
        for (int index = 0; index < Customer.customers_test.length; index++) {
            tempNames.add(Customer.customers_test[index].getName());
        }
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempNames);

        ListView list = getListView();
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(CallQueueActivity.this, CallerDetailActivity.class);
                intent.putExtra("Customer", Customer.customers_test[position]);
                Log.d("heyo", "what is up");
                Log.d("heyo", "" + position);
                Log.d("heyo", Customer.customers_test[position].getName());
                startActivity(intent);
            }
        });

    }
}