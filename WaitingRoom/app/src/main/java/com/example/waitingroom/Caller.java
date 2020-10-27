package com.example.waitingroom;

import java.io.Serializable;
import java.util.ArrayList;

public class Caller implements Serializable {
    private String _name;
    private String _phoneNum;
    private String _reasonForCall;

    //Constructors
    private Caller(String name, String _phoneNum, String _reasonForCall) {
        _name = name;
    }

    //Getters and Setters
    public String getName() { return _name; }
    public String getPhoneNum() { return _phoneNum; }
    public String getReason() { return _reasonForCall; }

    public void setName(String name) { _name = name; }
    public void setPhoneNum(String phoneNum) { _phoneNum = phoneNum; }
    public void setReason(String reasonForCall) { _reasonForCall = reasonForCall; }


    public String toString() { return _name; }
}
