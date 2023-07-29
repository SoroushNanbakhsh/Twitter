package com.example.finalproject.timeLine;

import com.example.finalproject.FollowDetails;
import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.RequestType;
import com.example.finalproject.Models.RequestAndResponse.Response;
import com.example.finalproject.Models.RequestAndResponse.ResponseStatus;
import com.example.finalproject.Models.TextMessage;
import com.example.finalproject.Models.Tweet;
import com.example.finalproject.Models.User;
import com.example.finalproject.RequestAndReponse.Communicate;
import com.example.finalproject.Terminal.Menu;
import com.example.finalproject.Terminal.TerminalColor;
import com.example.finalproject.entry.Login;

import java.util.ArrayList;
import java.util.HashMap;

public class Timeline {
    static HashMap<User, TextMessage> newTweets = new HashMap<>();
    static HashMap<TextMessage, User> favstars = new HashMap<>();

    public static void startProcess() throws Exception {
        int i = 1;
         for(User elementUser : newTweets.keySet()) {
             System.out.print(i++ + "|");
             System.out.println(newTweets.get(elementUser));

             int choice = Menu.optionsForFollowingTweet();
             if(choice == 1) {
                 Tweet.like(newTweets.get(elementUser), Login.user);
                 if(elementUser.getMessages().get(elementUser.getMessages().size() - 1).getLikes() == 10) {
                     favstars.put(newTweets.get(elementUser), elementUser);

                     Request request = new Request(RequestType.UPDATE_USER, elementUser);
                     Response response;

                     response = Communicate.communicate(request);

                     if(response.getResponseStatus().equals(ResponseStatus.UPDATE_USER_VALID)) {
                         System.out.println(TerminalColor.ANSI_GREEN + "Success (favstar)" + TerminalColor.ANSI_RESET);
                     }
                     else {
                         System.out.println(TerminalColor.ANSI_RED + "Failed (favstar)" + TerminalColor.ANSI_RESET);
                     }
                 }
                 Menu.actionWasSuccessful();

                 Request request = new Request(RequestType.UPDATE_TWEET, new HashMap<User, TextMessage>().put(elementUser, newTweets.get(elementUser)));
                 Response response;

                 response = Communicate.communicate(request);

                 if(response.getResponseStatus().equals(ResponseStatus.UPDATE_TWEET_VALID)) {
                     System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                 }
                 else {
                     System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                     throw new Exception();
                 }
             }
             else if(choice == 2) {
                 TextMessage textMessage = new TextMessage(Menu.replyOption(), Login.user);
                 Tweet.reply(textMessage, newTweets.get(elementUser), Login.user);
                 Menu.actionWasSuccessful();

                 Request request = new Request(RequestType.UPDATE_TWEET, new HashMap<User, TextMessage>().put(elementUser, newTweets.get(elementUser)));
                 Response response;

                 response = Communicate.communicate(request);

                 if(response.getResponseStatus().equals(ResponseStatus.UPDATE_TWEET_VALID)) {
                     System.out.println(TerminalColor.ANSI_GREEN + "Success (Update Tweet)" + TerminalColor.ANSI_RESET);

                     request = new Request(RequestType.UPDATE_USER, Login.user);

                     response = Communicate.communicate(request);

                     if(response.getResponseStatus().equals(ResponseStatus.UPDATE_USER_VALID)) {
                         System.out.println(TerminalColor.ANSI_GREEN + "Success (Update User)" + TerminalColor.ANSI_RESET);
                     }
                     else {
                         System.out.println(TerminalColor.ANSI_RED + "Failed (Update User)" + TerminalColor.ANSI_RESET);
                         throw new Exception();
                     }
                 }
                 else {
                     System.out.println(TerminalColor.ANSI_RED + "Failed (Update Tweet)" + TerminalColor.ANSI_RESET);
                     throw new Exception();
                 }
             }
             else if(choice == 3) {
                 Login.user.addRetweets(elementUser ,newTweets.get(elementUser));
                 Menu.actionWasSuccessful();

                 Request request = new Request(RequestType.UPDATE_USER, Login.user);
                 Response response;

                 response = Communicate.communicate(request);

                 if(response.getResponseStatus().equals(ResponseStatus.UPDATE_USER_VALID)) {
                     System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                 }
                 else {
                     System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                     throw new Exception();
                 }
             }
             else if(choice == 4) {
                 Login.user.addRetweets(elementUser ,newTweets.get(elementUser));
                 newTweets.get(elementUser).addQuote(Login.user, new TextMessage(Menu.newQuote(), Login.user));
                 Menu.actionWasSuccessful();

                 Request request = new Request(RequestType.UPDATE_TWEET, new HashMap<User, TextMessage>().put(elementUser, newTweets.get(elementUser)));
                 Response response;

                 response = Communicate.communicate(request);

                 if(response.getResponseStatus().equals(ResponseStatus.UPDATE_TWEET_VALID)) {
                     System.out.println(TerminalColor.ANSI_GREEN + "Success (Update Tweet)" + TerminalColor.ANSI_RESET);

                     request = new Request(RequestType.UPDATE_USER, Login.user);

                     response = Communicate.communicate(request);

                     if(response.getResponseStatus().equals(ResponseStatus.UPDATE_USER_VALID)) {
                         System.out.println(TerminalColor.ANSI_GREEN + "Success (Update User)" + TerminalColor.ANSI_RESET);
                     }
                     else {
                         System.out.println(TerminalColor.ANSI_RED + "Failed (Update User)" + TerminalColor.ANSI_RESET);
                         throw new Exception();
                     }
                 }
                 else {
                     System.out.println(TerminalColor.ANSI_RED + "Failed (Update Tweet)" + TerminalColor.ANSI_RESET);
                     throw new Exception();
                 }
             }
             else if(choice == 5) {
                 FollowDetails.unfollow(elementUser);
                 Menu.actionWasSuccessful();
             }
             else {
                 Menu.invalidChoice();
             }
         }

         for(TextMessage elementTextMessage : favstars.keySet()) {
             Menu.showFavstar(elementTextMessage, favstars.get(elementTextMessage));

             int choice = Menu.optionsForFavstarTweets();
             if(choice == 1) {
                 FollowDetails.follow(favstars.get(elementTextMessage));
                 Menu.actionWasSuccessful();
             }
             else if(choice == 2) {
                 Tweet.like(elementTextMessage, Login.user);
                 Menu.actionWasSuccessful();

                 Request request = new Request(RequestType.UPDATE_TWEET, new HashMap<User, TextMessage>().put(favstars.get(elementTextMessage), elementTextMessage));
                 Response response;

                 response = Communicate.communicate(request);

                 if(response.getResponseStatus().equals(ResponseStatus.UPDATE_TWEET_VALID)) {
                     System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                 }
                 else {
                     System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                     throw new Exception();
                 }
             }
             else if(choice == 3) {
                 TextMessage textMessage = new TextMessage(Menu.replyOption(), Login.user);
                 Tweet.reply(textMessage, elementTextMessage, Login.user);
                 Menu.actionWasSuccessful();

                 Request request = new Request(RequestType.UPDATE_TWEET, new HashMap<User, TextMessage>().put(favstars.get(elementTextMessage), elementTextMessage));
                 Response response;

                 response = Communicate.communicate(request);

                 if(response.getResponseStatus().equals(ResponseStatus.UPDATE_TWEET_VALID)) {
                     System.out.println(TerminalColor.ANSI_GREEN + "Success (Update Tweet)" + TerminalColor.ANSI_RESET);

                     request = new Request(RequestType.UPDATE_USER, Login.user);

                     response = Communicate.communicate(request);

                     if(response.getResponseStatus().equals(ResponseStatus.UPDATE_USER_VALID)) {
                         System.out.println(TerminalColor.ANSI_GREEN + "Success (Update User)" + TerminalColor.ANSI_RESET);
                     }
                     else {
                         System.out.println(TerminalColor.ANSI_RED + "Failed (Update User)" + TerminalColor.ANSI_RESET);
                         throw new Exception();
                     }
                 }
                 else {
                     System.out.println(TerminalColor.ANSI_RED + "Failed (Update Tweet)" + TerminalColor.ANSI_RESET);
                     throw new Exception();
                 }
             }
             else if(choice == 4) {
                 Menu.showProfile(favstars.get(elementTextMessage));
             }
             else if(choice == 5) {
                 Login.user.addRetweets(favstars.get(elementTextMessage) ,elementTextMessage);
                 Menu.actionWasSuccessful();

                 Request request = new Request(RequestType.UPDATE_USER, Login.user);
                 Response response;

                 response = Communicate.communicate(request);

                 if(response.getResponseStatus().equals(ResponseStatus.UPDATE_USER_VALID)) {
                     System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                 }
                 else {
                     System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                     throw new Exception();
                 }
             }
             else if(choice == 6) {
                 Login.user.addRetweets(favstars.get(elementTextMessage) ,elementTextMessage);
                 newTweets.get(favstars.get(elementTextMessage)).addQuote(Login.user, new TextMessage(Menu.newQuote(), Login.user));
                 Menu.actionWasSuccessful();

                 Request request = new Request(RequestType.UPDATE_TWEET, new HashMap<User, TextMessage>().put(favstars.get(elementTextMessage), elementTextMessage));
                 Response response;

                 response = Communicate.communicate(request);

                 if(response.getResponseStatus().equals(ResponseStatus.UPDATE_TWEET_VALID)) {
                     System.out.println(TerminalColor.ANSI_GREEN + "Success (Update Tweet)" + TerminalColor.ANSI_RESET);

                     request = new Request(RequestType.UPDATE_USER, Login.user);

                     response = Communicate.communicate(request);

                     if(response.getResponseStatus().equals(ResponseStatus.UPDATE_USER_VALID)) {
                         System.out.println(TerminalColor.ANSI_GREEN + "Success (Update User)" + TerminalColor.ANSI_RESET);
                     }
                     else {
                         System.out.println(TerminalColor.ANSI_RED + "Failed (Update User)" + TerminalColor.ANSI_RESET);
                         throw new Exception();
                     }
                 }
                 else {
                     System.out.println(TerminalColor.ANSI_RED + "Failed (Update Tweet)" + TerminalColor.ANSI_RESET);
                     throw new Exception();
                 }
             }
             else {
                 Menu.invalidChoice();
             }
         }
    }
}

