package com.collab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessagesMenu extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView groupRecycleView;
    ArrayList<Group> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        messageList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            messageList.add(ds.getValue(Group.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(getApplicationContext(), messageList);
                        groupRecycleView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void goToGroupsMenu(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), GroupsMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void goToExploreMenu(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), ExploreMain.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void goToMoreMenu(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), MoreMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }
}