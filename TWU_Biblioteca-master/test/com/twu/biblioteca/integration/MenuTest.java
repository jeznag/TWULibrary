package com.twu.biblioteca.integration;


import com.twu.biblioteca.TestHelper;
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
        testHelper.loginSuccessfully();
    }
	
    @After
    public void afterTest() {
    }

    @Test
    public void check_that_fake_console_works(){
        testHelper.fakeKeyboardInput("Quit");
        testHelper.startAppWithSampleParameters();
        assertNotEquals(testHelper.getApp().getConsoleInterface(), null);
    }

    @Test
    public void check_welcome_message() {
    	//this should output the welcome message to system.out
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
    	int indexOfWelcome = testHelper.getIndexInFirstPartOfOutput("Welcome! I am the most efficient librarian in the world.");
        assertNotEquals(indexOfWelcome, -1);
    }

    @Test
    public void check_menu_explains_how_to_borrow_books_and_films() {
        //this should output the welcome message to system.out

        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfExplanation = testHelper.getIndexInOutput("Return <item category> <book title>: Return a book (You've gotta tell me the title and the item category. I'm good but I'm not that good.). Example: Borrow Book Shantaram");
        assertNotEquals(indexOfExplanation, -1);
    }

    @Test
    public void check_main_menu() {
    	//this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Menu)");
        testHelper.fakeKeyboardInput("Menu)");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
    	int indexOfMainMenu = testHelper.getIndexInLastPartOfOutput("You again? Aren't you bored yet? Very well, how would you like to amuse yourself next?\n===========================\nList: List the books available");
        assertNotEquals(indexOfMainMenu, -1);
    }

    @Test
    public void check_invalid_option() {
    	//this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("you suck librarian!");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfInvalidMessage = testHelper.getIndexInLastPartOfOutput("Whatchoo talkin bout fool? you suck librarian! isn't a real command. I only do this stuff");
        assertNotEquals(indexOfInvalidMessage, -1);
    }
    
    
    @Test
    public void check_that_quit_works() {
    	//this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Quit");
        testHelper.fakeKeyboardInput("I changed my mind. Please let me come back!");
        testHelper.startAppWithSampleParameters();
    	int indexOfQuitMessage = testHelper.getIndexInOutput("Get lost loser. You think I'm gonna come back to you after you broke up with me?");

        assertNotEquals(indexOfQuitMessage, -1);
    }

}
