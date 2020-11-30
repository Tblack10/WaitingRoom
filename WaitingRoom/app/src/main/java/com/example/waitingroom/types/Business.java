package com.example.waitingroom.types;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Business Model Class
 */
public class Business implements Serializable {
    //Name of the business
    private String name;
    //Business Location
    private String location;

    //Constructors

    /**
     * Business Constructor
     * @param name of the business, as a string
     */
    public Business(String name) {
        this.name = name;
    }

    /**
     * Business Constructor
     * @param name of the business, as a string
     * @param location of the business, as a string
     */
    public Business(String name, String location){
        this.name = name;
        this.location = location;
    }

    /**
     * Default constructor
     */
    public Business() {}

    //Getters & Setters

    /**
     * Business name getter
     * @return the business name as a string
     */
    public String getName() { return name; }

    /**
     * Location getter
     * @return the location of the business as a string
     */
    public String getLocation() { return location; }


    /**
     * Sets the name of the business
     * @param name takes the name of the business as a string
     */
    public void setName(String name) { this.name = name; }

    /**
     * Sets the location of the business
     * @param location of the business as a string
     */
    public void setLocation(String location) { name = location; }


    /**
     * Describes the Business
     * @return the name of the business as the string
     */
    public String toString() { return name; }

}
