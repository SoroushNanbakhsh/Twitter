package com.example.finalproject;

import com.example.finalproject.entry.Signup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AvatarScene {

    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    private TextField path;

    @FXML
    void finish(ActionEvent actionEvent) throws IOException {
        Signup.signupProcess(SignupScene.information.get(0), PasswordController.information.get(0), SignupScene.information.get(1), SignupScene.information.get(2), SignupScene.information.get(3), SignupScene.information.get(4), SignupScene.information.get(5), SignupScene.information.get(6), new Image(path.getText()));
        for (int i = SignupScene.information.size() - 1; i >= 0 ; i--) {
            SignupScene.information.remove(i);
        }
        for (int i = PasswordController.information.size() - 1; i >= 0 ; i--) {
            PasswordController.information.remove(i);
        }
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
    }

}
