package com.example.finalproject.Models;

import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.Response;

import java.io.Serializable;

public class Command implements Serializable {
    private String command;
    private Request request;
    private Response response;
    private String date;


    public Command(String command, Request request, Response response, String date) {
        this.command = command;
        this.request = request;
        this.response = response;
        this.date = date;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return "Command: " + command + " | " +
                "Request: " + request + " | " +
                "Response: " + response + " | " +
                "Date: " + date;
    }
}
