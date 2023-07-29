package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeBioScene {

    @FXML
    private Button changeBioLocationButton;

    @FXML
    private Button changeBioTextButton;

    @FXML
    private Button changeBioWebsiteURLButton;

    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    void changeBioLocation(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeBioLocationScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Change Bio Location");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void changeBioText(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeBioTextScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Change Bio Text");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void changeBioWebsiteURL(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeBioWebsiteURLScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Change Bio Website URL");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
