package org.example.Client.Utils;

import org.example.Client.Controller.UserController;
import org.example.Client.Models.RequestAndResponse.Request;
import org.example.Client.Models.RequestAndResponse.RequestType;
import org.example.Client.Models.RequestAndResponse.Response;
import org.example.Client.Models.RequestAndResponse.ResponseStatus;
import org.example.Client.RequestAndReponse.Communicate;
import org.example.Client.Terminal.TerminalColor;
import org.example.Client.entry.Login;
import org.example.Client.Terminal.Menu;
import org.example.Client.Models.TextMessage;
import org.example.Client.Models.Tweet;
import org.example.Client.Models.User;

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
                        Login.user.setUsername(newUserName);

                        Request request = new Request(RequestType.CHANGE_PROFILE, Login.user);
                        Response response;

                        response = Communicate.communicate(request);

                        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_VALID)) {
                            System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                        }
                    }
                    else {
                        throw new Exception();
                    }

                    break;

                case 2:
                    String newPassword = Menu.editProfilePassword();

                    boolean newUserPassword = UserController.userPasswordChecker(newPassword);

                    if(newUserPassword) {
                        Login.user.setPassword(newPassword);

                        Request request = new Request(RequestType.CHANGE_PROFILE, Login.user);
                        Response response;

                        response = Communicate.communicate(request);

                        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_VALID)) {
                            System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                        }
                        else {
                            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                        }
                    }
                    else {
                        throw new Exception();
                    }

                    break;

                case 3:
                    Login.user.setFirstName(Menu.editProfileFirstName());

                    Request request = new Request(RequestType.CHANGE_PROFILE, Login.user);
                    Response response;

                    response = Communicate.communicate(request);

                    if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_VALID)) {
                        System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                    }
                    else {
                        System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                    }

                    break;

                case 4:
                    Login.user.setLastName(Menu.editProfileLastName());

                    request = new Request(RequestType.CHANGE_PROFILE, Login.user);

                    response = Communicate.communicate(request);

                    if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_VALID)) {
                        System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                    }
                    else {
                        System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                    }

                    break;

                case 5:
                    String avatarFilePath = Menu.signupAvatarGetter();

                    if(UserController.userAvatarChecker(new File(avatarFilePath))) {
                        Login.user.setPicture(new File(avatarFilePath));

                        request = new Request(RequestType.CHANGE_PROFILE, Login.user);

                        response = Communicate.communicate(request);

                        if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_VALID)) {
                            System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                        }
                        else {
                            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                        }
                    }

                    break;
                case 6:
                    int editProfileBioChoice;

                    do {
                        editProfileBioChoice = Menu.editProfileBio();

                        switch (editProfileBioChoice) {
                            case 1 :
                                Login.user.getBio().setText(Menu.editProfileBioText());

                                request = new Request(RequestType.CHANGE_PROFILE, Login.user);
                                response = Communicate.communicate(request);

                                if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_VALID)) {
                                    System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                                }
                                else {
                                    System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                                }

                                break;
                            case 2 :
                                Login.user.getBio().setLocation(Menu.editProfileBioLocation());

                                request = new Request(RequestType.CHANGE_PROFILE, Login.user);
                                response = Communicate.communicate(request);

                                if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_VALID)) {
                                    System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                                }
                                else {
                                    System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
                                }

                                break;
                            case 3 :
                                Login.user.getBio().setWebsiteURL(Menu.editProfileBioWebsiteURL());

                                request = new Request(RequestType.CHANGE_PROFILE, Login.user);
                                response = Communicate.communicate(request);

                                if(response.getResponseStatus().equals(ResponseStatus.CHANGE_PROFILE_VALID)) {
                                    System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);
                                }
                                else {
                                    System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
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
                case 1 -> Tweet.addTweet();
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
}
