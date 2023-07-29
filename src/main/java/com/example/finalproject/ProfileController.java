package com.example.finalproject;

import com.example.finalproject.entry.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

class ProfileScene {


    @FXML
    private Label Bio = new Label(Login.user.getBio().toString());

    @FXML
    private Label birthdate = new Label(Login.user.getBirthDate());

    @FXML
    private Label followers = new Label(Login.user.getFollowers().size() + "");

    @FXML
    private Label followings = new Label(Login.user.getFollowings().size() + "");

    @FXML
    private ImageView header = new ImageView(Login.user.getHeader() == null ? new Image("com/example/client/png-transparent-back-front-sets-twitter-icon.png") : Login.user.getHeader());

    @FXML
    private ImageView profileImage = new ImageView(Login.user.getPicture() == null ? new Image("com/example/client/png-transparent-back-front-sets-twitter-icon.png") : Login.user.getPicture());

    @FXML
    private Label userId = new Label(Login.user.getFirstName() + "" + Login.user.getLastName());

    @FXML
    private Label username = new Label(Login.user.getUsername());

    @FXML
    private Label blockedUsers;

    @FXML
    private Button goToDirect;

    @FXML
    private Button goToSearch;

    @FXML
    private Button goToTimeline;

    @FXML
    private Button newTweet;

    @FXML
    private Label numberOfTweets = new Label(Login.user.getMessages().size() + "");

    private Stage stage;
    private Scene scene;

    @FXML
    void editProfile(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editProfileScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Edit Profile");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addTweet(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTweetScene.fxml"));

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Add Tweet");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void goToDirectScene(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("directScene.fxml"));

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Direct");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToSearchScene(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchScene.fxml"));

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Direct");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToTimelineScene(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("timelineScene.fxml"));

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Direct");
        stage.setScene(scene);
        stage.show();
    }
}
