package com.twu.biblioteca;

import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;
import java.util.Scanner;

public class BibliotecaApp {

    Library library;
    LibraryMenu menu;

    public BibliotecaApp(Library library, InputStream inputStream){
        this.library = library;
        this.menu = new LibraryMenu(this);
        runApp(inputStream);
    }

    public BibliotecaApp(){
        super(getSampleLibrary(), System.in);
        this.library = getSampleLibrary();
          menu = new LibraryMenu(this);
        runApp(System.in);
    }

    public static void main(String[] args) {
        new BibliotecaApp();
    }
    
    public void runApp(InputStream inputstream){
        System.out.println(menu.getMainMenu(true));
        String response = "";
        Scanner scanner = new Scanner(inputstream);
        while (response.indexOf(LibraryMenu.MESSAGE_ON_EXIT) == -1){
            String nextCommand = scanner.nextLine();
            response = menu.processCommand(nextCommand);
        	System.out.println(response);
        }
    }
    
   public static Library getSampleLibrary(){
    	Library library = new Library();
    	Book testBook = new Book("Gregory David Roberts", 2003, "Shantaram");
	    Book testBook2 = new Book("Sun Tzu", 1910, "The Art of War"); //first proper translation
	
	    library.addBook(testBook);
	    library.addBook(testBook2);
        return library;
   }
    
    public Library getLibrary(){
    	return library;
    }
}
