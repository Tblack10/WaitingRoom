package com.example.waitingroom.types;

import java.io.Serializable;

/**
 * Wrapper for the request
 */
public class RequestWrapper implements Serializable {
    //Request
    Request request;
    //Key of Request
    String key;

    /**
     * Request Constructor
     * @param key for the request as a string
     * @param request as s Request
     */
    public RequestWrapper(String key, Request request) {
        this.request = request;
        this.key = key;

    }

    /**
     * Returns the request
     * @return request as a request
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Sets the request
     * @param request as a request
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * Returns the key of request
     * @return key as string
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key as string
     * @param key as string
     */
    public void setKey(String key) {
        this.key = key;
    }
}
