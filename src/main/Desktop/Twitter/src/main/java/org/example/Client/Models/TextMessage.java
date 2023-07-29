package org.example.Client.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TextMessage{
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private String textMessage;
    private HashMap<User, TextMessage> quotes = new HashMap<>();
    private HashMap<TextMessage, User> replies = new HashMap<>();
    private ArrayList<User> likesByUsers = new ArrayList<>();
    private ArrayList<String> hashtags = new ArrayList<>();

    private int likes = 0;
    private int retweets = 0;
    private String date;

    public TextMessage(String textMessage) {
        this.textMessage = textMessage;

        date = formatter.format(new Date());
    }

    public static boolean permission(TextMessage textMessage) {
        return textMessage.textMessage.length() <= 280;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        likes = likes;
    }

    public void userLiked(User user) {
        likesByUsers.add(user);
    }

    public String getTextMessage() {
        return textMessage;
    }

    public HashMap<TextMessage, User> getReplies() {
        return replies;
    }

    public ArrayList<User> getLikesByUsers() {
        return likesByUsers;
    }

    public HashMap<User, TextMessage> getQuotes() {
        return quotes;
    }

    public void addQuote(User user, TextMessage textMessage) {
        quotes.put(user, textMessage);
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public void addHashtag(String string) {
        hashtags.add(string);
    }

    public ArrayList<String> getHashtags() {
        return hashtags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
