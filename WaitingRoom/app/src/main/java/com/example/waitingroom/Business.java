package com.example.waitingroom;

import android.telecom.Call;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Business implements Serializable {
    private String _name;
    private ArrayList<Caller> queue = new ArrayList<Caller>();


    public static final Business[] test_businesses = {
            new Business("Microsoft 2"),
            new Business("Canadian Amazon"),
            new Business("eXXXonMobil"),
            new Business("Apple"),
            new Business("Microsoft"),
            new Business("Apple 2"),
            new Business("Microsoft 3"),
    };



    public Business(String name) {
        _name = name;
    }

    public String getName() { return _name; }
    public ArrayList<Caller> getQueue() {
        return queue;
    }

    public String toString() { return _name; }
}
