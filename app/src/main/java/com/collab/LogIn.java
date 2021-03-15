package com.collab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void goBackToMainScreen() {
        Intent switchActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(switchActivityIntent);
    }
}
