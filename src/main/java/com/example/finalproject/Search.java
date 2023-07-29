package com.example.finalproject;

import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.RequestType;
import com.example.finalproject.Models.RequestAndResponse.Response;
import com.example.finalproject.Models.RequestAndResponse.ResponseStatus;
import com.example.finalproject.Models.User;
import com.example.finalproject.RequestAndReponse.Communicate;
import com.example.finalproject.Terminal.Menu;
import com.example.finalproject.Terminal.TerminalColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Search {
    public static void startProcess() throws Exception {
        Scanner scanner = new Scanner(System.in);

        String searchItem = scanner.nextLine();
        ArrayList<User> result = null;
        try {
            result = searchWithItem(searchItem);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(result.size() == 0) {
            Menu.notFound();
        }
        else {
            int i = 1;

            for(User elementUser : result) {
                System.out.println(i + "|" + elementUser);
                i++;
            }

            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            System.out.print("Enter index: ");
            int index = scanner.nextInt();

            if(index <= i - 1 && index >= 0) {
                Menu.showProfile(result.get(index - 1));


            }
            else {
                Menu.invalidChoice();
            }
        }
    }

    private static ArrayList<User> searchWithItem(String searchItem) throws Exception {
        Request request = new Request(RequestType.SEARCH_IN_USERS, searchItem);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.SEARCH_IN_USERS_INVALID)) {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);

            throw new Exception();
        }
        else {
            System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);

            return (ArrayList<User>) response.getData();
        }
    }
//    private static HashMap<TextMessage, User> searchTweet(String searchInput){
//        HashMap<TextMessage, User> result = new HashMap<>();
//        StringTokenizer stringTokenizer = new StringTokenizer(searchInput, "#");
//        String searchingString = null;
//        for (int i = 0; i < 2; i++) {
//            searchingString = stringTokenizer.nextToken();
//        }
//        ArrayList<User> users = ServerAccessor.getUsers();
//        HashMap<User, ArrayList<TextMessage>> tweets = Tweet.getTweets();
//        assert users != null;
//        for (User userCounter: users) {
//            if(tweets.containsKey(userCounter)){
//                ArrayList<TextMessage> textMessages = tweets.get(userCounter);
//                for (TextMessage textMessageCounter: textMessages) {
//                    if(textMessageCounter.getTextMessage().contains(searchingString)){
//                        result.put(textMessageCounter, userCounter);
//                    }
//                }
//            }
//        }
//        return result;
//    }
}
