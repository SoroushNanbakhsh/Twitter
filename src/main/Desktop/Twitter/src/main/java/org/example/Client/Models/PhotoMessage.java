package org.example.Client.Models;

import org.example.Client.Models.TextMessage;

import java.io.File;

public class PhotoMessage extends TextMessage {
    private File file;

    public PhotoMessage(String textMessage, File file) {
        super(textMessage);
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
