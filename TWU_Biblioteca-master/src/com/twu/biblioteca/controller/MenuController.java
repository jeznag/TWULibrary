package com.twu.biblioteca.controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.MenuView;
import com.twu.biblioteca.view.LibraryView;

/**
 * Created by jeremynagel on 5/03/15.
 */
public class MenuController {

    BibliotecaAppController app;

    LibraryModel libraryModel;
    MenuView menuView;
    LibraryView libraryView;
    MenuModel menuModel = new MenuModel();

    public MenuController(LibraryModel libraryModel, MenuView menuView, LibraryView libraryView, BibliotecaAppController app){
        this.libraryModel = libraryModel;
        this.menuView = menuView;
        this.libraryView = libraryView;
        this.app = app;
    }

    public String processCommand(String command, UserModel loggedInUser) throws InvalidArgumentException{
        if (menuModel.isExited()) {
            return menuView.getMessageAfterExit();
        }
        if (menuModel.isTooManyInvalidCommandsInARow()){
            return handleTooManyInvalidCommands();
        }
        if (command == null){
            return handleInvalidCommand(command);
        }
        else if (command.contains("Quit")) {
            return handleExit();
        } else if (command.equalsIgnoreCase("List Books")) {
            return handleListBooks();
        }
        else if (command.equalsIgnoreCase("List Films")) {
            return handleListFilms();
        }
        else if (command.startsWith("Return")) {
            return handleReturn(command);
        } else if (command.startsWith("Borrow")) {
            return handleBorrow(command, loggedInUser);
        } else if (command.startsWith("MyProfile")) {
            return handleMyProfile(loggedInUser);
        } else if (command.startsWith("Whodunnit")) {
            return handleWhoDunnit(command);
        }
        else if (command.equalsIgnoreCase("Menu")) {
            return handleMainMenu();
        } else {
            return handleInvalidCommand(command);
        }
    }

    private String handleWhoDunnit(String command) throws InvalidArgumentException{
        menuModel.resetInvalidCommands();
        String borrowItemType = extractItemTypeFromCommand(command, "whodunnit");
        String itemTitle = extractItemTitleFromCommand(command, "whodunnit");
        String borrowOutcome = app.getLibrary().whoBorrowedLibraryItem(itemTitle, libraryModel.getAppropriateListOfItems(borrowItemType));
        return borrowOutcome;
    }

    private String handleTooManyInvalidCommands() {
        menuModel.setExited(true);
        return menuView.getMessageAfterTooManyInvalidCommands();
    }

    private String handleMainMenu() {
        menuModel.resetInvalidCommands();
        return menuView.getMainMenu(false);
    }

    private String handleListBooks() {
        menuModel.resetInvalidCommands();
        return libraryView.listBooks();
    }

    private String handleListFilms() {
        menuModel.resetInvalidCommands();
        return libraryView.listFilms();
    }

    private String handleInvalidCommand(String command){
        menuModel.incrementInvalidCommands();
        return menuView.handleInvalidCommand(command);
    }

    private String handleBorrow(String command, UserModel loggedInUser) throws InvalidArgumentException{
        menuModel.resetInvalidCommands();
        String borrowItemType = extractItemTypeFromCommand(command, "borrow");
        String itemTitle = extractItemTitleFromCommand(command, "borrow");
        String borrowOutcome = app.getLibrary().borrowLibraryItem(itemTitle, libraryModel.getAppropriateListOfItems(borrowItemType), loggedInUser);
        return borrowOutcome;
    }

    private String handleExit() {
        menuModel.resetInvalidCommands();
        menuModel.setExited(true);
        return menuView.getMessageOnExit();
    }

    private String handleReturn(String command) throws InvalidArgumentException{
        menuModel.resetInvalidCommands();
        String returnItemType = extractItemTypeFromCommand(command, "return");
        String itemTitle = extractItemTitleFromCommand(command, "return");
        String borrowOutcome = app.getLibrary().returnLibraryItem(itemTitle, libraryModel.getAppropriateListOfItems(returnItemType));
        return borrowOutcome;
    }

    private String handleMyProfile(UserModel loggedInUser){
        return loggedInUser.getSummary();
    }

    public String extractItemTitleFromCommand(String command, String action){
        String[] commandParts = command.split(" ", 3);
        if (commandParts.length < 2)
            return menuView.getMessageIfTitleNotSupplied(action);
        String bookTitle = commandParts[2];
        return bookTitle;
    }

    public String extractItemTypeFromCommand(String command, String action) throws InvalidArgumentException{
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2)
            return menuView.getMessageIfTitleNotSupplied(action);
        String itemType = commandParts[1];
        return menuModel.isValidItemType(itemType);
    }

    public void showMainMenu(boolean firstTime){
        app.getConsoleInterface().writeToOutput(menuView.getMainMenu(true));
    }



}
