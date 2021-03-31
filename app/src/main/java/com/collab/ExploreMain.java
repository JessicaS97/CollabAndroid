package com.collab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ExploreMain extends AppCompatActivity {

    ConstraintLayout exploreMenuLayout;
    LinearLayout searchFilterExplore;
    EditText editTextTextPersonName2;
    DatabaseReference databaseReference;
    RecyclerView groupRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_main);

        exploreMenuLayout = (ConstraintLayout) findViewById(R.id.exploreMainLayout);
        searchFilterExplore = (LinearLayout) findViewById(R.id.searchFilterExplore);
        editTextTextPersonName2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        databaseReference = FirebaseDatabase.getInstance().getReference("groups");
        groupRecycleView = findViewById(R.id.linearLayout);

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

    public class GroupsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public GroupsViewHolder(View view) {
            super(view);
            mView = view;
        }

        public void setDetails(String groupName, String groupMemberSize) {
            TextView groupNameRetrieved = mView.findViewById(R.id.groupName);
            TextView groupSizeRetrieved = mView.findViewById(R.id.membersNumber);
            ImageView authorImage = mView.findViewById(R.id.profilePic);

            groupNameRetrieved.setText(groupName);
            groupSizeRetrieved.setText(groupMemberSize);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(authorImage);
        }
    }

    private void firebaseGroupSearch(String searchText) {
        Query firebaseSearchQuery = databaseReference.orderByChild("groupName").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<Group> options =
                new FirebaseRecyclerOptions.Builder<Group>()
                        .setQuery(databaseReference, Group.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Group, GroupsViewHolder>(options) {
            @Override
            public GroupsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_explore_main, parent, false);

                return new GroupsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(GroupsViewHolder holder, int position, Group model) {
                holder.setDetails(model.getGroupName(), model.getGroupSize());
            }
        };
        groupRecycleView.setAdapter(adapter);
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