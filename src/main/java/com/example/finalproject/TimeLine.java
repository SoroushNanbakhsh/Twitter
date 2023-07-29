package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class TimeLine{
    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    private ScrollPane scrollPane;
    private VBox tweetContent;
    public void initialize(){
        tweetContent = new VBox();
        scrollPane.setContent(tweetContent);
        tweetContent.setSpacing(20);
        tweetContent.setStyle("-fx-background-color: white;" + "-fx-padding: 15");
        showTweet();
    }
    public void showTweet(){
        for (Tweet tweet: TwitterApplication.tweets) {
            TweetComponent tweetComponent = new TweetComponent(tweet);
            tweetContent.getChildren().addAll(tweetComponent);
        }
    }
    public void switchToSearchScene(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("searchScene.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
    }
    public void switchToProfileScene(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("searchScene.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
    }
}
