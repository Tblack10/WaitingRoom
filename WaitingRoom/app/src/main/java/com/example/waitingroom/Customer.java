package com.example.waitingroom;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Customers are the people that call in to the app.
 */
@IgnoreExtraProperties
public class Customer implements Serializable {
    private String _name;
    private String _phoneNumber;
    private String _reason;


    //Constructors
    private Customer(String name, String phoneNumber, String reason) {
        _name = name;
        _phoneNumber = phoneNumber;
        _reason = reason;
    }

    public Customer() {};

    //Getters and Setters
    public String getName() { return _name; }
    public String getPhoneNumber() { return _phoneNumber; }

    public void setName(String name) { _name = name; }
    public void setPhoneNumber(String phoneNumber) { _phoneNumber = phoneNumber; }

    public String getReason() { return _reason; }
    public String toString() { return _name; }


    public static final Customer[] customers_test = {
            new Customer("Jeff Bezos", "1234567890", "Who am I?"),
            new Customer("Son of Jeff Bezos", "1234567890", "Where am I?"),
            new Customer("Son of Son of Jeff Bezos", "1234567890", "How do I make more money?"),
            new Customer("Son of Son of Son of Jeff Bezos", "1234567890", "What's the difference between canadian bacon and normal bacon?"),
    };

}
