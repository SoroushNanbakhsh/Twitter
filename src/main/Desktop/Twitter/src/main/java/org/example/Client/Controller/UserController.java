package org.example.Client.Controller;

import org.example.Client.Models.RequestAndResponse.Request;
import org.example.Client.Models.RequestAndResponse.RequestType;
import org.example.Client.Models.RequestAndResponse.Response;
import org.example.Client.Models.RequestAndResponse.ResponseStatus;
import org.example.Client.RequestAndReponse.Communicate;
import org.example.Client.Terminal.TerminalColor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {
    public static boolean userUsernameChecker(String username) {
        Request request = new Request(RequestType.CHECK_USERNAME, username);
        Response response;

        response = Communicate.communicate(request);

        return response.getResponseStatus().equals(ResponseStatus.VALID_USERNAME);
    }

    public static boolean userPasswordChecker(String password) {
        if(password.length() < 8) {
            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            System.out.println(TerminalColor.ANSI_RED + "Password must contain at least 8 characters!");
            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

            return false;
        }
        else {
            char ch;
            boolean capitalFlag = false;
            boolean lowerCaseFlag = false;

            for(int i=0;i < password.length();i++) {
                ch = password.charAt(i);

                if (Character.isUpperCase(ch)) {
                    capitalFlag = true;
                } else if (Character.isLowerCase(ch)) {
                    lowerCaseFlag = true;
                }

                if( capitalFlag && lowerCaseFlag) {
                    return true;
                }
            }
        }

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println(TerminalColor.ANSI_RED + "Password must contain at least 1 capital letter and 1 lowercase letter!");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return false;
    }

    public static boolean userEmailChecker(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()) {

            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            System.out.println(TerminalColor.ANSI_RED + "Invalid email address!");
            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

            return false;
        }
        else {
            Request request = new Request(RequestType.CHECK_EMAIL, email);
            Response response;

            response = Communicate.communicate(request);

            return response.getResponseStatus().equals(ResponseStatus.EMAIL_VALID);
        }
    }

    public static boolean userPhoneNumberChecker(String phoneNumber) {
        if(phoneNumber.length() != 10) {
            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            System.out.println(TerminalColor.ANSI_RED + "Invalid phone number!");
            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

            return false;
        }
        else {
            Request request = new Request(RequestType.CHECK_PHONE_NUMBER, phoneNumber);
            Response response;

            response = Communicate.communicate(request);

            return response.getResponseStatus().equals(ResponseStatus.PHONE_NUMBER_VALID);
        }
    }

    public static boolean userAvatarChecker(File file) {
        long fileSizeInMb = file.length();
        if((fileSizeInMb / (1024 * 1024)) <= 1) {
            return true;
        }
        else {
            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            System.out.println(TerminalColor.ANSI_RED + "File must be maximum 1 MB!");
            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

            return false;
        }
    }

    public static boolean userHeaderChecker(File file) {
        long fileSizeInMb = file.length();
        if((fileSizeInMb / (1024 * 1024)) <= 2) {
            return true;
        }
        else {
            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            System.out.println(TerminalColor.ANSI_RED + "File must be maximum 1 MB!");
            System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

            return false;
        }
    }
}
