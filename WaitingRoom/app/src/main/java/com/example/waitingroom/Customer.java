package com.example.waitingroom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private String _name;
    private String _password;
    private String _phoneNumber;
    private List<Business> _businesses;
    private Business _business;

    public static final Customer[] customers_test = {
            new Customer("Jeff Bezos", "1234567890"),
            new Customer("Son of Jeff Bezos", "1234567890"),
            new Customer("Son of Son of Jeff Bezos", "1234567890"),
            new Customer("Son of Son of Son of Jeff Bezos", "1234567890"),
    };


    private Customer(String name, String phoneNumber) {
        _name = name;
        _phoneNumber = phoneNumber;
    }

    public Customer(String name, String password, String phoneNumber, List<Business> businesses) {
        _name = name;
        _password = password;
        _phoneNumber = phoneNumber;
        _businesses = businesses;

    }


    public String getName() { return _name; }
    public String getPassword() { return _password; };
    public String getPhoneNumber() { return _phoneNumber; }
    public List<Business> getBusinesses() { return _businesses; }
    public Business getBusiness() { return _business; }


    public void setName(String name) { _name = name; }
    public void setPassword(String password) { _password = password; };
    public void setPhoneNumber(String phoneNumber) { _phoneNumber = phoneNumber; }
    public void setBusinesses(List<Business> businesses) { _businesses = businesses; }
    public void setBusiness(Business business) { _business = business; }


    public String toString() { return _name; }
}
