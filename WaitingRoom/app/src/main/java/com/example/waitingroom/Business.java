package com.example.waitingroom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Business
 */
public class Business implements Serializable {
    private String name;
    private String location;
    private ArrayList<Customer> queue = new ArrayList<>();
    private ArrayList<Caller> employees = new ArrayList<>();

    //Constructors
    public Business(String name) {
        this.name = name;
    }
    public Business(String name, String location, Caller admin){
        this.name = name;
        this.location = location;
        admin.setAdmin(true);
        employees.add(admin);
    }
    public Business() {}

    //Getters & Setters
    public String getName() { return name; }
    public String getLocation() { return location; }
    public ArrayList<Customer> getQueue() {
        return queue;
    }
    public ArrayList<Caller> getEmployees(){return employees;}


    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { name = location; }
    public void setQueue(ArrayList<Customer> queue) { this.queue = queue; }


    public String toString() { return name; }
    public static final Business[] businesses_test = {
            new Business("Microsoft 2"),
            new Business("Canadian Amazon"),
            new Business("eXXXonMobil"),
            new Business("Apple"),
            new Business("Microsoft"),
            new Business("Apple 2"),
            new Business("Microsoft 3")
    };
}
