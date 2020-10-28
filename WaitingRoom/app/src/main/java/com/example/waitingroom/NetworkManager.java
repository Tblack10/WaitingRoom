package com.example.waitingroom;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NetworkManager{

    /**
     * Queries the database for a single Customer
     * @param username as a string
     * @param callback implements MyCallback
     */
    static public void queryDatabaseForCustomer(String username, final MyCallback callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Query query = myRef.child("Customers").orderByChild("name").equalTo("UserOne");

        ValueEventListener customerListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    callback.onCallback(ds.getValue(Customer.class));
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting User failed, log a message

            }
        };

        query.addListenerForSingleValueEvent(customerListener);
    }
}


