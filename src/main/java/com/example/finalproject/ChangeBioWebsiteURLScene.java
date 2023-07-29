package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeBioWebsiteURLScene {

    @FXML
    private Button changeButton;

    @FXML
    private TextField newWebsiteURL;

    private Stage stage = new Stage();
    private Scene scene;

    @FXML
    void changeBioWebsiteURL(ActionEvent event) {
        Profile.changeBioWebsiteURL(newWebsiteURL.getText());

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
