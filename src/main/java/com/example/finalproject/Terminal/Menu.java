package com.example.finalproject.Terminal;

import com.example.finalproject.Models.Countries;
import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.RequestType;
import com.example.finalproject.Models.RequestAndResponse.Response;
import com.example.finalproject.Models.RequestAndResponse.ResponseStatus;
import com.example.finalproject.Models.TextMessage;
import com.example.finalproject.Models.User;
import com.example.finalproject.RequestAndReponse.Communicate;
import com.example.finalproject.Search;
import com.example.finalproject.UserController;
import com.example.finalproject.entry.Login;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public static void printFirstPage() {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.println("                    1| Login");
        System.out.println("                    2| Signup");
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println("**************************************************");
        System.out.print(TerminalColor.ANSI_RESET);
    }
    public static String[] printSignupPage() {
        Scanner scanner = new Scanner(System.in);
        String[] userDetails = new String[8];

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");

        do {
            System.out.print("                    Username: ");
            userDetails[0] = scanner.nextLine();
        } while (!UserController.userUsernameChecker(userDetails[0]));

        System.out.print("                    Firstname: ");
        userDetails[2] = scanner.nextLine();
        System.out.print("                    Lastname: ");
        userDetails[3] = scanner.nextLine();

        do {
            System.out.print("                    Password: ");
            userDetails[1] = scanner.nextLine();
        } while (UserController.userPasswordChecker(userDetails[1]) != 0);

        String password2;
        do {
            System.out.print("                    Again Password: ");
            password2 = scanner.nextLine();
        } while (!password2.equals(userDetails[1]));

        do {
            System.out.print("                    Email: ");
            userDetails[4] = scanner.nextLine();
        } while (UserController.userEmailChecker(userDetails[4]) != 0);

        do {
            System.out.print("                    Phone number: ");
            userDetails[5] = scanner.nextLine();
        } while (UserController.userPhoneNumberChecker(userDetails[5]) != 0);

        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Choose your country: \n");

        String[] countries = Countries.printCountries();

        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        int country = scanner.nextInt();
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");


        Locale countryLocale = new Locale("", countries[country - 1]);
        userDetails[6] = countryLocale.getDisplayCountry();
        scanner.nextLine();

        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");


        System.out.print("                    Birthdate: ");
        userDetails[7] = scanner.nextLine();

        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return userDetails;
    }

    public static int avatarOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.println("                    Do you want to upload avatar?");
        System.out.println("                    1|Yes");
        System.out.println("                    2|No");
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return scanner.nextInt();
    }

    public static String signupAvatarGetter() {
        String avatarFilePath;
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");

        do {
            System.out.print("                    Avatar file path: ");
            avatarFilePath = scanner.nextLine();
        } while (!UserController.userAvatarChecker(new File(avatarFilePath)));

        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return avatarFilePath;
    }

    public static String[] printLoginPage() {
        Scanner scanner = new Scanner(System.in);
        String[] usernamePassword = new String[2];

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Username: ");
        usernamePassword[0] = scanner.nextLine();
        System.out.print("                    Password: ");
        usernamePassword[1] = scanner.nextLine();
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println("**************************************************");

        return usernamePassword;
    }

    public static void loginFailed() {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("                    ----------");
        System.out.println(TerminalColor.ANSI_CYAN + "Wrong username or password");
        System.out.println("                    ----------");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
    }

    public static void search(ArrayList<User> users) throws Exception {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("Username, Firstname or Lastname: ");

        Search.startProcess();
    }

    public static void showProfile(User user) throws Exception {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("Followers: " + user.getFollowers().size() + " " + "Followings: " + user.getFollowings().size());
        System.out.println("                    ----------");
        System.out.println("Bio: " + user.getBio());
        System.out.println("                    ----------");
        // show tweets of user profile
        Menu.showTweets(user);
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        // checking if
    }

    public static void notFound() {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("                    Not found!");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
    }

    public static int homePage() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.println("                    1|Timeline");
        System.out.println("                    2|Search");
        System.out.println("                    3|Direct");
        System.out.println("                    4|Profile");
        System.out.println("                    0|Log out");
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        choice = scanner.nextInt();

        return choice;
    }

    public static void invalidChoice() {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("                    Invalid choice!");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
    }

    public static int profileOptions() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");

        System.out.print("followings: " + Login.user.getFollowings().size() + " | ");
        System.out.println("followers: " + Login.user.getFollowers().size());

        System.out.println("                    ----------");
        System.out.println("                    1|Edit profile");
        System.out.println("                    2|Tweets");
        System.out.println("                    3|Replies");
        System.out.println("\n                    0|Back");
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return scanner.nextInt();
    }

    public static int editProfileOptions() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.println("                    1|Username");
        System.out.println("                    2|Password");
        System.out.println("                    3|Firstname");
        System.out.println("                    4|Lastname");
        System.out.println("                    5|Profile avatar");
        System.out.println("                    6|Bio");
        System.out.println("\n                    0|Back");
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return scanner.nextInt();
    }

    public static String editProfileUserName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    New username: ");
        String username = scanner.nextLine();
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return username;
    }

    public static String editProfilePassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Password: ");
        String password = scanner.nextLine();
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return password;
    }

    public static String editProfileFirstName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Firstname: ");
        String firstname = scanner.nextLine();
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return firstname;
    }

    public static String editProfileLastName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Lastname: ");
        String lastname = scanner.nextLine();
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return lastname;
    }

    public static int editProfileBio() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.println("                    1|Edit text");
        System.out.println("                    2|Edit location");
        System.out.println("                    3|Edit websiteURL");
        System.out.println("\n                    0|Back");
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return scanner.nextInt();
    }

    public static String editProfileBioText() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");

        String text;
        do {
        System.out.print("                    Text: ");
        text = scanner.nextLine();

            if(text.length() > 160) {
                System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
                System.out.println(TerminalColor.ANSI_RED + "Bio's text maximum size is 160 characters!");
                System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            }

        }while(text.length() > 160);

        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return text;
    }

    public static String editProfileBioLocation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Location: ");
        String location = scanner.nextLine();
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return location;
    }

    public static String editProfileBioWebsiteURL() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Website URL: ");
        String websiteURL = scanner.nextLine();
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return websiteURL;
    }

    public static void showTweets(User user) throws Exception {
        Request request = new Request(RequestType.GET_USER_TWEETS, user);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.GET_USER_TWEETS_INVALID)) {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);

            throw new Exception();
        }
        else {
            System.out.println(TerminalColor.ANSI_GREEN + "Success" + TerminalColor.ANSI_RESET);

            ArrayList<TextMessage> messages = (ArrayList<TextMessage>) response.getData();
            for(TextMessage element : messages ) {
                System.out.println(element);
            }
        }
    }

    public static int addTweetOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.println("                    1|Add tweet");
        System.out.println("                    0|Back");
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        int choice = scanner.nextInt();

        return choice;
    }

    public static void showLatestTweet(User user) {
        TextMessage textMessage = user.getMessages().get(user.getMessages().size() - 1);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println(user.getUsername());

        System.out.println(textMessage.getTextMessage());
        System.out.println("Replies: " + textMessage.getReplies().size() + " | " + "Likes: " + textMessage.getLikes());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(formatter.format(textMessage.getDate()));
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
    }

    public static void showFavstar(TextMessage textMessage, User user) {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println(user.getUsername());
        System.out.println();

        System.out.println(textMessage.getTextMessage());
        System.out.println("Replies: " + textMessage.getReplies().size() + " | " + "Likes: " + textMessage.getLikes());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(formatter.format(textMessage.getDate()));
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
    }

    public static TextMessage addTweet() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Description: ");

        String text;
        do {

            text = scanner.nextLine();

            if(text.length() > 280) {
                System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
                System.out.println(TerminalColor.ANSI_RED + "Your text must contain at last 280 characters! try again.");
                System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            }

        }while(text.length() > 280);

        System.out.print("Do you want to add picture or video?");
        System.out.println("                    1| Yes");
        System.out.println("                    2| No");
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        int choice;
        do {
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
                System.out.println("\n\n\n\n");
                System.out.println("                    ----------");
                System.out.print("                    Enter File's path: ");

                File file;
                do {
                    String filePath = scanner.nextLine();
                    file = new File(filePath);

                    if(!file.exists()) {
                        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
                        System.out.println(TerminalColor.ANSI_RED + "Invalid path! try again.");
                        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
                    }

                } while(!file.exists());

                return new TextMessage(text, Login.user);

            } else if (choice == 2) {

                return new TextMessage(text, Login.user);

            } else {
                System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
                System.out.println(TerminalColor.ANSI_RED + "Invalid option! try again.");
                System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            }
        } while(!(choice == 1 || choice == 2));

        return null;
    }

    public static int directPage() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");

        // showing all followed users
        ArrayList<User> followedUsers = Login.user.getFollowings();

        int num = 1;
        for(User elementUser : followedUsers) {
            System.out.println("                    " + num++ + "| " + elementUser.getUsername());
            System.out.println("                    ----------");
            System.out.println("                    0|Get back");
        }

        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        int tmp = scanner.nextInt();
        scanner.nextLine();

        return tmp;
    }

    public static int optionsForFollowingTweet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("following  1|like  2|Reply  3|Retweet  4|Quote   5|Unfollow");

        return scanner.nextInt();
    }

    public static int optionsForFavstarTweets() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1|follow  2|like  3|Reply  4|show user profile  5|Retweet   6|Quote");

        return scanner.nextInt();
    }

    public static String replyOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Text: ");

        String text;
        do {

            text = scanner.nextLine();

            if(text.length() > 280) {
                System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
                System.out.println(TerminalColor.ANSI_RED + "Your text must contain at last 280 characters! try again.");
                System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
            }

        }while(text.length() > 280);

        return text;
    }

    public static void actionWasSuccessful() {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println(TerminalColor.ANSI_RED + "Successful");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
    }

    public static void showReply(TextMessage textMessageMain, TextMessage reply) {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println(textMessageMain.getTextMessage());
        System.out.println("Likes: " + textMessageMain.getLikes() + " | Replies: " + textMessageMain.getReplies());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(formatter.format(textMessageMain.getDate()));
        System.out.println(            "----------");
        System.out.println("Your reply to this Message: " + reply.getTextMessage());
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
    }

    public static String newQuote() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("                    Enter your text: ");
        String text = scanner.nextLine();
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return text;
    }

    public static void showListOfUsers(ArrayList<User> users, String title) {
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.print("                    " + title + ": ");
        System.out.println("                    ----------");

        for(User elementUser : users) {
            System.out.println(elementUser.getUsername());
        }

        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
    }

    public static String getChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");
        System.out.println("\n\n\n\n");
        System.out.println("                    ----------");
        System.out.print("Enter username(enter 0 to get back): ");
        String choice = scanner.nextLine();
        System.out.println("                    ----------");
        System.out.println("\n\n\n\n");
        System.out.println(TerminalColor.ANSI_PURPLE + "**************************************************");

        return choice;
    }

    public static void enteredChat(String username) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("You entered chat with " + username);
        System.out.println("==========================================");

        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(Login.user.getUsername());
        usernames.add(username);

        Request request = new Request(RequestType.GET_CHAT, usernames);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.GET_CHAT_VALID)) {
            HashMap<String, String> chat = (HashMap<String, String>) response.getData();

            if(chat != null) {
                for(String element : chat.keySet()) {
                    System.out.println(element + ": " + chat.get(element));
                }
            }
        }
        else {
            System.out.println(TerminalColor.ANSI_RED + "Failed" + TerminalColor.ANSI_RESET);
            throw new Exception();
        }

        String yourMessage;

        do {
            yourMessage = scanner.nextLine();

            request = new Request(RequestType.SEND_MESSAGE, yourMessage);

            response = Communicate.communicate(request);

            if(response.getResponseStatus().equals(ResponseStatus.SEND_MESSAGE_VALID)) {
                System.out.println(TerminalColor.ANSI_GREEN + "Message sent successfully" + TerminalColor.ANSI_RESET);
            }
            else {
                System.out.println(TerminalColor.ANSI_RED + "Failed to send message" + TerminalColor.ANSI_RESET);
                throw new Exception();
            }
        } while (!yourMessage.equals("Exit"));
    }
}
