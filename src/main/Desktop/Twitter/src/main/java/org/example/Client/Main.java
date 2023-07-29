package org.example.Client;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        try {
            app.startProcess();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}