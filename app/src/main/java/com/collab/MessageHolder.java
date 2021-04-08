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
}
