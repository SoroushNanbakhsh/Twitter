package com.example.finalproject.Models;

import java.util.HashMap;

public class Chat {
    private User user1;
    private User user2;
    private HashMap<User, String> chat;

    public Chat(User user1, User user2, HashMap<User, String> chat) {
        this.user1 = user1;
        this.user2 = user2;
        this.chat = chat;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public HashMap<User, String> getChat() {
        return chat;
    }

    public void setChat(HashMap<User, String> chat) {
        this.chat = chat;
    }
}
