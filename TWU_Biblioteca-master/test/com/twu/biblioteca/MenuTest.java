package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
**  Tests menu stories
**/
public class MenuTest {

    private TestHelper testHelper;
    
    @Before
    public void beforeTest() {
        testHelper = new TestHelper();
        testHelper.setUpStreams();
    }
	
    @After
    public void afterTest() {
	testHelper.cleanUpStreams();
    }

    @Test
    public void checkWelcomeMessage() {
    	//this should output the welcome message to system.out
        testHelper.fakeKeyboardInput("Quit\n");
    	BibliotecaApp.main(null);
    	int indexOfWelcome = testHelper.getIndexInOutput("Welcome! I am the most efficient librarian in the world.");
    	assertEquals(indexOfWelcome, 0);
    }
    
    @Test
    public void checkMainMenu() {
    	//this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Menu\nMenu\nQuit\n");
        testHelper.startAppWithSampleParameters();
    	int indexOfMainMenu = testHelper.getIndexInOutput("You again? Aren't you bored yet? Very well, how would you like to amuse yourself next?\n===========================\nList: List the books available");
        assertNotEquals(indexOfMainMenu, -1);
    }
    
    @Test
    public void checkInvalidOption() {
    	//this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("you suck librarian!\nQuit\n");
        BibliotecaApp.main(null);
        int indexOfInvalidMessage = testHelper.getIndexInOutput("Whatchoo talkin bout fool?you suck librarian! isn't a real command. I only do this stuff");
        assertNotEquals(indexOfInvalidMessage, -1);
    }
    
    
    @Test
    public void checkThatQuitWorks() {
    	//this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Quit\nI changed my mind. Please let me come back!\n");
        BibliotecaApp.main(null);
    	int indexOfQuitMessage = testHelper.getIndexInOutput("Get lost loser. You think I'm gonna come back to you after you broke up with me?");
    	assertNotEquals(indexOfQuitMessage, -1);
    }

}
