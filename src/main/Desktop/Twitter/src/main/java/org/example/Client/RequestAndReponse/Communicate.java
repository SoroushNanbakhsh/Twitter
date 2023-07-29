package org.example.Client.RequestAndReponse;

import org.example.Client.ClientSocket.ClientSocket;
import org.example.Client.Models.RequestAndResponse.Request;
import org.example.Client.Models.RequestAndResponse.Response;

import java.io.IOException;

public class Communicate {
    public static Response communicate(Request request) {
        Response response;

        try {
            ClientSocket.oos.writeObject(request);
            response = (Response) ClientSocket.ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
