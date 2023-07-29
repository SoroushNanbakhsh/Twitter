package org.example.Client;

import org.example.Client.ClientSocket.ClientSocket;
import org.example.Client.Terminal.Menu;
import org.example.Client.Utils.Profile;
import org.example.Client.Utils.Search;
import org.example.Client.entry.Login;
import org.example.Client.entry.Signup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class App {
    Scanner input = new Scanner(System.in);

    public void startProcess() throws Exception {
        try {
            ClientSocket.clientSocket = new Socket("localhost", 8080);

            ClientSocket.oos = new ObjectOutputStream(ClientSocket.clientSocket.getOutputStream());
            ClientSocket.ois = new ObjectInputStream(ClientSocket.clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean result = false;
        Thread thread = new Thread(new Listener());
        thread.start();

        while (!result) {

            Menu.printFirstPage();

            int option = input.nextInt();

            if (option == 1) {
                result = Login.loginProcess();
            } else if (option == 2) {
                result = Signup.signupProcess();
            }
        }

        while(true) {
            int choice = Menu.homePage();

            switch (choice) {
                case 1 -> Timeline.startProcess();
                case 2 -> Search.startProcess();
                case 4 -> Profile.startProcess();
                default -> Menu.invalidChoice();
            }
        }
    }
}
