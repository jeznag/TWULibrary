package com.twu.biblioteca.integration;


import com.twu.biblioteca.TestHelper;
import com.twu.biblioteca.controller.BibliotecaAppController;
import com.twu.biblioteca.model.LibraryModel;
import com.twu.biblioteca.view.LibraryView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
**  Tests Library stories
**/
public class LibraryTest {

    protected TestHelper testHelper;

    @Before
    public void beforeTest() {
        testHelper = new TestHelper();
    }

    @After
    public void afterTest() {
        testHelper.exit();
    }

    @Test
    public void check_library_summary_works_with_console() {
        testHelper.fakeKeyboardInput("List");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfLibraryList = testHelper.getIndexInLastPartOfOutput("Shantaram");
        assertNotEquals(indexOfLibraryList, -1);
    }

    @Test
    public void check_that_I_can_checkout() {
        testHelper.fakeKeyboardInput("Borrow Shantaram");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
    	int indexOfCheckoutMessage = testHelper.getIndexInLastPartOfOutput("The book's yours!");
    	assertNotEquals(indexOfCheckoutMessage, -1);
    }

    @Test
    public void check_that_I_cannot_checkout_twice() {
        testHelper.fakeKeyboardInput("Borrow Shantaram");
        testHelper.fakeKeyboardInput("Borrow Shantaram");
        testHelper.fakeKeyboardInput("Quit");
        //this should output the welcome message + main menu to system.out
        testHelper.startAppWithSampleParameters();
        //try to borrow twice
        int indexOfCheckoutMessage = testHelper.getIndexInLastPartOfOutput("That book ain't there sucker");
    	assertNotEquals(indexOfCheckoutMessage, -1);
    }

    @Test
    public void check_that_I_can_return() {
        testHelper.fakeKeyboardInput("Borrow Shantaram");
        testHelper.fakeKeyboardInput("Return Shantaram");
        testHelper.fakeKeyboardInput("Quit");

    	//this should output the welcome message + main menu to system.out
        testHelper.startAppWithSampleParameters();
    	int indexOfReturnMessage = testHelper.getIndexInLastPartOfOutput("Thank you kindly most respectable of all citizens. I worship the ground you stand on.\n");
        assertNotEquals(-1, indexOfReturnMessage);
        testHelper.exit();
    }
    
    @Test
    public void check_that_I_cannot_return_a_book_that_has_been_borrowed() {
    	//this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Return Shantaram");
        testHelper.fakeKeyboardInput("Quit");
        testHelper.startAppWithSampleParameters();
    	int indexOfReturnMessage = testHelper.getIndexInLastPartOfOutput("Who you trying to fool? You never borrowed that book and now you're saying you're returning it? What's wrong with you?\n");
        assertNotEquals(-1, indexOfReturnMessage);
    }
    
}
