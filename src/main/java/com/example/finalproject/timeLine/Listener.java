package com.example.finalproject.timeLine;

import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.RequestType;
import com.example.finalproject.Models.RequestAndResponse.Response;
import com.example.finalproject.Models.RequestAndResponse.ResponseStatus;
import com.example.finalproject.Models.TextMessage;
import com.example.finalproject.Models.User;
import com.example.finalproject.RequestAndReponse.Communicate;
import com.example.finalproject.Terminal.TerminalColor;
import com.example.finalproject.entry.Login;

import java.util.HashMap;

public class Listener implements Runnable {

    public Listener() {
    }

    @Override
    public void run() {
        while (true) {
            Request request = new Request(RequestType.GET_NEW_TWEETS, Login.user);
            Response response;

            response = Communicate.communicate(request);

            if (response.getResponseStatus().equals(ResponseStatus.GET_NEW_TWEETS_VALID)) {

                if (((HashMap<User, TextMessage>) response.getData()).size() != 0) {
                    for (User element : ((HashMap<User, TextMessage>) response.getData()).keySet()) {
                        if (((HashMap<User, TextMessage>) response.getData()).get(element).getLikes() <= 10) {
                            Timeline.newTweets.put(element, ((HashMap<User, TextMessage>) response.getData()).get(element));
                        } else {
                            Timeline.favstars.put(((HashMap<User, TextMessage>) response.getData()).get(element), element);
                        }
                    }
                }
            } else {
                System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);

                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
