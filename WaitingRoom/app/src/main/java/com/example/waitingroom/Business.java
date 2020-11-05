package com.example.waitingroom;

import android.telecom.Call;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Business
 */
public class Business implements Serializable {
    private String _name;
    private String _location;
    private ArrayList<Customer> _queue = new ArrayList();
    private ArrayList<Caller> _employees = new ArrayList();

    //Constructors
    public Business(String name) {
        _name = name;
    }
    public Business(String name, String location, Caller admin){
        _name = name;
        _location = location;
        admin.setAdmin(true);
        _employees.add(admin);
    }
    public Business() {};

    //Getters & Setters
    public String getName() { return _name; }
    public String getLocation() { return _location; }
    public ArrayList<Customer> getQueue() {
        return _queue;
    }
    public ArrayList<Caller> getEmployees(){return _employees;}


    public void setName(String name) { _name = name; }
    public void setLocation(String location) { _name = location; }
    public void setQueue(ArrayList queue) { _queue = queue; }


    public String toString() { return _name; }

    public static final ArrayList<Business> test_businesses =  new ArrayList<Business>( Arrays.asList(
            new Business("Microsoft 2"),
            new Business("Canadian Amazon"),
            new Business("eXXXonMobil"),
            new Business("Apple"),
            new Business("Microsoft"),
            new Business("Apple 2"),
            new Business("Microsoft 3") ));
}
