package com.collab;

import java.util.Map;

public class MessageSend extends Message {
    private Map time;

    public MessageSend() {
    }

    public MessageSend(Map time) {
        this.time = time;
    }

    public MessageSend(String message, String name, String profilePic, String message_type, Map time) {
        super(message, name, profilePic, message_type);
        this.time = time;
    }

    public MessageSend(String message, String urlPhoto, String name, String profilePic, String message_type, Map time) {
        super(message, urlPhoto, name, profilePic, message_type);
        this.time = time;
    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }
}
