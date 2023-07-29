package com.example.finalproject;

import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.RequestType;
import com.example.finalproject.Models.RequestAndResponse.Response;
import com.example.finalproject.Models.RequestAndResponse.ResponseStatus;
import com.example.finalproject.Models.User;
import com.example.finalproject.RequestAndReponse.Communicate;
import com.example.finalproject.Terminal.TerminalColor;
import com.example.finalproject.entry.Login;

import java.util.ArrayList;

public class FollowDetails {
    public static void follow(User searchUser){
        ArrayList<User> tmp = new ArrayList<>();

        tmp.add(Login.user);
        tmp.add(searchUser);

        Request request = new Request(RequestType.FOLLOW, tmp);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.FOLLOW_INVALID)) {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
        }
        else {
            System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
        }
    }
    public static void unfollow(User searchUser){
        ArrayList<User> tmp = new ArrayList<>();

        tmp.add(Login.user);
        tmp.add(searchUser);

        Request request = new Request(RequestType.UNFOLLOW, tmp);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.UNFOLLOW_INVALID)) {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
        }
        else {
            System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
        }
    }

    public static void block(User user) throws Exception {
        ArrayList<User> blockUser = new ArrayList<>();
        blockUser.add(Login.user);
        blockUser.add(user);

        Request request = new Request(RequestType.BLOCK, blockUser);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.BLOCK_VALID)) {
            Login.user.blockUser(user);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            throw new Exception();
        }
    }
}
