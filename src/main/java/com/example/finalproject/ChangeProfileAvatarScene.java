package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ChangeProfileAvatarScene {

    @FXML
    private Button changeButton;

    @FXML
    private TextField newImagePath;

    @FXML
    private Text pathError;

    @FXML
    private TextArea text;
    private Stage stage = new Stage();
    private Scene scene;

    @FXML
    void changeAvatar(ActionEvent event) {
        File file;

        do{
            file = new File(newImagePath.getText());

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

        Profile.changeProfileAvatar(new Image(newImagePath.getText()));

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
