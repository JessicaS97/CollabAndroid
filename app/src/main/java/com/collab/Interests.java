package com.collab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Interests extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
    }

    public void goBackToSignUpPage(View view) {
        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), SignUp.class);
        startActivity(switchActivityIntent);
    }

    public void interestSelected(View view) {
        String selectedInterestID = view.getResources().getResourceName(view.getId());
        Button selectedInterest = (Button)findViewById(view.getId());
        float currentAlpha = selectedInterest.getAlpha();
        if (currentAlpha == 0.5) {
            selectedInterest.setAlpha(1);
        } else {
            selectedInterest.setAlpha((float) 0.5);
        }
    }

    public void goToExploreMainPage(View view) {
        Intent intent = getIntent();
        String [] stringArray = intent.getStringArrayExtra("SIGN_UP_DETAILS");

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        String fullName = stringArray[0];
        String email = stringArray[1];
        String password = stringArray[2];
        ArrayList<String> interests = new ArrayList<String>();

        // Add interests array

        UserHelperClass helperClass = new UserHelperClass(fullName, email, password, interests);

        reference.child(email).setValue(helperClass);

        finish();
        Intent switchActivityIntent = new Intent(getBaseContext(), ExploreMain.class);
        startActivity(switchActivityIntent);
    }

    public ArrayList<String> getAllInterests(View view) {
        ArrayList<String> allInterestsSelected = new ArrayList<String>();
        for (int i = 1; i <= 6; i++) {
            int id = getResources().getIdentifier("button_"+i, "id", getPackageName());
            Button currentInterest = (Button) findViewById(id);
            if (currentInterest.getAlpha() == 1) {
                allInterestsSelected.add(currentInterest.getText().toString());
            }
        }
        return allInterestsSelected;
    }

}