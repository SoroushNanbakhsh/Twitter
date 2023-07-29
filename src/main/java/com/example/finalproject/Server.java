package com.example.finalproject;


import com.example.finalproject.File.FileAccessor;
import com.example.finalproject.Models.Command;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Server {
    public static ArrayList<Thread> threads = new ArrayList<>();

    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server is starting ...");
            System.out.println("=======================================================");

            FileAccessor.insertToLog(new Command("Server started", null, null, formatter.format(new Date())));

            int counter = 0;
            while(true){
                Socket client = serverSocket.accept();

                System.out.println("Client N" + ++counter + " | " + "Port: " + client.getPort() + " | " + "Inet: " + client.getInetAddress() + " | joined");
                FileAccessor.insertToLog(new Command("Client N" + counter + "left", null, null, formatter.format(new Date())));

                Thread thread = new Thread(new ClientHandler(client, counter));
                threads.add(thread);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
