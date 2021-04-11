package com.collab;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageChat extends AppCompatActivity {

    ImageView sendMessageButton;
    TextView textMessage;
    CircleImageView profilePicUser;
    TextView userName;
    RecyclerView messageRecyclerView;

    AdapterMessageChat adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_chat);

        profilePicUser = findViewById(R.id.profilePicMessage);
        userName = findViewById(R.id.userNameMessage);
        textMessage = findViewById(R.id.sendMessageText);
        sendMessageButton = findViewById(R.id.sendMessageButton);
        messageRecyclerView = findViewById(R.id.messagesRecyclerView);

        adapter = new AdapterMessageChat(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(linearLayoutManager);
        messageRecyclerView.setAdapter(adapter);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adapter.addMessage(new Message(textMessage.getText().toString(), userName.getText().toString(), "", "1", "00:00"));
            }
        });
    }

    public void goBackToMessagesPage(View view) {

    }
}
