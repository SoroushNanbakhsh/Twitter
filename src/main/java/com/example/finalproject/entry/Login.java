package com.example.finalproject.entry;

import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.RequestType;
import com.example.finalproject.Models.RequestAndResponse.Response;
import com.example.finalproject.Models.RequestAndResponse.ResponseStatus;
import com.example.finalproject.RequestAndReponse.Communicate;
import com.example.finalproject.Terminal.Menu;
import com.example.finalproject.Terminal.TerminalColor;
import com.example.finalproject.Models.User;
import com.example.finalproject.timeLine.Listener;

public class Login {
    public static User user;
    public static boolean loginProcess(String username, String password) {
        // TODO add token which user can log in without entering password and username

        String[] usernamePassword = new String[2];
        usernamePassword[0] = username;
        usernamePassword[1] = password;

        //String[] usernamePassword = Menu.printLoginPage();

        user = new User(null, null, null, null, null, null, null, null,null);

        user.setUsername(usernamePassword[0]);
        user.setPassword(usernamePassword[1]);

        Request request = new Request(RequestType.LOG_IN, user);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.LOG_IN_INVALID)) {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);

            return false;
        }
        else if(response.getResponseStatus().equals(ResponseStatus.LOG_IN_VALID)) {

            Thread thread = new Thread(new Listener());
            thread.start();

            user = (User) response.getData();
        }

        return true;
    }
}
