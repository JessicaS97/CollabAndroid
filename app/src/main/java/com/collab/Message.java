package com.collab;

public class Message {
    private String message;
    private String name;
    private String profilePic;
    private String message_type;
    private String time;

    public Message() {
    }

    public Message(String message, String name, String profilePic, String message_type, String time) {
        this.message = message;
        this.name = name;
        this.profilePic = profilePic;
        this.message_type = message_type;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
