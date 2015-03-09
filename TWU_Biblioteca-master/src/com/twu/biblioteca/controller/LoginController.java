package com.twu.biblioteca.controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.twu.biblioteca.controller.console.ConsoleInterface;
import com.twu.biblioteca.model.LoginModel;
import com.twu.biblioteca.model.UserModel;
import com.twu.biblioteca.view.LoginView;

/**
 * Created by jeremynagel on 6/03/15.
 */
public class LoginController {

    public UserModel requireLogin(LoginModel loginModel, ConsoleInterface console) throws IllegalArgumentException{
        LoginView loginView = new LoginView();
        console.writeToOutput(loginView.getInitialLoginMessage());
        String loginDetails = console.readLineFromInput();
        String username = getTokenisedStringElement(loginDetails, " ", 1);
        String password = getTokenisedStringElement(loginDetails, " ", 2);
        return loginModel.tryLogin(username, password);
    }

    private String getTokenisedStringElement(String stringToTokenise, String delimiter, int index){
        if (stringToTokenise == null)
            return null;
        String tokenParts[] = stringToTokenise.split(delimiter);

        if (index >= tokenParts.length)
            return null;
        return tokenParts[index];
    }
}
