package com.example.finalproject;

import com.example.finalproject.entry.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePasswordScene {

    @FXML
    private Button changeButton;

    @FXML
    private TextField newPassword;

    @FXML
    private Text newPasswordError;

    @FXML
    private TextField previousPassword;

    @FXML
    private Text previousPasswordError;

    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    void changePassword(ActionEvent event) {
        int result;

        do{
            if (Login.user.getPassword().equals(previousPassword.getText())){
                result = 0;
            }
            else{
                result = 1;
            }

            if(!(result == 0)) {
                previousPasswordError.setVisible(true);
            }
            else {
                previousPasswordError.setVisible(false);
            }

        }while(result != 0);

        do {
            result = UserController.userPasswordChecker(newPassword.getText());

            if(!(result == 0)) {
                newPasswordError.setVisible(true);
            }
            else {
                newPasswordError.setVisible(false);
            }

        }while(result != 0);

        Profile.changePassword(newPassword.getText());

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
