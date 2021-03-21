package com.collab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    ConstraintLayout forgotPasswordLayout;
    FirebaseAuth mAuth;

    Button forgotPasswordButton;
    TextInputLayout emailInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPasswordLayout = (ConstraintLayout) findViewById(R.id.forgotPasswordLayout);
        forgotPasswordButton = (Button) findViewById(R.id.button_resetPassword);
        emailInputLayout = (TextInputLayout) findViewById(R.id.forgotPasswordEmailInputLayout);

        forgotPasswordLayout.setOnClickListener(new View.OnClickListener() {

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

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailInputLayout.getEditText().getText().toString().isEmpty()) {
                    emailInputLayout.setError("Please enter an email address");
                    emailInputLayout.requestFocus();
                    return;
                }
                mAuth.sendPasswordResetEmail(emailInputLayout.getEditText().getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Reset password link sent to your email", Toast.LENGTH_LONG).show();

                                    finish();
                                    Intent switchActivityIntent = new Intent(getBaseContext(), MainActivity.class);
                                    startActivity(switchActivityIntent);
                                } else {
                                    Toast.makeText(getApplicationContext(),"Failed to reset password", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }

    public void goBackToMainScreen(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(switchActivityIntent);
    }

}