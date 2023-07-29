package org.example.Client.Models;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class User implements Serializable {
    private ArrayList<User> followers;
    private ArrayList<User> followings;
    private ArrayList<TextMessage> messages;
    private HashMap<User, TextMessage> retweets;
    private HashMap<TextMessage, TextMessage> replies;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String country;
    private String birthDate;
    private File picture;
    private Bio bio;
    private String lastLogInDate;

    public User(String username, String password, String firstName, String lastName, String email, String phoneNumber, String country, String birthDate, File picture) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.birthDate = birthDate;
        this.picture = picture;

        followers = new ArrayList<>();
        followings = new ArrayList<>();
        messages = new ArrayList<>();
        replies = new HashMap<>();
        retweets = new HashMap<>();

        bio = new Bio("", "", "");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public File getPicture() {
        return picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public ArrayList<User> getFollowings() {
        return followings;
    }

    public void addRetweets(User user , TextMessage textMessage) {
        retweets.put(user, textMessage);
    }

    public HashMap<User, TextMessage> getRetweets() {
        return retweets;
    }

    public void addFollower(User user) {
        followers.add(user);
    }

    public void addFollowing(User user) {
        followings.add(user);
    }

    public void removeFollower(User user) {
        followers.remove(user);
    }

    public void removeFollowing(User user) {
        followings.remove(user);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }

    public Bio getBio() {
        return bio;
    }

    public void setBio(Bio bio) {
        this.bio = bio;
    }

    public void addMessage(TextMessage message) {
        messages.add(message);
    }

    public void removeMessage(TextMessage message) {
        messages.remove(message);
    }

    public ArrayList<TextMessage> getMessages() {
        return messages;
    }

    public String getLastLogInDate() {
        return lastLogInDate;
    }

    public void setLastLogInDate(String lastLogInDate) {
        this.lastLogInDate = lastLogInDate;
    }

    public HashMap<TextMessage, TextMessage> getReplies() {
        return replies;
    }

    public void addReply(TextMessage textMessageMain, TextMessage repliedMessage) {
        replies.put(textMessageMain, repliedMessage);
    }


}

