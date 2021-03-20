package com.collab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    ConstraintLayout signUpLayout;
    LinearLayout containerGoToInterest;

    TextInputEditText fullNameInputLayout;
    TextInputEditText emailInputLayout;
    TextInputEditText passwordInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpLayout = (ConstraintLayout) findViewById(R.id.signUpLayout);
        containerGoToInterest = (LinearLayout) findViewById(R.id.containerGoToInterest);

        fullNameInputLayout = (TextInputEditText) findViewById(R.id.fullNameId);
        emailInputLayout = (TextInputEditText) findViewById(R.id.signUpEmailId);
        passwordInputLayout = (TextInputEditText) findViewById(R.id.signUpPasswordId);

        fullNameInputLayout.setFilters(new InputFilter[] {
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        // TODO Auto-generated method stub
                        if(source.equals("")){ // for backspace
                            return source;
                        }
                        if(source.toString().matches("[a-zA-Z ]+")){
                            return source;
                        }
                        return "";
                    }
                }
        });

        signUpLayout.setOnClickListener(new View.OnClickListener() {

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

        containerGoToInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullNameInputLayout.getText().toString().equals("") || emailInputLayout.getText().toString().equals("") ||
                        passwordInputLayout.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"Please complete all fields", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                    Intent switchActivityIntent = new Intent(getBaseContext(), Interests.class);
                    ArrayList signUpDetails = new ArrayList<String>();
                    signUpDetails.add(fullNameInputLayout.getText().toString());
                    signUpDetails.add(emailInputLayout.getText().toString());
                    signUpDetails.add(passwordInputLayout.getText().toString());
                    switchActivityIntent.putExtra("SIGN_UP_DETAILS", signUpDetails);
                    startActivity(switchActivityIntent);
                    overridePendingTransition(0, 0);
                }
            }
        });

        emailInputLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String email = emailInputLayout.getText().toString().trim();

                    if (!isValidEmail(email)) {
                        emailInputLayout.setError("Invalid email address");
                    }
                }
            }
        });

        passwordInputLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String password = passwordInputLayout.getText().toString();

                    if (password.length() < 8) {
                        passwordInputLayout.setError("Password must be at least 8 characters long");
                    }
                }
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void goBackToMainScreen(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(switchActivityIntent);
    }
}