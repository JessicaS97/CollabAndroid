package com.collab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

public class CreateGroupDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputLayout groupNameLayout;
    TextInputLayout groupSizeDropDown;
    TextInputLayout groupDescriptionLayout;
    TextInputLayout groupUserFitLayout;

    ConstraintLayout groupDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_details);

        TextInputLayout groupNameLayout = (TextInputLayout) findViewById(R.id.signUpFullNameInputLayout);
        ConstraintLayout groupsDetailsLayout = findViewById(R.id.createGroupDetailsLayout);

        Spinner groupSizeSpinner = (Spinner) findViewById(R.id.groupSizeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.group_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSizeSpinner.setAdapter(adapter);
        groupSizeSpinner.setOnItemSelectedListener(this);

        groupsDetailsLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clearFocus();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String groupSizeSelected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void goBackToCreateGroupCategoryScreen(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), CreateGroupCategory.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void createGroup() {
        validateFields();
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), GroupsMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void validateFields() {
        if (groupNameLayout.getEditText().getText().toString().isEmpty()) {
            groupNameLayout.setError("Email address is required");
            groupNameLayout.requestFocus();
            return;
        }
    }

    public void clearFocus() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focusedView = getCurrentFocus();

        if (focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
            focusedView.clearFocus();
        }
    }
}