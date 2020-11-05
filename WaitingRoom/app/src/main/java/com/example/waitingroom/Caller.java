package com.example.waitingroom;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Employee of a business
 */
public class Caller implements Serializable {
    private String _name;
    private String _password;
    private boolean _admin;
    private String _employer;

    //Constructors
    public Caller(String name, String password, String employer) {
        _name = name;
        _employer = employer;
        _password = password;
    }

    public Caller(){};

    //Getters and Setters
    public String getName() { return _name; }

    public void setName(String name) { _name = name; }

    public void setAdmin(boolean admin){
        _admin = admin;
    }

    public boolean isAdmin(){
        return _admin;
    }

    public String getPassword(){
        return _password;
    }
    //remove them from db
    public void fire(){

    }
    public String toString() { return _name; }
}
