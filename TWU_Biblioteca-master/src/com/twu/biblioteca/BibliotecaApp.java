package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    Library library;
    
    private static BibliotecaApp _app;
    
    public static void main(String[] args) {
        _app = new BibliotecaApp();
        LibraryMenu menu = new LibraryMenu(_app);
        System.out.println(menu.getMainMenu());
        Scanner s = new Scanner(System.in);
        while (true){
        	String nextCommand = scanner.next();
        	System.out.println(menu.processCommand(nextCommand));
        }
    }
    
    public static Library getLibrary(){
    	return Library;
    }
}
