package org.example.Client.Utils;

import org.example.Client.Models.RequestAndResponse.Request;
import org.example.Client.Models.RequestAndResponse.RequestType;
import org.example.Client.Models.RequestAndResponse.Response;
import org.example.Client.Models.RequestAndResponse.ResponseStatus;
import org.example.Client.Models.User;
import org.example.Client.RequestAndReponse.Communicate;
import org.example.Client.Terminal.TerminalColor;
import org.example.Client.entry.Login;

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
}
