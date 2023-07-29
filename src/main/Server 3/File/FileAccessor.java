package com.Twitter.File;

import org.example.Client.Models.Chat;
import org.example.Client.Models.Command;
import org.example.Client.Models.TextMessage;
import org.example.Client.Models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileAccessor {
    public static ArrayList<User> getUsers() {
        ArrayList<User> users;

        try {
            ObjectInputStream FIn = new ObjectInputStream(new FileInputStream("Users.bin"));

            try {
                users = (ArrayList<User>) FIn.readObject();
                putUsers(users);

                if(users == null) {
                    users = new ArrayList<>();
                }

                FIn.close();

                return users;
            } catch (ClassNotFoundException e) {
                FIn.close();
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    public static void putUsers(ArrayList<User> users) {

        try {
            ObjectOutputStream FOut  = new ObjectOutputStream(new FileOutputStream("Users.bin"));
            FOut.writeObject(users);
            FOut.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<User, ArrayList<User>> userFollowingsFileReader() {
        HashMap<User, ArrayList<User>> userFollowings;

        try {
            ObjectInputStream FIn = new ObjectInputStream(new FileInputStream("UserFollowings.bin"));

            try {
                userFollowings = (HashMap<User, ArrayList<User>>) FIn.readObject();
                FIn.close();

                return userFollowings;
            } catch (ClassNotFoundException e) {
                FIn.close();
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addingUserFollowingsFileWriter(User user, User followingPerson) {
        HashMap<User, ArrayList<User>> userFollowings;

        try {
            ObjectInputStream FIn = new ObjectInputStream(new FileInputStream("UserFollowings.bin"));

            try {
                userFollowings = (HashMap<User, ArrayList<User>>) FIn.readObject();
                FIn.close();

                ObjectOutputStream FOut = new ObjectOutputStream(new FileOutputStream("UserFollowings.bin"));
                for (User userCounter: getUsers()) {
                    if(userCounter.getUsername().equals(user.getUsername())){
                        ArrayList<User> followings = userFollowings.get(user);
                        followings.add(followingPerson);
                        FOut.writeObject(userFollowings.put(user, followings));
                    }
                }

            } catch (ClassNotFoundException e) {
                FIn.close();
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void removingUserFollowingsFileWriter(User user, User followingPerson) {
        HashMap<User, ArrayList<User>> userFollowings;

        try {
            ObjectInputStream FIn = new ObjectInputStream(new FileInputStream("UserFollowings.bin"));

            try {
                userFollowings = (HashMap<User, ArrayList<User>>) FIn.readObject();
                FIn.close();

                ObjectOutputStream FOut = new ObjectOutputStream(new FileOutputStream("UserFollowings.bin"));
                for (User userCounter: getUsers()) {
                    if(userCounter.getUsername().equals(user.getUsername())){
                        ArrayList<User> followings = userFollowings.get(user);
                        followings.remove(followingPerson);
                        FOut.writeObject(userFollowings.put(user, followings));
                    }
                }

            } catch (ClassNotFoundException e) {
                FIn.close();
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static HashMap<User, ArrayList<TextMessage>> readMessage(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Messages.bin"))){
            return (HashMap<User, ArrayList<TextMessage>>) objectInputStream.readObject();
        }catch (IOException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public static HashMap<User, HashMap<TextMessage, PhotoMessage>> readMessageWithPhoto(){
//        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("MessagesWithPhoto.bin"))){
//            return (HashMap<User, HashMap<TextMessage, PhotoMessage>>) objectInputStream.readObject();
//        }catch (IOException e){
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public static void writeMessage(HashMap<User, ArrayList<TextMessage>> userTweets){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Messages.bin"))) {
            objectOutputStream.writeObject(userTweets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void writeMessageWithPhoto(HashMap<User, HashMap<TextMessage, PhotoMessage>> userTweets){
//        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("MessagesWithPhoto.bin"))) {
//            objectOutputStream.writeObject(userTweets);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void insertToLog(Command command) {
        try {
            FileWriter fileWriter = new FileWriter("log.txt", true);

            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(command.toString());

            printWriter.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeLogFile() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("log.txt"));

            objectOutputStream.writeObject(null);

            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void putChats(ArrayList<Chat> chats) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("chats.bin"));

            objectOutputStream.writeObject(chats);

            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Chat> getChats() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("chats.bin"));

            ArrayList<Chat> chats = (ArrayList<Chat>) objectInputStream.readObject();
            putChats(chats);

            objectInputStream.close();

            return chats;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
