package com.twu.biblioteca.view;

import com.twu.biblioteca.model.UserModel;

/**
 * Created by jeremynagel on 9/03/15.
 */
public class LoginView {

    public String getInitialLoginMessage(){
        return "Who goes there? Give me your card number and password or else I ain't talking to you.\n" +
            "Identify yourself like this: <card number> <password>\n";
    }

    public String getLoginResponse(UserModel loggedInUser){
        if (loggedInUser != null)
            return MESSAGE_AFTER_SUCCESSFUL_LOGIN;
        return MESSAGE_AFTER_UNSUCCESSFUL_LOGIN;
    }

    public static final String MESSAGE_AFTER_SUCCESSFUL_LOGIN = "Very well then. \nPlease follow me into the lobby where I will introduce you to my colleague.\nI must warn you - he is quite rude.\n";
    public static final String MESSAGE_AFTER_UNSUCCESSFUL_LOGIN = "I regret to inform you that your login details are incorrect.\nPlease try again. Remember to write it like this <card number> <password>\n";
}
