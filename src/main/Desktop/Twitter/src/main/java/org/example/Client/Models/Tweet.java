package org.example.Client.Models;


import org.example.Client.Models.RequestAndResponse.Request;
import org.example.Client.Models.RequestAndResponse.RequestType;
import org.example.Client.Models.RequestAndResponse.Response;
import org.example.Client.Models.RequestAndResponse.ResponseStatus;
import org.example.Client.RequestAndReponse.Communicate;
import org.example.Client.Terminal.Menu;
import org.example.Client.entry.Login;

public abstract class Tweet {

    public static boolean addTweet(){
        TextMessage textMessage = Menu.addTweet();
        Login.user.addMessage(textMessage);
        Hashtag.tokenize(textMessage);

        Request request = new Request(RequestType.ADD_TWEET, Login.user);
        Response response;

        response = Communicate.communicate(request);

        return response.getResponseStatus().equals(ResponseStatus.ADD_TWEET_VALID);
    }

    public static void reply(TextMessage textMessage, TextMessage repliedTextMessage, User user){
        repliedTextMessage.getReplies().put(textMessage, user);
        user.addReply(textMessage, repliedTextMessage);
    }
    public static void like(TextMessage textMessage, User user) {
        textMessage.getLikesByUsers().add(user);
        int likes = textMessage.getLikes();
        likes++;
        textMessage.setLikes(likes);
    }
}
