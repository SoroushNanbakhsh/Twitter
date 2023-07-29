package com.example.finalproject;

import com.example.finalproject.Models.User;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProfileComponent extends AnchorPane {
    private Circle profileCircle;
    private Label usernameLabel;
    private Label userIdLabel;
    private User user;
    private Label bio;
    private Label location;
    private Label websiteURL;
    private Label countOfFollowings;
    private Label followings;
    private Label countOfFollowers;
    private Label followers;
    public ProfileComponent(User user){
        this.user = user;
        usernameLabel = new Label(user.getFirstName() + user.getLastName());
        userIdLabel = new Label(user.getUsername());
        bio = new Label(user.getBio().getText());
        location = new Label(user.getBio().getLocation());
        websiteURL = new Label(user.getBio().getWebsiteURL());
        profileCircle = new Circle(50);
        countOfFollowings = new Label(Integer.toString(user.getFollowings().size()));
        followings = new Label("followings");
        countOfFollowers = new Label(Integer.toString(user.getFollowers().size()));
        followers = new Label("followers");
        this.getChildren().addAll(usernameLabel, userIdLabel, bio, location, websiteURL, profileCircle);
        setConfig();
        setLocation();
    }
    private void setConfig(){
        this.setPrefSize(600, 400);
        this.setStyle("-fx-background-color: black;" +
                "-fx-background-radius: 12;" + "-fx-border-radius: 12");
        usernameLabel.setFont(Font.font("serif", FontWeight.BOLD, 25));
        usernameLabel.setStyle("-fx-text-fill: black");
        profileCircle.setFill(new ImagePattern(user.getPicture()));
        profileCircle.setRadius(50);
        userIdLabel.setFont(Font.font("serif", FontWeight.NORMAL, 20));
        userIdLabel.setStyle("-fx-text-fill: black");
        bio.setFont(Font.font("serif", FontWeight.NORMAL, 25));
        bio.setStyle("-fx-text-fill: black");
        countOfFollowings.setFont(Font.font("serif", FontWeight.BOLD, 17));
        countOfFollowings.setStyle("-fx-text-fill: black");
        followings.setFont(Font.font("serif", FontWeight.NORMAL, 17));
        followings.setStyle("-fx-text-fill: black");
        countOfFollowers.setFont(Font.font("serif", FontWeight.BOLD, 17));
        countOfFollowers.setStyle("-fx-text-fill: black");
        followings.setFont(Font.font("serif", FontWeight.NORMAL, 17));
        followings.setStyle("-fx-text-fill: black");
        location.setFont(Font.font("serif", FontWeight.NORMAL, 17));
        location.setStyle("-fx-text-fill: black");
        websiteURL.setFont(Font.font("serif", FontWeight.NORMAL, 17));
        websiteURL.setStyle("-fx-text-fill: black");
    }
    private void setLocation(){
        AnchorPane.setLeftAnchor(profileCircle, 20.0);
        AnchorPane.setTopAnchor(profileCircle, 15.0);
        AnchorPane.setLeftAnchor(usernameLabel, 170.0);
        AnchorPane.setTopAnchor(usernameLabel, 35.0);
        AnchorPane.setLeftAnchor(userIdLabel, 170.0);
        AnchorPane.setTopAnchor(userIdLabel, 85.0);
        AnchorPane.setLeftAnchor(bio, 20.0);
        AnchorPane.setTopAnchor(bio, 135.0);
        AnchorPane.setLeftAnchor(countOfFollowings, 20.0);
        AnchorPane.setTopAnchor(countOfFollowings, 200.0);
        AnchorPane.setLeftAnchor(followings, 70.0);
        AnchorPane.setTopAnchor(followings, 200.0);
        AnchorPane.setLeftAnchor(countOfFollowers, 170.0);
        AnchorPane.setTopAnchor(countOfFollowers, 200.0);
        AnchorPane.setLeftAnchor(followers, 220.0);
        AnchorPane.setTopAnchor(followers, 200.0);
        AnchorPane.setLeftAnchor(location, 20.0);
        AnchorPane.setTopAnchor(location, 265.0);
        AnchorPane.setLeftAnchor(websiteURL, 20.0);
        AnchorPane.setTopAnchor(websiteURL, 325.0);
    }
}
