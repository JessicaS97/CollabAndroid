package com.collab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewSignUpPage(View view) {
        Intent switchActivityIntent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void viewLogInPage(View view) {
        Intent switchActivityIntent = new Intent(getBaseContext(), LogIn.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }
}