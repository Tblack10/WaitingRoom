package com.example.waitingroom;

import java.util.ArrayList;
import java.util.Arrays;

public class Business {
    private String _name;
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public static final Business[] test_businesses = {
            new Business("Microsoft 2"),
            new Business("Canadian Amazon"),
            new Business("eXXXonMobil"),
            new Business("Laundro-hero"),
    };



    // Each country has a name, description and an image resource
    private Business(String name) {
        _name = name;
    }

    public String getName() { return _name; }
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    public String toString() { return _name; }
}
