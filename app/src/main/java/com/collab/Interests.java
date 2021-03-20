package com.collab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Interests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
    }

    public void goBackToSignUpPage(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), SignUp.class);
        startActivity(switchActivityIntent);
    }

    public void interestSelected(View view) {
        String selectedInterestID = view.getResources().getResourceName(view.getId());
        Button selectedInterest = (Button)findViewById(view.getId());
        float currentAlpha = selectedInterest.getAlpha();
        if (currentAlpha == 0.5) {
            selectedInterest.setAlpha(1);
        } else {
            selectedInterest.setAlpha((float) 0.5);
        }
    }

    public void goToExploreMainPage(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), ExploreMain.class);
        startActivity(switchActivityIntent);
    }

}