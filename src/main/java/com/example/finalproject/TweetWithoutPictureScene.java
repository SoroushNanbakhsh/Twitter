package com.example.finalproject;

import com.example.finalproject.Models.TextMessage;
import com.example.finalproject.Models.Tweet;
import com.example.finalproject.entry.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TweetWithoutPictureScene {

    @FXML
    private TextArea text;

    @FXML
    private Button tweetButton;

    @FXML
    private TextField tweetText;
    private Stage stage = new Stage();
    private Scene scene;

    @FXML
    void tweet(ActionEvent event) {
        TextMessage textMessage = new TextMessage(tweetText.getText(), Login.user);
        Tweet.addTweet(textMessage);

        goToProfileScene();
    }

    private void goToProfileScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfileScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Profile");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
