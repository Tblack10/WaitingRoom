package com.example.waitingroom.types;

import java.io.Serializable;

public class RequestWrapper implements Serializable {
    Request request;
    String key;

    public RequestWrapper(String key, Request request) {
        this.request = request;
        this.key = key;
    }
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
