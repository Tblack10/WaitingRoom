package com.example.waitingroom;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private String _name;
    private int _phoneNumber;

    public static final Customer[] customers_test = {
            new Customer("Jeff Bezos", 1234567890),
            new Customer("Son of Jeff Bezos", 1234567890),
            new Customer("Son of Son of Jeff Bezos", 1234567890),
            new Customer("Son of Son of Son of Jeff Bezos", 1234567890),
    };



    // Each country has a name, description and an image resource
    private Customer(String name, int phoneNumber) {
        _name = name;
        _phoneNumber = phoneNumber;

    }

    public String getName() { return _name; }
    public int getPhoneNumber() { return _phoneNumber; }
    public String toString() { return _name; }
}
