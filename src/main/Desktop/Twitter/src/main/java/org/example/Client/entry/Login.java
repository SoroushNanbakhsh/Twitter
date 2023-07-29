package org.example.Client.entry;

import org.example.Client.Models.RequestAndResponse.Request;
import org.example.Client.Models.RequestAndResponse.RequestType;
import org.example.Client.Models.RequestAndResponse.Response;
import org.example.Client.Models.RequestAndResponse.ResponseStatus;
import org.example.Client.RequestAndReponse.Communicate;
import org.example.Client.Terminal.Menu;
import org.example.Client.Models.User;

public class Login {
    public static User user;
    public static boolean loginProcess() {
        // TODO add token which user can login without entering password and username

        String[] usernamePassword = Menu.printLoginPage();
        user.setUsername(usernamePassword[0]);
        user.setPassword(usernamePassword[1]);

        Request request = new Request(RequestType.LOG_IN, user);
        Response response;

        response = Communicate.communicate(request);

        if(response.getResponseStatus().equals(ResponseStatus.LOG_IN_INVALID)) {
            return false;
        }
        else if(response.getResponseStatus().equals(ResponseStatus.LOG_IN_VALID)) {
            user = (User) response.getData();
        }

        return true;
    }
}
