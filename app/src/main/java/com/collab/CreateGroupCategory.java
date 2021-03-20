package com.collab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateGroupCategory extends AppCompatActivity {

    Button selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_category);
    }

    public void goToCreateGroupDetails(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), CreateGroupDetails.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void categorySelected(View view) {
        Button currentCategory = (Button)findViewById(view.getId());
        float currentAlpha = currentCategory.getAlpha();
        if (currentAlpha == 0.5) {
            currentCategory.setAlpha(1);
            if (selectedCategory != null) {
                selectedCategory.setAlpha((float) 0.5);
            }
            selectedCategory = currentCategory;
        } else {
            currentCategory.setAlpha((float) 0.5);
        }
    }

    public void goBackToGroupsMenu(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), GroupsMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }
}