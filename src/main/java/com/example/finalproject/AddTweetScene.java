package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTweetScene {

    @FXML
    private Button tweetWithPicButton;

    @FXML
    private Button tweetWithoutPictureButton;

    private Stage stage = new Stage();
    private Scene scene;

    @FXML
    void tweetWithPic(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TweetWithPictureScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Add Tweet");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tweetWithoutPic(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TweetWithoutPictureScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Add Tweet");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
