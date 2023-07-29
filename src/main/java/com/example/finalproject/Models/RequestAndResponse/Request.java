package com.example.finalproject.Models.RequestAndResponse;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class is the data that is sent from client to server.
 * each request has a request type that determines how the server should
 * respond to it. it also contains data that might be needed in order for
 * the server to do the specific request
 *
 *
 */

public class Request implements Serializable {

    private final RequestType requestType;
    private final Object data;

    /**
     * Creates new request that contains the specific request type.
     *
     * @param requestType the type of request being made
     */
    public Request(RequestType requestType, Object data) {
        this.requestType = requestType;
        this.data = data;
    }

    /**
     * returns the data that expected to transfer between server and client
     *
     * @return specific data
     */
    public Object getData() {
        return data;
    }

    /**
     * returns the request type of the given request
     *
     * @return RequestType of the request
     */
    public RequestType getRequestType() {
        return requestType;
    }
}
