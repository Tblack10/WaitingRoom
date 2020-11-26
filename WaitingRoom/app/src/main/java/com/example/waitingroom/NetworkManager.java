package com.example.waitingroom;

import android.util.Log;

import com.example.waitingroom.types.Employee;
import com.example.waitingroom.types.Request;
import com.example.waitingroom.types.RequestWrapper;
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
        Query query = myRef.child("Customers").orderByChild("name").equalTo(username);
        ValueEventListener customerListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    callback.onCallback(ds);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query.addListenerForSingleValueEvent(customerListener);
    }
    /**
     * Queries the database for all businesses
     * @param callback implements MyCallback
     */
    static public void getBusinesses(final MyCallback callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Query query = myRef.child("Businesses");
        ValueEventListener customerListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onCallback(dataSnapshot);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query.addListenerForSingleValueEvent(customerListener);
    }
    /**
     * Queries the database for a single Customer
     * @param username as a string
     * @param callback implements MyCallback
     */
    static public void getRequest(final RequestWrapper request, final Employee employee, final MyCallback callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Query query = myRef.child("Businesses");
        ValueEventListener customerListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                DataSnapshot dataSnapshot = data.child(employee.getEmployer()).child("requests");
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getKey().equals(request.getKey())) {
                        callback.onCallback(ds);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query.addListenerForSingleValueEvent(customerListener);
    }
}


