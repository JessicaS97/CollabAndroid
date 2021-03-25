package com.collab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileSetUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_set_up);
    }

    public void goBackToExplore(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), ExploreMain.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void finishProfileSetup(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), GroupsMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }
}