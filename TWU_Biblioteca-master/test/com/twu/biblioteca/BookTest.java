package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
    @Before
    public void setUpStreams() {
	System.setOut(new PrintStream(outContent));
	System.setErr(new PrintStream(errContent));
    }
	
    @After
    public void cleanUpStreams() {
	System.setOut(null);
	System.setErr(null);
    }

    @Test
    public void checkWelcomeMessage() {
    	//this should output the welcome message to system.out
    	BibliotecaApp.main(null);
    	int indexOfWelcome = outContent.indexOf("Welcome! I am the most efficient librarian in the world.");
    	assertEquals(indexOfWelcome, 0);
    }

    @Test
    public void bookSummaryCorrect() {
	Book testBook = new Book("Matthew Reilly", 1984, "Scarecrow");
        assertEquals(testBook.getSummary(), "'Scarecrow' by Matthew Reilly published in 1984");
    }
    
    @Test
    public void librarySummaryCorrect() {
	Library libary = new Library();
	setUpLibrary(library);
	
        assertEquals(library.listBooks(), "'Shantaram' by Gregory David Roberts published in 2003\n'The Art of War' by Sun Tzu published in 1910");
    }
    
    
    @Test
    public void checkMainMenu() {
    	//this should output the welcome message + main menu to system.out
    	BibliotecaApp.main(null);
    	fakeKeyboardInput("Menu");
    	int indexOfMainMenu = outContent.indexOf("You again? Aren't you bored yet? Very well, how would you like to amuse yourself next?\nList: List the books available");
    	assertEquals(indexOfMainMenu, 0);
    }
    
    @Test
    public void checkInvalidOption() {
    	//this should output the welcome message + main menu to system.out
    	BibliotecaApp.main(null);
    	fakeKeyboardInput("you suck librarian!");
    	int indexOfInvalidMessage = outContent.indexOf("Whatchoo talkin bout fool. I only do this stuff");
    	assertEquals(indexOfInvalidMessage, 0);
    }
    
    
    @Test
    public void checkThatQuitWorks() {
    	//this should output the welcome message + main menu to system.out
    	BibliotecaApp.main(null);
    	fakeKeyboardInput("Quit");
    	fakeKeyboardInput("I changed my mind. Please let me come back!");
    	int indexOfQuitMessage = outContent.indexOf("Get lost loser. You think I'm gonna come back to you after you broke up with me?");
    	assertEquals(indexOfQuitMessage, 0);
    }
    
    
    @Test
    public void checkThatICanCheckout() {
    	setUpLibrary(BibliotecaApp.getLibrary());
    	//this should output the welcome message + main menu to system.out
    	BibliotecaApp.main(null);
    	fakeKeyboardInput("Borrow Shantaram");
    	int indexOfCheckoutMessage = outContent.indexOf("The book's yours!");
    	assertEquals(indexOfCheckoutMessage, 0);
    }
    
    @Test
    public void checkThatICannotCheckoutTwice() {
    	setUpLibrary(BibliotecaApp.getLibrary());
    	//this should output the welcome message + main menu to system.out
    	BibliotecaApp.main(null);
    	fakeKeyboardInput("Borrow Shantaram");
    	//try to borrow it again
    	fakeKeyboardInput("Borrow Shantaram");
    	int indexOfCheckoutMessage = outContent.indexOf("That book ain't there sucker");
    	assertEquals(indexOfCheckoutMessage, 0);
    }
    
    
    @Test
    public void checkThatICanReturn() {
    	setUpLibrary(BibliotecaApp.getLibrary());
    	//this should output the welcome message + main menu to system.out
    	BibliotecaApp.main(null);
    	fakeKeyboardInput("Borrow Shantaram");
    	fakeKeyboardInput("Return Shantaram");
    	int indexOfReturnMessage = outContent.indexOf("Thank you kindly most respectable of all citizens. I worship the ground you stand on.\n");
    	assertEquals(indexOfReturnMessage, 0);
    }
    
    @Test
    public void checkThatICannotReturnABookThatIsNotBorrowed() {
    	setUpLibrary(BibliotecaApp.getLibrary());
    	//this should output the welcome message + main menu to system.out
    	BibliotecaApp.main(null);
    	fakeKeyboardInput("Return Shantaram");
    	int indexOfReturnMessage = outContent.indexOf("Who you trying to fool? You never borrowed that book and now you're saying you're returning it? What's wrong with you?\n");
    	assertEquals(indexOfReturnMessage, 0);
    }
    
    private void fakeKeyboardInput(String input){
	InputStream stdin = System.in;
	try {
	  System.setIn(new ByteArrayInputStream(input.getBytes()));
	  Scanner scanner = new Scanner(System.in);
	  System.out.println(scanner.nextLine());
	} finally {
	  System.setIn(stdin);
	}
    }
    
    private void setUpLibrary(Library library){
    	Book testBook = new Book("Gregory David Roberts", 2003, "Shantaram");
	Book testBook2 = new Book("Sun Tzu", 1910, "The Art of War"); //first proper translation
	
	library.addBook(testBook);
	library.addBook(testBook2);
    }
}
