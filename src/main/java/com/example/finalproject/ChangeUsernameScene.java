package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeUsernameScene {

    @FXML
    private Button changeButton;

    @FXML
    private Text errorText;

    @FXML
    private TextField newUsernameTextField;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    void changeUsername(ActionEvent event) {
        boolean result;
        do {
            result = UserController.userUsernameChecker(newUsernameTextField.getText());

            if(!result) {
                errorText.setVisible(true);
            }
            else {
                errorText.setVisible(false);
            }
        }while(!result);

        Profile.changeUsername(newUsernameTextField.getText());

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
