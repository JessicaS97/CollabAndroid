package com.collab;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView message;
    private TextView time;
    private CircleImageView imageMessage;

    public MessageHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.userNameMessage);
        message = itemView.findViewById(R.id.message);
        time = itemView.findViewById(R.id.timeMessage);
        imageMessage = itemView.findViewById(R.id.profilePicMessage);
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getMessage() {
        return message;
    }

    public void setMessage(TextView message) {
        this.message = message;
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public CircleImageView getImageMessage() {
        return imageMessage;
    }

    public void setImageMessage(CircleImageView imageMessage) {
        this.imageMessage = imageMessage;
    }
}
