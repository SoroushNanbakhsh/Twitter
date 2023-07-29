package com.example.finalproject;

import com.example.finalproject.ClientSocket.ClientSocket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class TwitterApplication extends Application {
    public static ArrayList<Tweet> tweets = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
//        Image image = new Image(getClass().getResource("Logo_of_Twitter.svg.png").toURI().toString());
//        User user1 = new User("Anna", "anna2004", "Armina", "Motaghi",
//                "ani.motaghi@gmail.com", "9174484945", "IR", "2004-08-07",
//               image);
//        Image image1 = new Image(getClass().getResource("images.png").toURI().toString());
//
//        tweets.add(new Tweet(user1, "hi"));
//        tweets.add(new Tweet(user1, "bye"));
//        tweets.add(new Tweet(user1, "meow", image1));
//        user1.setMessages(tweets);
        ClientSocket.clientSocket = new Socket("localhost", 9999);
        ClientSocket.oos = new ObjectOutputStream(ClientSocket.clientSocket.getOutputStream());
        ClientSocket.ois = new ObjectInputStream(ClientSocket.clientSocket.getInputStream());

        FXMLLoader fxmlLoader = new FXMLLoader(TwitterApplication.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}