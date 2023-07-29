package com.example.finalproject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

public class TweetComponent extends AnchorPane {
    private static Image redHeartImage;

    static {
        try {
            redHeartImage = new Image(TweetComponent.class.getResource("heart-romantic-love-graphic-vector.jpg").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static Image heartImage;

    static {
        try {
            heartImage = new Image(TweetComponent.class.getResource("03 - Classes and Objects.png").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static Image quoteImage;

    static {
        try {
            quoteImage = new Image(TweetComponent.class.getResource("quote.png").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static Image retweetImage;

    static {
        try {
            retweetImage = new Image(TweetComponent.class.getResource("png-transparent-back-front-sets-twitter-icon.png").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private ImageView heartImageView = new ImageView(heartImage);
    private ImageView quoteImageView = new ImageView(quoteImage);
    private ImageView retweetImageView = new ImageView(retweetImage);
    private Circle profileCircle;
    private Label usernameLabel;
    private Label userIdLabel;
    private TextArea textArea;
    private ImageView tweetImage;
    private Label dateLabel;
    private Separator separator1;
    private Separator separator2;
    private Label countOfRetweet;
    private Label retweetLabel;
    private Label countOfQuote;
    private Label quoteLabel;
    private Label countOfLike;
    private Label likeLabel;
    private Button quoteButton;
    private Button retweetButton;
    private Button likeButton;
    private Tweet tweet;
    private Rectangle rectangle;
    private TextField retweetTextField;
    public TweetComponent(Tweet tweet){
        this.tweet = tweet;
        usernameLabel = new Label(tweet.getUser().getFirstName() + tweet.getUser().getLastName());
        userIdLabel = new Label(tweet.getUser().getUsername());
        textArea = new TextArea(tweet.getTextMessage());
        tweetImage = new ImageView(tweet.getImage());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mma.MM/dd/yy");
//        dateLabel = new Label(simpleDateFormat.format(tweet.getDate()));
        countOfRetweet = new Label(Integer.toString(tweet.getRetweets()));
        countOfQuote = new Label(Integer.toString(tweet.getCountOfQuotes()));
        countOfLike = new Label(Integer.toString(tweet.getLikes()));
        retweetLabel = new Label("Retweets");
        quoteLabel = new Label("Quote Tweets");
        likeLabel = new Label("Likes");
        quoteButton = new Button();
        retweetButton = new Button();
        likeButton = new Button();
        profileCircle = new Circle(50);
        separator1 = new Separator();
        separator2 = new Separator();
        retweetTextField = new TextField();
        this.getChildren().addAll(usernameLabel, userIdLabel, textArea, tweetImage, countOfRetweet, countOfQuote,
                countOfLike, retweetLabel, quoteLabel, likeLabel, quoteButton, retweetButton, likeButton, profileCircle,
                separator1, separator2);
//        bindCountLabel();
        setConfig();
        setLocation();
        setAction();
    }
    private void bindCountLabel(){
        countOfRetweet.textProperty().bind(new SimpleIntegerProperty(tweet.getRetweets()).asString());
        countOfQuote.textProperty().bind(new SimpleIntegerProperty(tweet.getCountOfQuotes()).asString());
        countOfLike.textProperty().bind(new SimpleIntegerProperty(tweet.getLikes()).asString());
    }
    private void setConfig(){
        this.setPrefSize(600, 400);
        this.setStyle("-fx-background-color: #033783;" +
                "-fx-background-radius: 12;" + "-fx-border-radius: 12");
        profileCircle.setFill(new ImagePattern(tweet.getUser().getPicture()));
        profileCircle.setRadius(50);
        usernameLabel.setFont(Font.font("serif", FontWeight.BOLD, 30));
        usernameLabel.setStyle("-fx-text-fill: white");
        userIdLabel.setFont(Font.font("serif", FontWeight.NORMAL, 25));
        userIdLabel.setStyle("-fx-text-fill: gray");
        textArea.setPrefSize(280, 185);
        textArea.setMaxWidth(280);
        textArea.setMaxHeight(185);
        textArea.setMinWidth(280);
        textArea.setMinHeight(185);
        textArea.setEditable(false);
        textArea.setStyle("-fx-text-fill: white");
        textArea.setStyle("-fx-background-color: #033783");
        textArea.setStyle("-fx-border-color: #033783");
        textArea.setStyle("-fx-progress-color: #033783");
        textArea.setStyle("-fx-color-label-visible: #033783");
        textArea.setFont(Font.font("serif", FontWeight.NORMAL, 15));
        tweetImage.setFitWidth(255);
        tweetImage.setFitHeight(185);
        rectangle = new Rectangle(255, 185);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        tweetImage.setClip(rectangle);
//        dateLabel.setFont(Font.font("serif", FontWeight.NORMAL, 13));
//        dateLabel.setStyle("-fx-text-fill: gray");
        separator1.setPrefSize(550, 1);
        separator1.setStyle("-fx-background-color: black");
        separator2.setPrefSize(550, 1);
        separator2.setStyle("-fx-background-color: black");
        separator1.setOpacity(0.15);
        separator2.setOpacity(0.15);
        retweetImageView.setFitWidth(30);
        retweetImageView.setFitHeight(20);
        retweetButton.setGraphic(retweetImageView);
        retweetButton.setPrefSize(30, 20);
        retweetButton.setStyle("-fx-background-radius: 50");
        countOfRetweet.setFont(Font.font("serif", FontWeight.BOLD, 14));
        countOfRetweet.setStyle("-fx-background-color: white");
        retweetLabel.setFont(Font.font("serif", FontWeight.NORMAL, 14));
        retweetLabel.setStyle("-fx-background-color: gray");

        quoteImageView.setFitWidth(30);
        quoteImageView.setFitHeight(20);
        quoteButton.setGraphic(quoteImageView);
        quoteButton.setPrefSize(30, 20);
        quoteButton.setStyle("-fx-background-radius: 50");
        countOfQuote.setFont(Font.font("serif", FontWeight.BOLD, 14));
        countOfQuote.setStyle("-fx-background-color: white");
        quoteLabel.setFont(Font.font("serif", FontWeight.NORMAL, 14));
        quoteLabel.setStyle("-fx-background-color: gray");

        heartImageView.setFitWidth(30);
        heartImageView.setFitHeight(20);
        likeButton.setGraphic(heartImageView);
        likeButton.setPrefSize(30, 20);
        likeButton.setStyle("-fx-background-radius: 50");
        countOfLike.setFont(Font.font("serif", FontWeight.BOLD, 14));
        countOfLike.setStyle("-fx-background-color: white");
        likeLabel.setFont(Font.font("serif", FontWeight.NORMAL, 14));
        likeLabel.setStyle("-fx-background-color: gray");
    }
    private void setLocation(){
        AnchorPane.setLeftAnchor(usernameLabel, 130.0);
        AnchorPane.setTopAnchor(usernameLabel, 15.0);
        AnchorPane.setTopAnchor(profileCircle, 15.0);
        AnchorPane.setLeftAnchor(profileCircle, 15.0);
        AnchorPane.setLeftAnchor(userIdLabel, 135.0);
        AnchorPane.setTopAnchor(userIdLabel, 50.0);
        AnchorPane.setLeftAnchor(textArea, 20.0);
        AnchorPane.setTopAnchor(textArea, 120.0);
        AnchorPane.setLeftAnchor(tweetImage, 310.0);
        AnchorPane.setTopAnchor(tweetImage, 120.0);
//        AnchorPane.setLeftAnchor(dateLabel, 20.0);
//        AnchorPane.setTopAnchor(dateLabel, 310.0);

        AnchorPane.setLeftAnchor(separator1, 20.0);
        AnchorPane.setTopAnchor(separator1, 330.0);
        AnchorPane.setLeftAnchor(separator2, 20.0);
        AnchorPane.setTopAnchor(separator2, 355.0);
        AnchorPane.setLeftAnchor(countOfRetweet, 20.0);
        AnchorPane.setTopAnchor(countOfRetweet, 335.0);
        AnchorPane.setLeftAnchor(retweetLabel, 50.0);
        AnchorPane.setTopAnchor(retweetLabel, 335.0);
        AnchorPane.setLeftAnchor(countOfQuote, 150.0);
        AnchorPane.setTopAnchor(countOfQuote, 335.0);
        AnchorPane.setLeftAnchor(quoteLabel, 180.0);
        AnchorPane.setTopAnchor(quoteLabel, 335.0);
        AnchorPane.setLeftAnchor(countOfLike, 280.0);
        AnchorPane.setTopAnchor(countOfLike, 335.0);
        AnchorPane.setLeftAnchor(likeLabel, 310.0);
        AnchorPane.setTopAnchor(likeLabel, 335.0);
        AnchorPane.setLeftAnchor(quoteButton, 20.0);
        AnchorPane.setTopAnchor(quoteButton, 360.0);
        AnchorPane.setLeftAnchor(retweetButton, 120.0);
        AnchorPane.setTopAnchor(retweetButton, 360.0);
        AnchorPane.setLeftAnchor(likeButton, 220.0);
        AnchorPane.setTopAnchor(likeButton, 360.0);
    }
    private void setAction(){
        quoteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tweet.setCountOfQuotes(tweet.getCountOfQuotes() + 1);
                countOfQuote.setText(Integer.toString(tweet.getCountOfQuotes()));
            }
        });

        retweetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tweet.setRetweets(tweet.getRetweets() + 1);
                countOfRetweet.setText(Integer.toString(tweet.getRetweets()));
                retweetTextField();
            }
        });

        likeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tweet.setLikes(tweet.getLikes() + 1);
                countOfLike.setText(Integer.toString(tweet.getLikes()));
                heartImageView.setImage(redHeartImage);
            }
        });
    }
    private void retweetTextField(){
        retweetTextField.setFont(Font.font("serif", FontWeight.NORMAL, 15));
        retweetTextField.setPrefSize(179.0, 25.0);
        AnchorPane.setTopAnchor(retweetTextField, 330.0);
        AnchorPane.setLeftAnchor(retweetTextField, 221.0);
    }
}
