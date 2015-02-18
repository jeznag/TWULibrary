package com.twu.biblioteca;


public class LibraryMenu{
	
	private boolean exited = false;	
	BibliotecaApp app;

	public LibraryMenu(BibliotecaApp app){
		this.app = app;		
	}

	public String processCommand(String command){
		if (exited)
			return "Get lost loser. You think I'm gonna come back to you after you broke up with me?";
	
		if (command.equalsIgnoreCase("Quit")){
			exited = true;
			return "Kthanxbye";
		}
		else if (command.equalsIgnoreCase("List")){
			return app.getLibrary().listBooks() + getMainMenu(false);
		}
		else if (command.startsWith("Return")){
			bookTitle = extractBookTitleFromCommand(command, "return");
			Book bookToReturn = app.getLibrary(commandParts).getBookByTitle(bookTitle);
			return bookToReturn.returnBook() + getMainMenu(false);
		}
		else if (command.startsWith("Borrow")){
			bookTitle = extractBookTitleFromCommand(command, "borrow");
			Book bookToBorrow = app.getLibrary(commandParts).getBookByTitle(bookTitle);
			return bookToReturn.borrowBook() + getMainMenu(false);
		}
		else if (command.equalsIgnoreCase("Menu")){
			return getMainMenu(false);
		}
		else{
			return "Whatchoo talkin bout fool. I only do this stuff.\n" + getMainMenu(false);
		}
	}

	public String extractBookTitleFromCommand(String command, String action){
		commandParts = command.split(" ");
		if (commandParts.size() < 2)
			return "Hey! I told you I'm not a mind reader. Tell me what book you want to " + action;
		
		bookTitle = commandParts[1];
		return bookTitle;
	}

	public String getMainMenu(boolean firstTime){
		String intro = firstTime ? getFirstTimeMessage() : getRepeatMessage();
		String mainMenu = 
			"List: List the books available" +
			"\nReturn <book title>: Return a book (You've gotta tell me the title. I'm good but I'm not that good.)\n" +
			"\nBorrow <book title>: Borrow a book (You've gotta tell me the title. I'm good but I'm not that good.)\n" +
			"\nQuit: Have I impressed you enough already?\n";

		return intro + mainMenu;
	}

	public String getFirstTimeMessage(){
		return "Welcome! I am the most efficient librarian in the world.\n In what way can I exceed your expectations and thus boost my ego?\n In a few words, tell me how I can help.\n";
	}
	
	public String getRepeatMessage(){
		return "You again? Aren't you bored yet? Very well, how would you like to amuse yourself next?\n";
	}
}