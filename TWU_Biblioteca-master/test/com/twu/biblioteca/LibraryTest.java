package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
**  Tests Library stories
**/
public class LibraryTest {

    private TestHelper testHelper;
    
    @Before
    public void beforeTest() {
	    testHelper = new TestHelper();
	    testHelper.setUpStreams();
    }
	
    @After
    public void afterTest() {
        testHelper.exit();
        testHelper.cleanUpStreams();
    }

    @Test
    public void librarySummaryCorrect() {
        Library testLibrary = BibliotecaApp.getSampleLibrary();
        assertEquals(testLibrary.listBooks(), "'Shantaram' by Gregory David Roberts published in 2003\n'The Art of War' by Sun Tzu published in 1910\n");
    }
    
    @Test
    public void checkThatICanCheckout() {
        testHelper.fakeKeyboardInput("Borrow Shantaram\nQuit\n");
        testHelper.startAppWithSampleParameters();
    	int indexOfCheckoutMessage = testHelper.getIndexInOutput("The book's yours!");
    	assertNotEquals(indexOfCheckoutMessage, -1);
    }

    @Test
    public void checkThatICannotCheckoutTwice() {
        testHelper.fakeKeyboardInput("Borrow Shantaram\nBorrow Shantaram\nQuit\n");
        //this should output the welcome message + main menu to system.out
        testHelper.startAppWithSampleParameters();
        //try to borrow twice
        int indexOfCheckoutMessage = testHelper.getIndexInOutput("That book ain't there sucker");
    	assertNotEquals(indexOfCheckoutMessage, -1);
    }

    @Test
    public void checkThatICanReturn() {
        testHelper.fakeKeyboardInput("Borrow Shantaram\nReturn Shantaram\nQuit\n");
    	//this should output the welcome message + main menu to system.out
        testHelper.startAppWithSampleParameters();
    	int indexOfReturnMessage = testHelper.getIndexInOutput("Thank you kindly most respectable of all citizens. I worship the ground you stand on.\n");
        assertNotEquals(-1, indexOfReturnMessage);
        testHelper.exit();
    }
    
    @Test
    public void checkThatICannotReturnABookThatIsNotBorrowed() {
    	//this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Return Shantaram\nQuit\n");
        testHelper.startAppWithSampleParameters();
    	int indexOfReturnMessage = testHelper.getIndexInOutput("Who you trying to fool? You never borrowed that book and now you're saying you're returning it? What's wrong with you?\n");
        assertNotEquals(-1, indexOfReturnMessage);
    }
    
}
