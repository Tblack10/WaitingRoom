package com.example.waitingroom.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Request;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * PositionInLIneActivity displays your current position in the caller queue
 */
public class PositionInLineActivity extends AppCompatActivity {
    private Business business;
    private Request request;
    private TextView positionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_position);
        business = (Business) getIntent().getSerializableExtra("business");
        request = (Request) getIntent().getSerializableExtra("request");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        positionText = findViewById(R.id.position);

        Query query = myRef.child("Businesses").child(business.getName().toLowerCase()).child("requests").orderByChild("date").endAt(request.getDate());
        ValueEventListener customerListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int counter = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    counter++;
                }
                positionText.setText("" + counter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query.addValueEventListener(customerListener);
    }
}