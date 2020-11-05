package com.example.waitingroom;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Customers are the people that call in to the app.
 */
@IgnoreExtraProperties
public class Customer implements Serializable {
    private String name;
    private String phoneNumber;
    private String reason;


    //Constructors
    private Customer(String name, String phoneNumber, String reason) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.reason = reason;
    }

    public Customer() {}

    //Getters and Setters
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getReason() { return reason; }
    public String toString() { return name; }


    public static final Customer[] customers_test = {
            new Customer("Jeff Bezos", "1234567890", "Who am I?"),
            new Customer("Son of Jeff Bezos", "1234567890", "Where am I?"),
            new Customer("Son of Son of Jeff Bezos", "1234567890", "How do I make more money?"),
            new Customer("Son of Son of Son of Jeff Bezos", "1234567890", "What's the difference between canadian bacon and normal bacon?"),
    };

}
