package com.twu.biblioteca.controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.twu.biblioteca.controller.console.ConsoleInterface;
import com.twu.biblioteca.model.LoginModel;
import com.twu.biblioteca.model.UserModel;
import com.twu.biblioteca.model.factory.LibraryFactory;
import com.twu.biblioteca.model.LibraryModel;
import com.twu.biblioteca.model.factory.LoginFactory;
import com.twu.biblioteca.view.LoginView;
import com.twu.biblioteca.view.MenuView;
import com.twu.biblioteca.view.LibraryView;
import com.twu.biblioteca.controller.console.RealConsole;

public class BibliotecaAppController {

    public ConsoleInterface getConsoleInterface() {
        return consoleInterface;
    }

    public void setConsoleInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public BibliotecaAppController(LibraryModel library, ConsoleInterface consoleInterface){
        this.library = library;
        this.menu = new MenuView(this);
        runApp(consoleInterface);
    }

    public BibliotecaAppController(){
        this(LibraryFactory.getSampleBookAndMovieLibrary(), new RealConsole());
    }

    public static void main(String[] args) {
        new BibliotecaAppController();
    }

    public void runApp(ConsoleInterface console){
        setConsoleInterface(console);
        setUpMVC();
        UserModel loggedInUser = handleLogin(console);
        menuController.showMainMenu(true);
        handleMenuInput(console, loggedInUser);
    }

    private void handleMenuInput(ConsoleInterface console, UserModel loggedInUser) {
        String response = "";
        while (!response.contains(menuView.MESSAGE_AFTER_EXIT)){
            String nextCommand = console.readLineFromInput();
            try {
                response = menuController.processCommand(nextCommand, loggedInUser);
            }
            catch (InvalidArgumentException e){
                console.writeToOutput(e.getLocalizedMessage());
            }
        	console.writeToOutput(response);
        }
    }

    private UserModel handleLogin(ConsoleInterface console) {
        String loginResponse = "";
        UserModel loggedInUser = null;
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        int loginAttempts = 0;
        final int MAX_LOGIN_ATTEMPTS = 5;
        while (!loginResponse.contains(LoginView.MESSAGE_AFTER_SUCCESSFUL_LOGIN) && loginAttempts < MAX_LOGIN_ATTEMPTS){
            loggedInUser = loginController.requireLogin(loginModel, console);
            loginResponse = loginView.getLoginResponse(loggedInUser);
            console.writeToOutput(loginResponse);
            loginAttempts++;
        }
        return loggedInUser;
    }

    private void setUpMVC(){
        libraryView  = new LibraryView(library);
        menuView = new MenuView(this);
        menuController = new MenuController(library, menuView, libraryView, this);
    }

    public LibraryModel getLibrary(){
    	return library;
    }

    public LibraryView getLibraryView() {
        return libraryView;
    }

    LibraryModel library;
    MenuView menu;
    ConsoleInterface consoleInterface;
    LibraryController libraryController = new LibraryController();
    LoginModel loginModel = LoginFactory.getSampleLogins();


    LibraryView libraryView;
    MenuView menuView;
    MenuController menuController;

}
