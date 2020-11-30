package com.example.waitingroom.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Employee of a business
 */
public class Employee implements Serializable {
    //Name of employee
    private String name;
    //Admin status, true if admin, false if not
    private boolean admin;
    //Employer name
    private String employer;

    //Constructors

    /**
     * Employee constructor
     * @param name of the employee as a string
     * @param employer of the employee as a string
     * @param admin status of the employee, as a boolean
     */
    public Employee(String name, String employer, Boolean admin) {
        this.name = name;
        this.admin = admin;
        this.employer = employer;
    }

    /**
     * Maps a business name to employer
     * @param map, employer as a string, name of employee as a string
     */
    public Employee(HashMap<String, String> map) {
        this.name = map.get("name");
        this.employer = map.get("employer");
    }

    /**
     * Default employee constructor
     */
    public Employee(){}

    //Getters and Setters

    /**
     * Returns the name of employee
     * @return name of the employee as a string
     */
    public String getName() { return this.name; }

    /**
     * Sets the name of the employee
     * @param name of employee as a string
     */
    public void setName(String name) { this.name = name; }

    /**
     * Admin status of employee
     * @param admin as a boolean, true if admin, false if not
     */
    public void setAdmin(boolean admin){
        this.admin = admin;
    }

    /**
     * Determines the admin status of an employee
     * @return status as a boolean, true if admin, false if not
     */
    public boolean isAdmin(){
        return this.admin;
    }

    /**
     * Employee ToString method
     * @return a description of the employee as a string
     */
    public String toString() { return this.name; }

    /**
     * Gets the employer of the employee
     * @return the employer as a string
     */
    public String getEmployer() {
        return this.employer;
    }

    /**
     * Sets the employer of an employee
     * @param employer of an employee as a string
     */
    public void setEmployer(String employer) {
        this.employer = employer;
    }
}
