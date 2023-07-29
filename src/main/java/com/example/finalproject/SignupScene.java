package com.example.finalproject;

import com.example.finalproject.ClientSocket.ClientSocket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignupScene implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField username;
    @FXML
    private TextField email;

    public TextField getFirstname() {
        return firstname;
    }

    public TextField getLastname() {
        return lastname;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getEmail() {
        return email;
    }

    public TextField getPhoneNumber() {
        return phoneNumber;
    }


    public TextField getBirthdate() {
        return birthdate;
    }

    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField country;
    @FXML
    private TextField birthdate;
    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    private Text takenUsername;
    @FXML
    private Text invalidEmail;
    @FXML
    private Text signedUpEmail;
    @FXML
    private Text invalidPhoneNumber;
    @FXML
    private Text signedUpPhoneNumber;
    static ArrayList<String> information = new ArrayList<>();


    private ObservableList<String> temp = FXCollections.observableArrayList(Locale.getISOCountries());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.setItems(temp);
        choiceBox.setOnAction(this::setCountry);
    }
    public void switchToPasswordScene(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("passwordScene.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
    }

    public void usernameError(){
        if(!UserController.userUsernameChecker(username.getText())){
            takenUsername.setVisible(true);
        }
        else{
            takenUsername.setVisible(false);
            information.add(username.getText());
        }
    }
    public void setFirstname(){
        information.add(firstname.getText());
    }
    public void setLastname(){
        information.add(lastname.getText());
    }
    public void emailError(){
        if(UserController.userEmailChecker(email.getText()) == 1){
            invalidEmail.setVisible(true);
            email.clear();
        }
        else if(UserController.userEmailChecker(email.getText()) == 2){
            signedUpEmail.setVisible(true);
            email.clear();
        }
        else{
            invalidEmail.setVisible(false);
            signedUpEmail.setVisible(false);
            information.add(email.getText());
        }
    }
    public void phoneNumberError() {
        if (UserController.userPhoneNumberChecker(phoneNumber.getText()) == 1) {
            invalidPhoneNumber.setVisible(true);
            phoneNumber.clear();
        } else if (UserController.userEmailChecker(phoneNumber.getText()) == 2) {
            signedUpPhoneNumber.setVisible(true);
            phoneNumber.clear();
        } else {
            invalidPhoneNumber.setVisible(false);
            signedUpPhoneNumber.setVisible(false);
            information.add(phoneNumber.getText());
        }
    }

    public void setCountry(ActionEvent actionEvent){
        String count = choiceBox.getValue();
        information.add(count);
    }
    public void setBirthdate(){
        information.add(birthdate.getText());
    }
}
