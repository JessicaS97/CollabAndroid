package com.collab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity implements View.OnKeyListener {

    TextInputEditText emailInputLayout;
    TextInputEditText passwordInputLayout;
    ConstraintLayout logInLayout;
    ImageView logInButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailInputLayout = (TextInputEditText) findViewById(R.id.emailInputField);
        passwordInputLayout = (TextInputEditText) findViewById(R.id.passwordInputField);
        logInLayout = (ConstraintLayout) findViewById(R.id.logInLayout);
        logInButton = (ImageView) findViewById((R.id.buttonLoggedIn));

        mAuth = FirebaseAuth.getInstance();

        logInLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                View focusedView = getCurrentFocus();

                if (focusedView != null) {
                    inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
                    focusedView.clearFocus();
                }
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailInputLayout.getText().toString().isEmpty()) {
                    emailInputLayout.setError("Email address is required");
                    emailInputLayout.requestFocus();
                    return;
                }

                if (passwordInputLayout.getText().toString().isEmpty()) {
                    passwordInputLayout.setError("Password is required");
                    passwordInputLayout.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(emailInputLayout.getText().toString(), passwordInputLayout.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    finish();
                                    Intent switchActivityIntent = new Intent(getBaseContext(), ExploreMain.class);
                                    startActivity(switchActivityIntent);
                                } else {
                                    Toast.makeText(getApplicationContext(),"Failed to log in", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    };

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
        // Go to main screen app
        }
        return false;
    }

    public void goBackToMainScreen(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void goToSignUpPage(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), SignUp.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void goToForgotPassword(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), ForgotPassword.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

}
