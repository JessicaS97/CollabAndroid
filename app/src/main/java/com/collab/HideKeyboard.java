package com.collab;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static androidx.core.content.ContextCompat.getSystemService;

public class HideKeyboard {
    /*public static void hideKeyboard(View.OnClickListener activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(((View) view).getWindowToken(), 0);
    }*/
}
