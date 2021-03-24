package com.collab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class ExploreMain extends AppCompatActivity {

    ConstraintLayout exploreMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_main);

        exploreMenuLayout = (ConstraintLayout) findViewById(R.id.exploreMainLayout);

        exploreMenuLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clearFocus(view);
            }
        });
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
            view.requestFocusFromTouch();
        }
    }
}