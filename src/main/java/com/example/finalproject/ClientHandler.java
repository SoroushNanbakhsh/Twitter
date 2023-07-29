package com.example.finalproject;

import com.example.finalproject.File.FileAccessor;
import com.example.finalproject.Models.Bio;
import com.example.finalproject.Models.Command;
import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.RequestType;
import com.example.finalproject.Models.RequestAndResponse.Response;
import com.example.finalproject.Models.RequestAndResponse.ResponseStatus;
import com.example.finalproject.Models.TextMessage;
import com.example.finalproject.Models.User;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ClientHandler implements Runnable{
    private Socket client;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int N0;

    public ClientHandler(Socket client, int N0) throws IOException {
        this.client = client;
        this.N0 = N0;

        oos = new ObjectOutputStream(client.getOutputStream());
        ois = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        while(true) synchronized (this){
            Request request;

            try {
                request = ((Request) ois.readObject());

                System.out.println("Client N"+ N0 + " | " + "Request received" + " | " + request.getRequestType());
                FileAccessor.insertToLog(new Command("Request", request, null, formatter.format(new Date())));

                Response response = getResponse(request);

                System.out.println("Response sent" + " | " + response.getResponseStatus());
                FileAccessor.insertToLog(new Command("Response", null, response, formatter.format(new Date())));

                oos.writeObject(response);

                if(response.getResponseStatus().equals(ResponseStatus.LOG_OUT_VALID)) {
                    ois.close();
                    oos.close();
                    client.close();

                    FileAccessor.insertToLog(new Command("Client N" + N0 + " left", null, null, formatter.format(new Date())));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * creates the response for sent request
     *
     * @param request analysis request which is sent from client
     * @return returns specific response due to received request
     */
    private Response getResponse(Request request) {
        Response response = null;
        RequestType requestType = request.getRequestType();

        switch (requestType) {
            // --- login and signup section ---
            case CHECK_USERNAME -> response = checkUsername((String) request.getData());
            case CHECK_EMAIL -> response = checkEmail((String) request.getData());
            case CHECK_PHONE_NUMBER -> response = checkPhoneNumber((String) request.getData());
            case SIGN_UP -> response = signUpResponse((User) request.getData());
            case LOG_IN -> response = logInResponse((User) request.getData());
            case LOG_OUT -> response = logOutResponse((User) request.getData());
            case LOG_IN_WITH_TIME -> response = logInWithTime();

            // Tweet
            case ADD_TWEET -> response = addTweetResponse((User) request.getData());
            case GET_NEW_TWEETS -> response = getNewTweets((User) request.getData());
            case UPDATE_TWEET -> response = updateTweetResponse((HashMap<User, TextMessage>) request.getData());

            // miscellaneous
            case FOLLOW -> response = followResponse((ArrayList<User>) request.getData());
            case UNFOLLOW -> response = unfollowResponse((ArrayList<User>) request.getData());
            case BLOCK -> response = blockUserResponse((ArrayList<User>) request.getData());

            // search
            case SEARCH_IN_USERS -> response = searchInUsersResponse((String) request.getData());

            // user
            case UPDATE_USER -> response = updateUserResponse((User) request.getData());
            case CHANGE_PROFILE_AVATAR -> response = changeProfileAvatar((HashMap<User, Image>) request.getData());
            case CHANGE_PROFILE_USERNAME -> response = changeProfileUsername((HashMap<User, String>) request.getData());
            case CHANGE_PROFILE_PASSWORD -> response = changeProfilePassword((HashMap<User, String>) request.getData());
            case CHANGE_PROFILE_FIRSTNAME -> response = changeProfileFirstName((HashMap<User, String>) request.getData());
            case CHANGE_PROFILE_LASTNAME -> response = changeProfileLastName((HashMap<User, String>) request.getData());
            case CHANGE_PROFILE_BIO -> response = changeProfileBio((HashMap<User, Bio>) request.getData());

            // Direct
            case DIRECT_MESSAGE -> response = directMessageResponse((ArrayList<User>) request.getData());

        }

        return response;
    }

    /**
     *
     * checking if given username already exists or not
     *
     * @param username
     * @return returns response due to result of username
     */
    private Response checkUsername(String username) {
        if(userFinder(username) != null) {
            return new Response(ResponseStatus.INVALID_USERNAME);
        }

        return new Response(ResponseStatus.VALID_USERNAME);
    }

    /**
     * checking if the email which client entered while trying to sign up is
     * already exists or not
     *
     * @param email entered email for sign up by user
     * @return if the email exists then the return response will be invalid
     */
    private Response checkEmail(String email) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.EMAIL_VALID);
        }

        for(User elementUser : users) {
            if(elementUser.getEmail().equals(email)) {
                return new Response(ResponseStatus.EMAIL_INVALID);
            }
        }

        return new Response(ResponseStatus.EMAIL_VALID);
    }

    /**
     * checking if phone number entered by user while trying to signup already exists or not
     *
     * @param phoneNumber entered by user while trying to signup from client
     * @return valid if it exists, invalid if it doesn't
     */
    private Response checkPhoneNumber(String phoneNumber) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.PHONE_NUMBER_VALID);
        }

        for(User elementUser : users) {
            if(elementUser.getPhoneNumber().equals(phoneNumber)) {
                return new Response(ResponseStatus.PHONE_NUMBER_INVALID);
            }
        }

        return new Response(ResponseStatus.PHONE_NUMBER_VALID);
    }

    /**
     *
     * searching for User by username
     *
     * @param username
     * @return User will be returned if it is founded by username and null will be returned if it is not founded
     */
    private User userFinder(String username) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return null;
        }

        for(User elementUser : users) {
            if(elementUser.getUsername().equals(username)) {
                return elementUser;
            }
        }

        return null;
    }

    /**
     * adds user to file
     *
     * @param user
     * @return sign up valid if the action is possible
     */
    private Response signUpResponse(User user) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            users = new ArrayList<>();
        }

        users.add(user);
        FileAccessor.putUsers(users);

        return new Response(ResponseStatus.SIGNUP_VALID);
    }

    /**
     * Checks if sent User object from client exactly exists in file/database or not
     *
     * @param user User that only has 2 fields filled(username and password)
     * @return if user exists in file/database returns valid response
     */
    private Response logInResponse(User user) {
        ArrayList<User> users = FileAccessor.getUsers();

        for(User elementUser : users) {
            if(user.getUsername().equals(elementUser.getUsername())) {
                if(user.getPassword().equals(elementUser.getPassword())) {
                    return new Response(ResponseStatus.LOG_IN_VALID, elementUser);
                }
                else {
                    return new Response(ResponseStatus.LOG_IN_INVALID);
                }
            }
        }

        return new Response(ResponseStatus.LOG_IN_INVALID);
    }

    /**
     * log you out and sets your device information 'and' time information for your next log in
     *
     * @param user sets user which is provided from client parameters
     * @return valid
     */
    private Response logOutResponse(User user) {
        ArrayList<User> users = FileAccessor.getUsers();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for(User elementUser : users) {
            if(elementUser.equals(user)) {
                elementUser.setLastLogInDate(formatter.format(new Date()));
            }
        }

        FileAccessor.putUsers(users);

        for(Thread element : Server.threads) {
            if(element.equals(Thread.currentThread())) {
                element.interrupt();
            }
        }

        return new Response(ResponseStatus.LOG_OUT_VALID);
    }

    /**
     * this happens before trying to log in 'and' it depends on your device and last log in time.
     * if you had log in less than 5 minutes with the same device before, you won't need to write your password and username again
     *
     * @return reponse due to your last log in and device information
     */
    private Response logInWithTime() {
        ArrayList<User> users = FileAccessor.getUsers();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for(User elementUser : users) {
            String tmp = elementUser.getLastLogInDate();
            int lastMinute = Integer.parseInt(tmp.split(":")[1]);
            int currentMinute = Integer.parseInt(formatter.format(new Date()));

            if((currentMinute - lastMinute) <= 5) {
                // TODO check if the device is exactly last login device or not

                return new Response(ResponseStatus.LOG_IN_WITH_TIME_VALID);
            }
        }

        return new Response(ResponseStatus.LOG_IN_WITH_TIME_INVALID);
    }

    /**
     * since tweets adds to user object, this method finds user with the same
     * username and password that is sent from client and replace it with the
     * previous one to keep new tweet
     *
     * @param user
     * @return valid if it exists, invalid if it doesn't
     */
    private Response addTweetResponse(User user) {
        ArrayList<User> users = FileAccessor.getUsers();

        for(User elementUser : users) {
            if(user.getUsername().equals(elementUser.getUsername())) {
                if(user.getPassword().equals(elementUser.getPassword())) {
                    users.remove(elementUser);
                    users.add(user);

                    FileAccessor.putUsers(users);

                    return new Response(ResponseStatus.ADD_TWEET_VALID);
                }
                else {
                    return new Response(ResponseStatus.ADD_TWEET_INVALID);
                }
            }
        }

        return new Response(ResponseStatus.ADD_TWEET_INVALID);
    }

    /**
     * adding user to following field of log in user
     *
     * @param users first element of this array list is log in user and second one is target user
     * @return valid if log in user founded, invalid if it doesn't
     */
    private Response followResponse(ArrayList<User> users) {
        ArrayList<User> allUsers = FileAccessor.getUsers();

        for(User elementUser : allUsers) {
            if(elementUser.equals(users.get(0))) {
                elementUser.addFollowing(users.get(1));
                FileAccessor.putUsers(allUsers);

                return new Response(ResponseStatus.FOLLOW_VALID);
            }
        }

        return new Response(ResponseStatus.FOLLOW_INVALID);
    }

    /**
     * removes target user from log in user's followings
     *
     * @param users
     * @return valid if log in user exists, invalid if it doesn't
     */
    private Response unfollowResponse(ArrayList<User> users) {
        ArrayList<User> allUsers = FileAccessor.getUsers();

        for(User elementUser : allUsers) {
            if(users.get(0).equals(elementUser)) {
                elementUser.removeFollowing(users.get(1));
                FileAccessor.putUsers(allUsers);

                return new Response(ResponseStatus.UNFOLLOW_VALID);
            }
        }

        return new Response(ResponseStatus.UNFOLLOW_INVALID);
    }

    /**
     * iterating through users and compare user's firstname,lastname
     * and firstname with search item
     *
     * @param searchItem
     * @return those users that has at least one field same as search item
     */
    private Response searchInUsersResponse(String searchItem) {
        ArrayList<User> users = FileAccessor.getUsers();
        ArrayList<User> result = new ArrayList<>();

        for(User elementUser : users) {
            if(elementUser.getUsername().contains(searchItem)) {
                result.add(elementUser);
            }
        }

        return new Response(ResponseStatus.SEARCH_IN_USERS_VALID, result);
    }

    /**
     * compares now and time of each tweet if it is earlier, tweet's writer
     * getting checked and if it is one of User's followings, it will be returned
     *
     * @param user
     * @return
     */
    private Response getNewTweets(User user) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ArrayList<User> users = FileAccessor.getUsers();
        HashMap<User, TextMessage> result = new HashMap<>();

        if(users == null) {
            return new Response(ResponseStatus.GET_NEW_TWEETS_VALID, result);
        }

        for(User elementUser : users) {
            if(user.getFollowings() == null) {
                return new Response(ResponseStatus.GET_NEW_TWEETS_VALID, result);
            }

            if(user.getFollowings().contains(elementUser)) {
                ArrayList<TextMessage> messages = elementUser.getMessages();

                for (TextMessage elementMessage : messages) {
                    int year_1 = Integer.parseInt(elementMessage.getDate().split("/")[0]);
                    int year_2 = Integer.parseInt((formatter.format(new Date())).split("/")[0]);
                    BigInteger yearDiffInSec = new BigInteger((year_2 - year_1) * 31536000 + "");

                    int month_1 = Integer.parseInt(elementMessage.getDate().split("/")[1]);
                    int month_2 = Integer.parseInt((formatter.format(new Date())).split("/")[1]);
                    BigInteger monthDiffInSec = new BigInteger((month_2 - month_1) * 2592000 + "");

                    int day_1 = Integer.parseInt(elementMessage.getDate().split("/")[2]);
                    int day_2 = Integer.parseInt((formatter.format(new Date())).split("/")[2]);
                    BigInteger dayDiffInSec = new BigInteger((day_2 - day_1) * 86400 + "");

                    int hour_1 = Integer.parseInt((elementMessage.getDate().split(" ")[1]).split(":")[0]);
                    int hour_2 = Integer.parseInt(((formatter.format(new Date())).split(" ")[1]).split(":")[0]);
                    BigInteger hourDiffInSec = new BigInteger((hour_2 - hour_1) * 3600 + "");

                    int minute_1 = Integer.parseInt((elementMessage.getDate().split(":")[1]));
                    int minute_2 = Integer.parseInt((formatter.format(new Date())).split(":")[1]);
                    BigInteger minuteDiffInSec = new BigInteger((minute_2 - minute_1) * 60 + "");

                    int second_1 = Integer.parseInt(elementMessage.getDate().split(":")[2]);
                    int second_2 = Integer.parseInt((formatter.format(new Date())).split(":")[2]);

                    if ((Integer.parseInt(yearDiffInSec.add(monthDiffInSec.add(dayDiffInSec.add(hourDiffInSec.add(minuteDiffInSec.add(new BigInteger(second_2 - second_1 + "")))))).toString())) > 0) {
                        result.put(elementUser, elementMessage);
                    }
                }
            }
        }

        return new Response(ResponseStatus.GET_NEW_TWEETS_VALID, result);
    }

    /**
     * removes most likely user from file of users and replace it with
     * updated one
     *
     * @param user
     * @return valid if user founded, invalid if it doesn't
     */
    private Response updateUserResponse(User user) {
        ArrayList<User> users = FileAccessor.getUsers();

        for(User elementUser : users) {
            if(elementUser.getUsername().equals(user.getUsername())) {
                if(elementUser.getPassword().equals(user.getPassword())) {
                    users.remove(elementUser);
                    users.add(user);

                    FileAccessor.putUsers(users);

                    return new Response(ResponseStatus.UPDATE_USER_VALID);
                }
                else {
                    return new Response(ResponseStatus.UPDATE_USER_INVALID);
                }
            }
        }

        return new Response(ResponseStatus.UPDATE_USER_INVALID);
    }

    /**
     * updates user because one of it's tweets
     *
     * @param tweet
     * @return
     */
    private Response updateTweetResponse(HashMap<User, TextMessage> tweet) {
        User writer = tweet.keySet().iterator().next();

        updateUserResponse(writer);

        return new Response(ResponseStatus.UPDATE_TWEET_VALID);
    }

    /**
     *
     * change's logged-in user's avatar which is sent from client
     *
     * @param tmp
     * @return
     */
    private Response changeProfileAvatar(HashMap<User, Image> tmp) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.CHANGE_PROFILE_AVATAR_INVALID);
        }

        User user = tmp.keySet().iterator().next();

        for(User elementUser : users) {
            if(elementUser.getUsername().equals(user.getUsername())) {

                if(elementUser.getPassword().equals(user.getPassword())) {
                    user.setPicture(tmp.get(user));
                    users.remove(elementUser);
                    users.add(user);

                    FileAccessor.putUsers(users);

                    return new Response(ResponseStatus.CHANGE_PROFILE_AVATAR_VALID);
                }
                else {
                    return new Response(ResponseStatus.CHANGE_PROFILE_AVATAR_INVALID);
                }
            }
        }

        return new Response(ResponseStatus.CHANGE_PROFILE_AVATAR_INVALID);
    }

    private Response changeProfileUsername(HashMap<User, String> tmp) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.CHANGE_PROFILE_USERNAME_INVALID);
        }
        else {
            User user = tmp.keySet().iterator().next();

            for(User elementUser : users) {
                if(elementUser.equals(user)) {
                    user.setUsername(tmp.get(elementUser));
                    users.remove(elementUser);
                    users.add(user);

                    FileAccessor.putUsers(users);

                    return new Response(ResponseStatus.CHANGE_PROFILE_USERNAME_VALID);
                }
            }

            return new Response(ResponseStatus.CHANGE_PROFILE_USERNAME_INVALID);
        }
    }

    private Response changeProfilePassword(HashMap<User, String> tmp) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.CHANGE_PROFILE_PASSWORD_INVALID);
        }
        else {
            User user = tmp.keySet().iterator().next();

            for(User elementUser : users) {
                if(elementUser.equals(user)) {
                    users.remove(user);
                    user.setPassword(tmp.get(elementUser));
                    users.add(user);

                    FileAccessor.putUsers(users);

                    return new Response(ResponseStatus.CHANGE_PROFILE_PASSWORD_VALID);
                }
            }

            return new Response(ResponseStatus.CHANGE_PROFILE_PASSWORD_INVALID);
        }
    }

    private Response changeProfileFirstName(HashMap<User, String> tmp) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.CHANGE_PROFILE_FIRSTNAME_INVALID);
        }
        else {
            User user = tmp.keySet().iterator().next();

            for(User elementUser : users) {
                if(elementUser.equals(user)) {
                    users.remove(user);
                    user.setFirstName(tmp.get(elementUser));
                    users.add(user);

                    FileAccessor.putUsers(users);

                    return new Response(ResponseStatus.CHANGE_PROFILE_FIRSTNAME_VALID);
                }
            }

            return new Response(ResponseStatus.CHANGE_PROFILE_FIRSTNAME_INVALID);
        }
    }

    private Response changeProfileLastName(HashMap<User, String> tmp) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.CHANGE_PROFILE_LASTNAME_INVALID);
        }
        else {
            User user = tmp.keySet().iterator().next();

            for(User elementUser : users) {
                if(elementUser.equals(user)) {
                    users.remove(user);
                    user.setLastName(tmp.get(elementUser));
                    users.add(user);

                    FileAccessor.putUsers(users);

                    return new Response(ResponseStatus.CHANGE_PROFILE_LASTNAME_VALID);
                }
            }

            return new Response(ResponseStatus.CHANGE_PROFILE_LASTNAME_INVALID);
        }
    }

    private Response changeProfileBio(HashMap<User, Bio> tmp) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.CHANGE_PROFILE_BIO_INVALID);
        }
        else {
            User user = tmp.keySet().iterator().next();

            for(User elementUser : users) {
                if(elementUser.equals(user)) {
                    users.remove(user);
                    user.setBio(tmp.get(elementUser));
                    users.add(user);

                    FileAccessor.putUsers(users);

                    return new Response(ResponseStatus.CHANGE_PROFILE_BIO_VALID);
                }
            }

            return new Response(ResponseStatus.CHANGE_PROFILE_BIO_INVALID);
        }
    }

    private Response directMessageResponse(ArrayList<User> user) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.DIRECT_MESSAGE_INVALID);
        }
        else {
            for(User elementUser : users) {
                if(elementUser.equals(user.get(0))) {
                    for(User elementUser2 : users) {
                        if(elementUser2.equals(user.get(1))) {
                            return new Response(ResponseStatus.DIRECT_MESSAGE_VALID);
                        }
                    }

                    return new Response(ResponseStatus.DIRECT_MESSAGE_INVALID);
                }
            }

            return new Response(ResponseStatus.DIRECT_MESSAGE_INVALID);
        }
    }

    private Response blockUserResponse(ArrayList<User> tmp) {
        ArrayList<User> users = FileAccessor.getUsers();

        if(users == null) {
            return new Response(ResponseStatus.BLOCK_INVALID);
        }
        else {
            for(User element : users) {
                if(element.equals(tmp.get(0))) {
                    for(User element2 : users) {
                        if(element2.equals(tmp.get(1))) {
                            users.remove(tmp.get(0));
                            tmp.get(0).blockUser(tmp.get(1));
                            users.add(tmp.get(0));

                            FileAccessor.putUsers(users);

                            return new Response(ResponseStatus.BLOCK_VALID);
                        }
                    }

                    return new Response(ResponseStatus.BLOCK_INVALID);
                }
            }

            return new Response(ResponseStatus.BLOCK_INVALID);
        }
    }
}
