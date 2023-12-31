package com.Twitter.HttpServer;

import com.Twitter.HttpServer.config.Configuration;
import com.Twitter.HttpServer.config.ConfigurationManager;
import com.Twitter.HttpServer.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 * Driver class for the Http Server
 *
 */

public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void run() {

        LOGGER.info("Server starting ...");

        ConfigurationManager.getInstance().loadConfigurationFile("/Users/soroushnanbakhsh/Desktop/AP-Final-Project/Server/TwitterServer/src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using Port: " + conf.getPort());
        LOGGER.info("Using webroot: " + conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();

        } catch (IOException e) {
            e.printStackTrace();
            // TODO handle later
        }
    }
}
