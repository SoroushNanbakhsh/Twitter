package com.example.finalproject;

import com.example.finalproject.Models.User;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tweet {
    private String textMessage;
    private HashMap<User, Tweet> quotes = new HashMap<>();
    private HashMap<Tweet, User> replies = new HashMap<>();
    private ArrayList<User> likesByUsers = new ArrayList<>();
    private ArrayList<String> hashtags = new ArrayList<>();

    private int likes = 0;
    private int retweets = 0;
    private int countOfQuotes = 0;
    private Date date;
    private User user;
    private Image image;

    public Tweet(User user, String textMessage, Image image) {
        this.textMessage = textMessage;
        this.user = user;
        this.image = image;
    }

    public Tweet(User user, String textMessage) {
        this.user = user;
        this.textMessage = textMessage;
    }

    public static boolean permission(Tweet textMessage) {
        return textMessage.textMessage.length() <= 280;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void userLiked(User user) {
        likesByUsers.add(user);
    }

    public String getTextMessage() {
        return textMessage;
    }

    public HashMap<Tweet, User> getReplies() {
        return replies;
    }

    public ArrayList<User> getLikesByUsers() {
        return likesByUsers;
    }

    public HashMap<User, Tweet> getQuotes() {
        return quotes;
    }

    public void setQuotes(HashMap<User, Tweet> quotes) {
        this.quotes = quotes;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public Date getDate() {
        return date;
    }

    public void addHashtag(String string) {
        hashtags.add(string);
    }

    public ArrayList<String> getHashtags() {
        return hashtags;
    }

    public User getUser() {
        return user;
    }

    public Image getImage() {
        return image;
    }

    public int getCountOfQuotes() {
        return countOfQuotes;
    }

    public void setCountOfQuotes(int countOfQuotes) {
        this.countOfQuotes = countOfQuotes;
    }
}
