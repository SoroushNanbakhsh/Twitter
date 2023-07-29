package com.example.finalproject.Models;

import java.io.Serializable;

public class Bio implements Serializable {
    private String text;
    private String location;
    private String websiteURL;

    public Bio(String text, String location, String websiteURL) {
        this.text = text;
        this.location = location;
        this.websiteURL = websiteURL;
    }

    public String getText() {
        return text;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    @Override
    public String toString() {
        return "*" + text + "\n-> " + websiteURL + "\n-> " + location;
    }
}
