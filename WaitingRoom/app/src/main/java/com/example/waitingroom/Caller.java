package com.example.waitingroom;

import java.util.ArrayList;

public class Caller {
    private String _name;
    private ArrayList<Customer> _callerQueue = new ArrayList<Customer>();

    public static final Caller[] test_businesses = {
            new Caller("Microsoft 2"),
            new Caller("Canadian Amazon"),
            new Caller("eXXXonMobil"),
            new Caller("Laundro-hero"),
    };



    // Each country has a name, description and an image resource
    private Caller(String name) {
        _name = name;

    }

    public String getName() { return _name; }
    public ArrayList<Customer> getCustomers() {
        return _callerQueue;
    }
    public String toString() { return _name; }
}
