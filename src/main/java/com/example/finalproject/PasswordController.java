package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PasswordController {
    static ArrayList<String> information = new ArrayList<>();

    @FXML
    private TextField confirmationPassword;

    @FXML
    private Text length;

    @FXML
    private TextField password;

    @FXML
    private Text uLCase;

    @FXML
    private Text wrong;
    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    void confirmationError(ActionEvent event) {
       if(!(password.getText().equals(confirmationPassword.getText()))){
           wrong.setVisible(true);
           confirmationPassword.clear();
       }
       else{
           wrong.setVisible(false);
           information.add(confirmationPassword.getText());
       }
    }

    @FXML
    void passwordError(ActionEvent event) {
        if(UserController.userPasswordChecker(password.getText()) == 1){
            length.setVisible(true);
            password.clear();
        }
        else if(UserController.userPasswordChecker(password.getText()) == 2){
            uLCase.setVisible(true);
            password.clear();
        }
        else{
            length.setVisible(false);
            uLCase.setVisible(false);
        }
    }

    @FXML
    void switchToSceneAvatarOptionScene(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("avatarOptionScene.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
    }

}
