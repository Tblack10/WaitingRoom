package com.example.waitingroom.types;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Business
 */
public class Business implements Serializable {
    private String name;
    private String location;

    //Constructors
    public Business(String name) {
        this.name = name;
    }
    public Business(String name, String location){
        this.name = name;
        this.location = location;
    }
    public Business() {}

    //Getters & Setters
    public String getName() { return name; }
    public String getLocation() { return location; }


    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { name = location; }


    public String toString() { return name; }

}
