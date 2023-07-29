package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchScene {
    @FXML
    private ScrollPane scrollPane;
    private VBox profileContent;
    public void initialize(){
        profileContent = new VBox();
        scrollPane.setContent(profileContent);
        profileContent.setSpacing(20);
        profileContent.setStyle("-fx-background-color: white;" + "-fx-padding: 15");
    }
}
