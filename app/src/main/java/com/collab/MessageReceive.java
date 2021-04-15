package com.collab;

public class MessageReceive extends Message {
    private Long hour;

    public MessageReceive() {
    }

    public MessageReceive(Long hour) {
        this.hour = hour;
    }

    public MessageReceive(String message, String urlPhoto, String name, String profilePic, String message_type, Long hour) {
        super(message, urlPhoto, name, profilePic, message_type);
        this.hour = hour;
    }

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }
}
