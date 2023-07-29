package com.example.finalproject.Models;

public class Hashtag {
    public static void tokenize(TextMessage textMessage) {
        String text = textMessage.getTextMessage();

        String[] hashtags = text.split("#");
        for(int i = 1; i < hashtags.length; i++) {
            textMessage.addHashtag(hashtags[i - 1]);
        }
    }
}
