package com.example.waitingroom;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Employee of a business
 */
public class Caller implements Serializable {
    private String name;
    private String password;
    private boolean admin;
    private String employer;

    //Constructors
    public Caller(String name, String password, String employer) {
        this.name = name;
        this.employer = employer;
        this.password = password;
    }
    public Caller(HashMap<String, Object> map) {
        this.name = (String) map.get("name");
        Log.e("a", name);
        this.employer = (String) map.get("employer");
        Log.e("a", employer);
        this.password = (String) map.get("password");
        this.admin = (boolean) map.get("admin");
    }
    public Caller(){}

    //Getters and Setters
    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public void setAdmin(boolean admin){
        this.admin = admin;
    }

    public boolean isAdmin(){
        return this.admin;
    }

    public String getPassword(){
        return this.password;
    }
    //remove them from db
    public void fire(){

    }
    public String toString() { return this.name; }

    public String getEmployer() {
        return this.employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }
}
