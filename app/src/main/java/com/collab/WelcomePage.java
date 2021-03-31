package com.collab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    TextView welcomeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        welcomeTitle = findViewById(R.id.welcomeTitle);

        Intent intent = getIntent();
        String userName = intent.getExtras().getString("USER_NAME");
        welcomeTitle.setText("Welcome, " + userName + "!");
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