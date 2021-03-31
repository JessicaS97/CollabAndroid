package com.collab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }

    public void goToProfileSetUpPage(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), ProfileSetUp.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void goToExplorePage(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), ExploreMain.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }
}