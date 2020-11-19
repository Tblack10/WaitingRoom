package com.example.waitingroom.types;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Customers are the people that call in to the app.
 */
@IgnoreExtraProperties
public class Customer implements Serializable {
    private String name;
    private String phoneNumber;


    //Constructors
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {}

    //Getters and Setters
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String toString() { return name; }


    public static final Customer[] customers_test = {
            new Customer("Jeff Bezos", "1234567890"),
            new Customer("Son of Jeff Bezos", "1234567890"),
            new Customer("Son of Son of Jeff Bezos", "1234567890"),
            new Customer("Son of Son of Son of Jeff Bezos", "1234567890"),
    };

}
