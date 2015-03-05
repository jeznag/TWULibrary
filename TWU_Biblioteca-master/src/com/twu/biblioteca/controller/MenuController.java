package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.LibraryModel;
import com.twu.biblioteca.model.MenuModel;
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

    public String processCommand(String command) {
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
        } else if (command.equalsIgnoreCase("List")) {
            return handleListBooks();
        } else if (command.startsWith("Return")) {
            return handleReturn(command);
        } else if (command.startsWith("Borrow")) {
            return handleBorrow(command);
        } else if (command.equalsIgnoreCase("Menu")) {
            return handleMainMenu();
        } else {
            return handleInvalidCommand(command);
        }
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

    private String handleInvalidCommand(String command){
        menuModel.incrementInvalidCommands();
        return menuView.handleInvalidCommand(command);
    }

    private String handleBorrow(String command) {
        menuModel.resetInvalidCommands();
        String bookTitle = extractBookTitleFromCommand(command, "borrow");
        String borrowOutcome = app.getLibrary().borrowBook(bookTitle);
        return borrowOutcome;
    }

    private String handleExit() {
        menuModel.resetInvalidCommands();
        menuModel.setExited(true);
        return menuView.getMessageOnExit();
    }

    private String handleReturn(String command){
        menuModel.resetInvalidCommands();
        String bookTitle = extractBookTitleFromCommand(command, "return");
        String returnOutcome = app.getLibrary().returnBook(bookTitle);
        return returnOutcome;
    }

    public String extractBookTitleFromCommand(String command, String action){
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2)
            return menuView.getMessageIfTitleNotSupplied(action);
        String bookTitle = commandParts[1];
        return bookTitle;
    }

    public void showMainMenu(boolean firstTime){
        app.getConsoleInterface().writeToOutput(menuView.getMainMenu(true));
    }

}
