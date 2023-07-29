package com.example.finalproject.RequestAndReponse;


import com.example.finalproject.ClientSocket.ClientSocket;
import com.example.finalproject.Models.RequestAndResponse.Request;
import com.example.finalproject.Models.RequestAndResponse.Response;

import java.io.IOException;

public class Communicate {
    public static Response communicate(Request request) {
        Response response;

        try {
            ClientSocket.oos.flush();
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
