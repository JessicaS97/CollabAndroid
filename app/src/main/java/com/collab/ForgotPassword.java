package com.collab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ForgotPassword extends AppCompatActivity {

    ConstraintLayout forgotPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPasswordLayout = (ConstraintLayout) findViewById(R.id.forgotPasswordLayout);

        forgotPasswordLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                View focusedView = getCurrentFocus();

                if (focusedView != null) {
                    inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
                    view.requestFocusFromTouch();
                }
            }
        });

    }

    public void goBackToMainScreen(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(switchActivityIntent);
    }

}