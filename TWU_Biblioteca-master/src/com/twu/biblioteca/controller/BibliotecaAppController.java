package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.console.ConsoleInterface;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.LibraryModel;
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
        this(getSampleLibrary(), new RealConsole());
    }

    public static void main(String[] args) {
        new BibliotecaAppController();
    }

    public void runApp(ConsoleInterface console){
        setConsoleInterface(console);
        setUpMVC();
        menuController.showMainMenu(true);
        String response = "";
        while (!response.contains(menuView.MESSAGE_AFTER_EXIT)){
            String nextCommand = console.readLineFromInput();
            response = menuController.processCommand(nextCommand);
        	console.writeToOutput(response);
        }
    }

    private void setUpMVC(){
        libraryView  = new LibraryView(library);
        menuView = new MenuView(this);
        menuController = new MenuController(library, menuView, libraryView, this);
    }
    
   public static LibraryModel getSampleLibrary(){
        LibraryModel library = new LibraryModel();
    	Book testBook = new Book("Gregory David Roberts", 2003, "Shantaram");
	    Book testBook2 = new Book("Sun Tzu", 1910, "The Art of War"); //first proper translation
	
	    library.addBook(testBook);
	    library.addBook(testBook2);
        return library;
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


    LibraryView libraryView;
    MenuView menuView;
    MenuController menuController;

}
