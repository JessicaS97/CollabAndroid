package com.collab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateGroupDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputLayout groupNameLayout;
    EditText groupDescriptionText;
    Spinner groupSizeSpinner;
    EditText groupUserFitText;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    ConstraintLayout groupDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_details);

        groupNameLayout = (TextInputLayout) findViewById(R.id.groupNameInputLayout);
        groupDescriptionText = (EditText) findViewById(R.id.groupDescriptionText);
        groupUserFitText = (EditText) findViewById(R.id.userFitText);
        ConstraintLayout groupsDetailsLayout = (ConstraintLayout) findViewById(R.id.createGroupDetailsLayout);
        groupSizeSpinner = (Spinner) findViewById(R.id.groupSizeSpinner);

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

        groupSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#696969"));
                ((TextView) parent.getChildAt(0)).setTextSize((float)15);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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

    public void createGroup(View view) {
        validateFields();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("groups");

        String groupName = groupNameLayout.getEditText().getText().toString();
        String groupSize = groupSizeSpinner.getSelectedItem().toString();
        String groupDescription = groupDescriptionText.getText().toString();
        String groupProfileFit = groupUserFitText.getText().toString();
        String groupCategory = getIntent().getStringExtra("Category");
        String groupAuthor = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Group group = new Group(groupName, groupSize, groupDescription, groupProfileFit, groupCategory, groupAuthor);
        String strParentKey = reference.push().getKey();
        reference.child(strParentKey).setValue(group).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Group has been created successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Failed to create group! Try again", Toast.LENGTH_LONG).show();
                }
            }
        });

        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), GroupsMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }

    public void validateFields() {
        if (groupNameLayout.getEditText().getText().toString().isEmpty()) {
            groupNameLayout.setError("Group name is required");
            groupNameLayout.requestFocus();
            return;
        }

        if (groupDescriptionText.getText().toString().isEmpty()) {
            groupDescriptionText.setError("Group description is required");
            groupDescriptionText.requestFocus();
            return;
        }

        if (groupUserFitText.getEditableText().toString().isEmpty()) {
            groupUserFitText.setError("Who should join field is required");
            groupUserFitText.requestFocus();
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

    public void goBackToGroupsMenu(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), GroupsMenu.class);
        startActivity(switchActivityIntent);
        overridePendingTransition(0, 0);
    }
}