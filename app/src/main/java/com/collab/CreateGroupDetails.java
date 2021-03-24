package com.collab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

public class CreateGroupDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputLayout groupNameLayout;
    TextInputLayout groupSizeDropDown;
    TextInputLayout groupDescriptionLayout;
    TextInputLayout groupUserFitLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_details);

        TextInputLayout groupNameLayout = (TextInputLayout) findViewById(R.id.signUpFullNameInputLayout);

        Spinner groupSizeSpinner = (Spinner) findViewById(R.id.groupSizeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.group_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSizeSpinner.setAdapter(adapter);
        groupSizeSpinner.setOnItemSelectedListener(this);
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
}