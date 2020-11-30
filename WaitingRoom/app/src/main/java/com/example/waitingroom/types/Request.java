package com.example.waitingroom.types;

import java.io.Serializable;

public class Request implements Serializable {
    //Name person making request
    private String name;
    //Phone number of the user of the request
    private String phoneNumber;
    //Description of the issue requestee is having
    private String description;
    //Date the request occurred
    private String date;

    /**
     * Request Constructor
     * @param name of the requester as a string
     * @param phoneNumber of the requester as a string
     * @param description of the request as a string
     * @param date of the request as a string
     */
    public Request(String name, String phoneNumber, String description, String date){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.date = date;
    }

    /**
     * Default request constructor
     */
    public Request() {}

    /**
     * Gets the name of the requester
     * @return name of the requester as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the requester
     * @param name of the requester as a string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the phone number of the request
     * @return phoneNumber as a string
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phoneNumber of the requester
     * @param phoneNumber of the requester as a string
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Description of the requesters issue
     * @return description as a string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the issue requester is having
     * @param description of the issue as a string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date of issue
     * @return date of issue as a string
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of issue
     * @param date of issue as a string
     */
    public void setDate(String date) {
        this.date = date;
    }

}
