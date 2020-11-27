package com.example.waitingroom.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Employee of a business
 */
public class Employee implements Serializable {
    private String name;
    private boolean admin;
    private String employer;

    //Constructors
    public Employee(String name, String employer, Boolean admin) {
        this.name = name;
        this.admin = admin;
        this.employer = employer;
    }
    public Employee(HashMap<String, String> map) {
        this.name = map.get("name");
        this.employer = map.get("employer");
    }
    public Employee(){}

    //Getters and Setters
    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public void setAdmin(boolean admin){
        this.admin = admin;
    }

    public boolean isAdmin(){
        return this.admin;
    }

    public String toString() { return this.name; }

    public String getEmployer() {
        return this.employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }
}
