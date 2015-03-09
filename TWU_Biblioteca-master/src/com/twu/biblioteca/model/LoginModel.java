package com.twu.biblioteca.model;

import java.util.ArrayList;

/**
 * Created by jeremynagel on 9/03/15.
 */
public class LoginModel {

    public LoginModel(ArrayList<UserModel> users) {
        this.users = users;
    }

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserModel> users) {
        this.users = users;
    }

    public UserModel tryLogin(String cardNumber, String password){
        if (cardNumber == null || password == null)
            return null;
        for (UserModel user: users){
            if (user.tryLogin(cardNumber, password))
                return user;
        }
        return null;
    }

    private ArrayList<UserModel> users = new ArrayList<UserModel>();

}
