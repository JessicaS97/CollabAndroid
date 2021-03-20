package com.collab;

import java.util.ArrayList;

public class UserHelperClass {
    String fullName, email, password;
    ArrayList<String> interests;

    public UserHelperClass() {
    }

    public UserHelperClass(String fullName, String email, String password, ArrayList<String> interests) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.interests = interests;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }
}
