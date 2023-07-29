package com.example.finalproject;

import com.example.finalproject.entry.Signup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AvatarOptionScene {
    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    void finish(ActionEvent actionEvent) throws IOException {
        Signup.signupProcess(SignupScene.information.get(0), PasswordController.information.get(0), SignupScene.information.get(1), SignupScene.information.get(2), SignupScene.information.get(3), SignupScene.information.get(4), SignupScene.information.get(5), SignupScene.information.get(6), null);
        SignupScene.information = new ArrayList<>();
        PasswordController.information = new ArrayList<>();
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        System.out.println("jvndjvsd");
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void yesButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("avatarScene.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
    }

}
