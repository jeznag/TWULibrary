package com.twu.biblioteca.view;


import com.twu.biblioteca.controller.BibliotecaAppController;

public class MenuView {

    BibliotecaAppController app;
    public MenuView(BibliotecaAppController app){
        this.app = app;
    }

    public String getMessageAfterExit() {
        return (MESSAGE_AFTER_EXIT);
    }

    public String getMessageOnExit() {
        return (MESSAGE_ON_EXIT);
    }

    public String getMessageAfterTooManyInvalidCommands(){
        return MESSAGE_AFTER_TOO_MANY_INVALID_COMMANDS;
    }

    public String getMainMenu(boolean firstTime){
        String intro = firstTime ? getFirstTimeMessage() : getRepeatMessage();
        String mainMenu = "===========================\n" +
                "List: List the books available" +
                "\nReturn <book title>: Return a book (You've gotta tell me the title. I'm good but I'm not that good.)\n" +
                "\nBorrow <book title>: Borrow a book (You've gotta tell me the title. I'm good but I'm not that good.)\n" +
                "\nQuit: Have I impressed you enough already?\n===========================\n";

        return (intro + mainMenu);
    }

    public String getFirstTimeMessage(){
        return "Welcome! I am the most efficient librarian in the world.\n In what way can I exceed your expectations and thus boost my ego?\n In a few words, tell me how I can help.\n";
    }

    public String getRepeatMessage(){
        return "You again? Aren't you bored yet? Very well, how would you like to amuse yourself next?\n";
    }

    public String handleInvalidCommand(String command){
        return "Whatchoo talkin bout fool? " + command + " isn't a real command. I only do this stuff.\n" + getMainMenu(false);
    }

    public String getMessageIfTitleNotSupplied(String action){
        return "Hey! I told you I'm not a mind reader. Tell me what book you want to " + action;
    }

    public final String MESSAGE_AFTER_EXIT = "Get lost loser. You think I'm gonna come back to you after you broke up with me?\n";
    public final String MESSAGE_ON_EXIT = "Kthanxbye\n";
    public final String MESSAGE_AFTER_TOO_MANY_INVALID_COMMANDS = "I'm sick of talking to you. You make no sense!\n";

}