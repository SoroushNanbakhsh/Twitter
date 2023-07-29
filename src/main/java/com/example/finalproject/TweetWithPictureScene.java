package com.example.finalproject;

import com.example.finalproject.Models.TextMessage;
import com.example.finalproject.Models.Tweet;
import com.example.finalproject.entry.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TweetWithPictureScene {

    @FXML
    private Text pathError;

    @FXML
    private TextField pathOfImageFlied;

    @FXML
    private Button tweetButton;

    @FXML
    private TextField tweetTextFlied;

    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    void addTweet(ActionEvent event) {
        File file;

        do{
            file = new File(pathOfImageFlied.getText());

            if(!file.exists()) {
                pathError.setVisible(true);
            }
            else {
                pathError.setVisible(false);
            }
        } while(!file.exists());

        boolean result;
        do {
            result = UserController.userAvatarChecker(file);

            if(!result) {
                pathError.setVisible(true);
            }
            else {
                pathError.setVisible(false);
            }
        }while(!result);

        TextMessage textMessage = new TextMessage(tweetTextFlied.getText(), Login.user);
        textMessage.setImage(new Image(pathOfImageFlied.getText()));
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
