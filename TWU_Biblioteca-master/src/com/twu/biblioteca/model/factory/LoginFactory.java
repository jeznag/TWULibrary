package com.twu.biblioteca.model.factory;

import com.twu.biblioteca.model.LoginModel;
import com.twu.biblioteca.model.UserModel;

import java.util.ArrayList;

/**
 * Created by jeremynagel on 9/03/15.
 */
public class LoginFactory {

    public static LoginModel getSampleLogins(){
        UserModel drSpock = new UserModel("Dr Spock", "spock@dr.com", "1800000000", "123-4567", "reallySecurePassword");
        ArrayList<UserModel> users = new ArrayList<UserModel>();
        users.add(drSpock);
        return new LoginModel(users);
    }

}
