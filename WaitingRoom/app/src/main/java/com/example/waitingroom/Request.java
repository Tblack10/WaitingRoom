package com.example.waitingroom;

import java.io.Serializable;

public class Request implements Serializable {
    private Customer customer;
    private Business business;
    private String title;
    private String description;

    public Request(Customer customer, Business business, String title, String description){
        this.customer = customer;
        this.business = business;
        this.title = title;
        this.description = description;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static final Request[] requests_test = {
            new Request(Customer.customers_test[0], Business.businesses_test[0], "My toilet is sucking me into the earth?", "Don't call the police please"),
            new Request(Customer.customers_test[1], Business.businesses_test[1], "My dog is eating my hand", "All I did was try to bite it"),
            new Request(Customer.customers_test[2], Business.businesses_test[2], "How come the sky is blue?", "My mom says it's chemtrails"),
            new Request(Customer.customers_test[3], Business.businesses_test[3], "My hand is stuck in a light bulb", "I can't turn it off, it feels too good.")
    };
}
