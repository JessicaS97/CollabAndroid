package com.collab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class ProfileSetUp extends AppCompatActivity {

    int TAKE_IMAGE_CODE = 10001;

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

    public void editProfilePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, TAKE_IMAGE_CODE);
        }
    }
}