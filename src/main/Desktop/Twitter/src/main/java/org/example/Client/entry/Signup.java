package org.example.Client.entry;


import org.example.Client.Models.RequestAndResponse.Request;
import org.example.Client.Models.RequestAndResponse.RequestType;
import org.example.Client.Models.RequestAndResponse.Response;
import org.example.Client.Models.RequestAndResponse.ResponseStatus;
import org.example.Client.RequestAndReponse.Communicate;
import org.example.Client.Terminal.Menu;
import org.example.Client.Models.User;

import java.io.File;

public class Signup {
    public static boolean signupProcess() {
        User user;
        String[] userDetails = Menu.printSignupPage();

        int avatarOption;
        do {
            avatarOption = Menu.avatarOption();
        } while (!(avatarOption == 1 || avatarOption == 2));

        if(avatarOption == 1) {
            user = new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4], userDetails[5], userDetails[6], userDetails[7], new File(Menu.signupAvatarGetter()));
        }
        else {
            user = new User(userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4], userDetails[5], userDetails[6], userDetails[7], null);
        }

        Request request = new Request(RequestType.SIGN_UP, user);
        Response response;

        response = Communicate.communicate(request);

        return response.getResponseStatus().equals(ResponseStatus.SIGNUP_VALID);
    }
}
