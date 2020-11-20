package com.example.waitingroom.types;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Business
 */
public class Business implements Serializable {
    private String name;
    private String location;
    private ArrayList<Employee> employees = new ArrayList<>();

    //Constructors
    public Business(String name) {
        this.name = name;
    }
    public Business(String name, String location, Employee admin){
        this.name = name;
        this.location = location;
        admin.setAdmin(true);
        employees.add(admin);
    }
    public Business() {}

    //Getters & Setters
    public String getName() { return name; }
    public String getLocation() { return location; }
    public ArrayList<Employee> getEmployees(){return employees;}


    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { name = location; }


    public String toString() { return name; }

}
