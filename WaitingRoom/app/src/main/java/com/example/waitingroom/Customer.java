package com.example.waitingroom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer has Businesses which has a queue of Callers
 */
public class Customer implements Serializable {
    private String _name;
    private String _password;
    private String _phoneNumber;
    private ArrayList<Business> _businesses;


    //Constructors
    private Customer(String name, String password) {
        _name = name;
        _password = password;
    }

    public Customer(String name, String password, String phoneNumber, ArrayList<Business> businesses) {
        _name = name;
        _password = password;
        _phoneNumber = phoneNumber;
        _businesses = businesses;

    }

    //Getters and Setters
    public String getName() { return _name; }
    public String getPassword() { return _password; };
    public String getPhoneNumber() { return _phoneNumber; }
    public List<Business> getBusinesses() { return _businesses; }

    public void setName(String name) { _name = name; }
    public void setPassword(String password) { _password = password; };
    public void setPhoneNumber(String phoneNumber) { _phoneNumber = phoneNumber; }
    public void setBusinesses(ArrayList<Business> businesses) { _businesses = businesses; }


    public String toString() { return _name; }


    public static final Customer[] customers_test = {
            new Customer("Jeff Bezos", "1234567890"),
            new Customer("Son of Jeff Bezos", "1234567890"),
            new Customer("Son of Son of Jeff Bezos", "1234567890"),
            new Customer("Son of Son of Son of Jeff Bezos", "1234567890"),
    };

}
