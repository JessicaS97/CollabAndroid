package com.collab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExploreMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_main);
    }

    public void categorySelected(View view) {
        String selectedInterestID = view.getResources().getResourceName(view.getId());
        Button selectedInterest = (Button)findViewById(view.getId());
        float currentAlpha = selectedInterest.getAlpha();
        if (currentAlpha == 0.5) {
            selectedInterest.setAlpha(1);
        } else {
            selectedInterest.setAlpha((float) 0.5);
        }
    }
}