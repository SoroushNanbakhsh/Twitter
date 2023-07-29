package com.example.finalproject.entry;


import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.RequestType;
import com.example.finalproject.Models.RequestAndResponse.Response;
import com.example.finalproject.Models.RequestAndResponse.ResponseStatus;
import com.example.finalproject.Models.User;
import com.example.finalproject.RequestAndReponse.Communicate;
import com.example.finalproject.Terminal.Menu;
import javafx.scene.image.Image;


import java.io.File;

public class Signup {
    public static boolean signupProcess(String username, String password, String firstname, String lastname,
                                        String email, String phoneNumber, String country, String birthdate, Image profile) {
        User user;
        String[] userDetails = {username, password, firstname, lastname, email, phoneNumber, country, birthdate};

//        int avatarOption;
//        do {
//            avatarOption = Menu.avatarOption();
//        } while (!(avatarOption == 1 || avatarOption == 2));
//
//        if(avatarOption == 1) {
//            user = new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4], userDetails[5], userDetails[6], userDetails[7], profile);
//        }
//        else {
//            user = new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4], userDetails[5], userDetails[6], userDetails[7], null);
//        }
        user = new User(username, password, firstname, lastname, email, phoneNumber, country, birthdate, profile);

        Request request = new Request(RequestType.SIGN_UP, user);
        Response response;

        response = Communicate.communicate(request);

        return response.getResponseStatus().equals(ResponseStatus.SIGNUP_VALID);
    }
}
