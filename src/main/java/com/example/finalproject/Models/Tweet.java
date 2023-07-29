package com.example.finalproject.Models;


import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.RequestType;
import com.example.finalproject.Models.RequestAndResponse.Response;
import com.example.finalproject.Models.RequestAndResponse.ResponseStatus;
import com.example.finalproject.RequestAndReponse.Communicate;
import com.example.finalproject.Terminal.Menu;
import com.example.finalproject.Terminal.TerminalColor;
import com.example.finalproject.entry.Login;

public abstract class Tweet {

    public static boolean addTweet(TextMessage textMessage){
        Hashtag.tokenize(textMessage);

        Request request = new Request(RequestType.ADD_TWEET, Login.user);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.ADD_TWEET_VALID)) {
            Login.user.addMessage(textMessage);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return response.getResponseStatus().equals(ResponseStatus.ADD_TWEET_VALID);
    }

    public static void reply(TextMessage textMessage, TextMessage repliedTextMessage, User user){
        repliedTextMessage.getReplies().put(textMessage, user);
        user.addReply(textMessage, repliedTextMessage);
    }
    public static void like(TextMessage textMessage, User user) {
        textMessage.getLikesByUsers().add(user);
        int likes = textMessage.getLikes();
        likes++;
        textMessage.setLikes(likes);
    }
}
