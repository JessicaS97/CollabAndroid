package com.collab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExploreMain extends AppCompatActivity {

    ConstraintLayout exploreMenuLayout;
    LinearLayout searchFilterExplore;
    EditText editTextTextPersonName2;
    DatabaseReference databaseReference;
    RecyclerView groupRecycleView;
    ArrayList<Group> groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_main);

        exploreMenuLayout = findViewById(R.id.exploreMainLayout);
        searchFilterExplore = findViewById(R.id.searchFilterExplore);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        databaseReference = FirebaseDatabase.getInstance().getReference("groups");
        groupRecycleView = findViewById(R.id.linearLayout);
        groupRecycleView.setHasFixedSize(true);
        groupRecycleView.setLayoutManager(new LinearLayoutManager(this));

        exploreMenuLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clearFocus(view);
            }
        });

        searchFilterExplore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String searchText = editTextTextPersonName2.getText().toString();
                firebaseGroupSearch(searchText);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        groupList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            groupList.add(ds.getValue(Group.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(getApplicationContext(), groupList);
                        groupRecycleView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        if (editTextTextPersonName2 != null) {
            editTextTextPersonName2.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    firebaseGroupSearch(editTextTextPersonName2.getText().toString());
                    return true;
                }
            });
        }
    }

    private void firebaseGroupSearch(String searchText) {
        Query firebaseSearchQuery = databaseReference.orderByChild("groupName").startAt(searchText).endAt(searchText + "\uf8ff");
        Log.i("Search", searchText);
        ArrayList<Group> resultsGroupList = new ArrayList<>();
        for (Group group : groupList) {
            if (group.getGroupName().toLowerCase().contains(searchText.toLowerCase())) {
                resultsGroupList.add(group);
            }
        }
        AdapterClass adapterClass = new AdapterClass(getApplicationContext(), groupList);
        groupRecycleView.setAdapter(adapterClass);
    }

    public void categorySelected(View view) {
        Button selectedInterest = (Button)findViewById(view.getId());
        float currentAlpha = selectedInterest.getAlpha();
        if (currentAlpha == 0.5) {
            selectedInterest.setAlpha(1);
        } else {
            selectedInterest.setAlpha((float) 0.5);
        }
    }

    public void goToGroupsMenu(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), GroupsMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void goToMessagesMenu(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), MessagesMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void goToMoreMenu(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), MoreMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void clearFocus(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focusedView = getCurrentFocus();

        if (focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
            focusedView.clearFocus();
        }
    }
}