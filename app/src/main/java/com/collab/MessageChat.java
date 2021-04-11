package com.collab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageChat extends AppCompatActivity {

    ImageView sendMessageButton;
    EditText textMessage;
    CircleImageView profilePicUser;
    TextView userName;
    RecyclerView messageRecyclerView;
    ImageView sendImageButton;

    AdapterMessageChat adapter;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_chat);

        profilePicUser = findViewById(R.id.profilePicMessage);
        userName = findViewById(R.id.userNameMessageChat);
        textMessage = findViewById(R.id.sendMessageText);
        sendMessageButton = findViewById(R.id.sendMessageButton);
        messageRecyclerView = findViewById(R.id.messagesRecyclerView);
        sendImageButton = findViewById(R.id.sendImageButton);

        adapter = new AdapterMessageChat(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(linearLayoutManager);
        messageRecyclerView.setAdapter(adapter);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new Message(textMessage.getText().toString(), userName.getText().toString(), "", "1", "00:00"));
                textMessage.setText("");
            }
        });

        sendImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Select an image"), 1);
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message message = snapshot.getValue(Message.class);
                adapter.addMessage(message);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setScrollbar() {
        messageRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    public void goBackToMessagesPage(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), MessagesMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }
}
