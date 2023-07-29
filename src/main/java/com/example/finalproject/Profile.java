package com.example.finalproject;

import com.example.finalproject.Models.Bio;
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
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Profile {
    public static void startProcess() throws Exception {
        int choice;

        do {
            choice = Menu.profileOptions();

            switch (choice) {
                case 1 -> Profile.editProfile();
                case 2 -> Profile.tweets();
                case 3 -> Profile.replies();
                case 0 -> {}

                default -> Menu.invalidChoice();
            }
        } while(choice != 1 && choice != 2 && choice != 3 && choice != 0);
    }

    public static void editProfile() throws Exception {
        int choice;

        do {
            choice = Menu.editProfileOptions();

            switch (choice) {
                case 1:
                    String newUserName = Menu.editProfileUserName();

                    boolean newUserNameCheck = UserController.userUsernameChecker(newUserName);

                    if(newUserNameCheck) {
                        HashMap<User, String> tmp = new HashMap<>();
                        tmp.put(Login.user, newUserName);

                        Request request = new Request(RequestType.CHANGE_PROFILE_USERNAME, tmp);
                        Response response;

                        response = Communicate.communicate(request);

                        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_USERNAME_VALID)) {
                            Login.user.setUsername(newUserName);
                        }
                        else {
                            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                            throw new Exception();
                        }
                    }
                    else {
                        throw new Exception();
                    }

                    break;

                case 2:
                    String newPassword = Menu.editProfilePassword();

                    int newUserPassword = UserController.userPasswordChecker(newPassword);

                    if(newUserPassword == 0) {
                        HashMap<User, String> tmp = new HashMap<>();
                        tmp.put(Login.user, newPassword);

                        Request request = new Request(RequestType.CHANGE_PROFILE_PASSWORD, tmp);
                        Response response;

                        response = Communicate.communicate(request);

                        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_PASSWORD_VALID)) {
                            Login.user.setPassword(newPassword);
                        }
                        else {
                            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                            throw new Exception();
                        }
                    }
                    else {
                        throw new Exception();
                    }

                    break;

                case 3:
                    String newFirstName = Menu.editProfileFirstName();

                    HashMap<User, String> tmp = new HashMap<>();
                    tmp.put(Login.user, newFirstName);

                    Request request = new Request(RequestType.CHANGE_PROFILE_FIRSTNAME, tmp);
                    Response response;

                    response = Communicate.communicate(request);

                    if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_FIRSTNAME_VALID)) {
                        Login.user.setFirstName(newFirstName);
                    }
                    else {
                        System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                        throw new Exception();
                    }

                    break;

                case 4:
                    String newLastName = Menu.editProfileLastName();

                    tmp = new HashMap<>();
                    tmp.put(Login.user, newLastName);

                    request = new Request(RequestType.CHANGE_PROFILE_LASTNAME, tmp);
                    response = Communicate.communicate(request);

                    if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_LASTNAME_VALID)) {
                        Login.user.setLastName(newLastName);
                    }
                    else {
                        System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                        throw new Exception();
                    }

                    break;

                case 5:
                    String avatarFilePath = Menu.signupAvatarGetter();

                    if(UserController.userAvatarChecker(new File(avatarFilePath))) {
                        Image newImage = new Image(avatarFilePath);

                        HashMap<User, Image> tmp2 = new HashMap<>();
                        tmp2.put(Login.user, newImage);
                        request = new Request(RequestType.CHANGE_PROFILE_AVATAR, tmp2);

                        response = Communicate.communicate(request);

                        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_AVATAR_VALID)) {
                            Login.user.setPicture(newImage);
                        }
                        else {
                            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                            throw new Exception();
                        }
                    }

                    break;
                case 6:
                    int editProfileBioChoice;

                    do {
                        editProfileBioChoice = Menu.editProfileBio();

                        switch (editProfileBioChoice) {
                            case 1 :
                                String newText = Menu.editProfileBioText();
                                Bio newBio = Login.user.getBio();
                                newBio.setText(newText);

                                HashMap<User, Bio> tmp2 = new HashMap<>();
                                tmp2.put(Login.user, newBio);

                                request = new Request(RequestType.CHANGE_PROFILE_BIO, tmp2);
                                response = Communicate.communicate(request);

                                if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_BIO_VALID)) {
                                    Login.user.getBio().setText(newText);
                                }
                                else {
                                    System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                                    throw new Exception();
                                }

                                break;
                            case 2 :
                                String newLocation = Menu.editProfileBioLocation();
                                newBio = Login.user.getBio();
                                newBio.setLocation(newLocation);

                                tmp2 = new HashMap<>();
                                tmp2.put(Login.user, newBio);

                                request = new Request(RequestType.CHANGE_PROFILE_BIO, tmp2);
                                response = Communicate.communicate(request);

                                if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_BIO_VALID)) {
                                    Login.user.getBio().setLocation(newLocation);
                                }
                                else {
                                    System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                                    throw new Exception();
                                }

                                break;
                            case 3 :
                                String newWebsiteURL = Menu.editProfileBioWebsiteURL();
                                newBio = Login.user.getBio();
                                newBio.setWebsiteURL(newWebsiteURL);

                                tmp2 = new HashMap<>();
                                tmp2.put(Login.user, newBio);

                                request = new Request(RequestType.CHANGE_PROFILE_BIO, tmp2);
                                response = Communicate.communicate(request);

                                if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_BIO_VALID)) {
                                    Login.user.getBio().setWebsiteURL(newWebsiteURL);
                                }
                                else {
                                    System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                                    throw new Exception();
                                }

                                break;
                            case 0 :
                                break;
                            default :
                                Menu.invalidChoice();
                        }

                    } while(editProfileBioChoice != 1 && editProfileBioChoice != 2 && editProfileBioChoice != 3 && editProfileBioChoice != 0);

                    break;
                case 0:
                    break;
                default:
                    Menu.invalidChoice();
            }

        }while(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 0);
    }

    public static void tweets() throws Exception {
        Menu.showTweets(Login.user);

        int choice;
        do {
            choice = Menu.addTweetOption();

            switch (choice) {
                case 0 -> {}
                case 1 -> Tweet.addTweet(null);
                default -> Menu.invalidChoice();
            }

        } while(choice != 1 && choice != 0);
    }

    public static void replies() {
        HashMap<TextMessage, TextMessage> replies = Login.user.getReplies();

        for(TextMessage elementMessage : replies.keySet()) {
            Menu.showReply(elementMessage, replies.get(elementMessage));
        }
    }

    public static void changeUsername(String newUserName) {
        HashMap<User, String> tmp = new HashMap<>();
        tmp.put(Login.user, newUserName);

        Request request = new Request(RequestType.CHANGE_PROFILE_USERNAME, tmp);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_USERNAME_VALID)) {
            Login.user.setUsername(newUserName);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void changePassword(String newPassword) {
        HashMap<User, String> tmp = new HashMap<>();
        tmp.put(Login.user, newPassword);

        Request request = new Request(RequestType.CHANGE_PROFILE_PASSWORD, tmp);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_PASSWORD_VALID)) {
            Login.user.setPassword(newPassword);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void changeFirstname(String newFirstName) {
        HashMap<User, String> tmp = new HashMap<>();
        tmp.put(Login.user, newFirstName);

        Request request = new Request(RequestType.CHANGE_PROFILE_FIRSTNAME, tmp);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_FIRSTNAME_VALID)) {
            Login.user.setFirstName(newFirstName);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void changeLastname(String newLastName) {
        HashMap<User, String> tmp = new HashMap<>();
        tmp.put(Login.user, newLastName);

        Request request = new Request(RequestType.CHANGE_PROFILE_LASTNAME, tmp);
        Response response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_LASTNAME_VALID)) {
            Login.user.setLastName(newLastName);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void changeBioText(String newText) {
        Bio newBio = Login.user.getBio();
        newBio.setText(newText);

        HashMap<User, Bio> tmp2 = new HashMap<>();
        tmp2.put(Login.user, newBio);

        Request request = new Request(RequestType.CHANGE_PROFILE_BIO, tmp2);
        Response response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_BIO_VALID)) {
            Login.user.getBio().setText(newText);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void changeBioLocation(String newLocation) {
        Bio newBio = Login.user.getBio();
        newBio.setLocation(newLocation);

        HashMap<User, Bio> tmp2 = new HashMap<>();
        tmp2.put(Login.user, newBio);

        Request request = new Request(RequestType.CHANGE_PROFILE_BIO, tmp2);
        Response response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_BIO_VALID)) {
            Login.user.getBio().setLocation(newLocation);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void changeBioWebsiteURL(String newWebsiteURL) {
        Bio newBio = Login.user.getBio();
        newBio.setWebsiteURL(newWebsiteURL);

        HashMap<User, Bio> tmp2 = new HashMap<>();
        tmp2.put(Login.user, newBio);

        Request request = new Request(RequestType.CHANGE_PROFILE_BIO, tmp2);
        Response response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_BIO_VALID)) {
            Login.user.getBio().setWebsiteURL(newWebsiteURL);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void changeProfileAvatar(Image newImage) {
        HashMap<User, Image> tmp2 = new HashMap<>();
        tmp2.put(Login.user, newImage);

        Request request = new Request(RequestType.CHANGE_PROFILE_AVATAR, tmp2);

        Response response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_AVATAR_VALID)) {
            Login.user.setPicture(newImage);
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

