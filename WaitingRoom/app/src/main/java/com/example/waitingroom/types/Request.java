package com.example.waitingroom.types;

import java.io.Serializable;

public class Request implements Serializable {
    private String name;
    private String phoneNumber;
    private String description;
    private String date;

    public Request(String name, String phoneNumber, String description, String date){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
