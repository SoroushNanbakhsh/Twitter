package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class editProfileScene {
    @FXML
    private Button changeBioButton;

    @FXML
    private Button changeFirstnameButton;

    @FXML
    private Button changeHeaderButton;

    @FXML
    private Button changeLastnameButton;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button changeProfileAvatarButton;

    @FXML
    private Button changeUsernameButton;

    private Stage stage = new Stage();
    private Scene scene;

    @FXML
    void changeBioScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeBioScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("change Bio");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void changeFirstnameScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeFirstnameScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Change Firstname");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void changeLastnameScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeLastnameScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Change Lastname");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void changePasswordScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePasswordScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Change Password");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void changeUsernameScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeUsernameScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Change Username");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void changeProfileAvatar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeProfileAvatarScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Change Avatar");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void changeHeader(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeHeaderScene.fxml"));
            scene = new Scene(fxmlLoader.load());

            stage.setTitle("Change Header");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
