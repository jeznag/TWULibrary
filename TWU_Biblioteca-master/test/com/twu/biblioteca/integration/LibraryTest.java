package com.twu.biblioteca.integration;


import com.sun.javaws.exceptions.InvalidArgumentException;
import com.twu.biblioteca.TestHelper;
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
        testHelper.loginSuccessfully();
    }

    @After
    public void afterTest() {
        testHelper.exit();
    }

    @Test
    public void check_library_book_summary_works_with_console() {
        testHelper.fakeKeyboardInput("List Books");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfLibraryList = testHelper.getIndexInLastPartOfOutput("Shantaram");
        assertNotEquals(indexOfLibraryList, -1);
    }

    @Test
    public void check_library_film_summary_works_with_console() {
        testHelper.fakeKeyboardInput("List Films");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfLibraryList = testHelper.getIndexInLastPartOfOutput("Mad Max");
        assertNotEquals(indexOfLibraryList, -1);
    }

    @Test
    public void check_library_film_summary_does_not_contain_books() {
        testHelper.fakeKeyboardInput("List Films");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfLibraryList = testHelper.getIndexInLastPartOfOutput("Shantaram");
        assertEquals(indexOfLibraryList, -1);
    }

    @Test
    public void check_that_I_can_checkout() {
        testHelper.fakeKeyboardInput("Borrow Book Shantaram");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
    	int indexOfCheckoutMessage = testHelper.getIndexInLastPartOfOutput("The book's yours!");
    	assertNotEquals(indexOfCheckoutMessage, -1);
    }

    @Test
    public void check_that_I_cannot_checkout_twice() {
        testHelper.fakeKeyboardInput("Borrow Book Shantaram");
        testHelper.fakeKeyboardInput("Borrow Book Shantaram");
        testHelper.exit();
        //this should output the welcome message + main menu to system.out
        testHelper.startAppWithSampleParameters();
        //try to borrow twice
        int indexOfCheckoutMessage = testHelper.getIndexInLastPartOfOutput("That book ain't there sucker");
    	assertNotEquals(indexOfCheckoutMessage, -1);
    }

    @Test
    public void check_that_I_can_return_books() {
        testHelper.fakeKeyboardInput("Borrow Book Shantaram");
        testHelper.fakeKeyboardInput("Return Book Shantaram");
        testHelper.exit();

    	//this should output the welcome message + main menu to system.out
        testHelper.startAppWithSampleParameters();
    	int indexOfReturnMessage = testHelper.getIndexInLastPartOfOutput("Thank you kindly most respectable of all citizens. I worship the ground you stand on.\n");
        assertNotEquals(-1, indexOfReturnMessage);
    }

    @Test
    public void check_that_I_can_return_films() {
        testHelper.fakeKeyboardInput("Borrow Film Mad Max");
        testHelper.fakeKeyboardInput("Return Film Mad Max");
        testHelper.exit();

        //this should output the welcome message + main menu to system.out
        testHelper.startAppWithSampleParameters();
        int indexOfReturnMessage = testHelper.getIndexInLastPartOfOutput("Thank you kindly most respectable of all citizens. I worship the ground you stand on.\n");

        assertNotEquals(-1, indexOfReturnMessage);
    }

    @Test
    public void check_that_I_cannot_borrow_a_fake_film() {
        testHelper.fakeKeyboardInput("Borrow Film An Imaginary Film");
        testHelper.exit();

        //this should output the welcome message + main menu to system.out
        testHelper.startAppWithSampleParameters();
        int indexOfReturnMessage = testHelper.getIndexInLastPartOfOutput("Who you trying to fool?");

        assertNotEquals(-1, indexOfReturnMessage);
    }

    @Test
    public void check_that_I_cannot_return_a_book_that_has_been_borrowed() {
    	//this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Return Book Shantaram");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
    	int indexOfReturnMessage = testHelper.getIndexInLastPartOfOutput("Who you trying to fool? You never borrowed that book and now you're saying you're returning it? What's wrong with you?\n");
        assertNotEquals(-1, indexOfReturnMessage);
    }

    @Test
    public void check_that_we_have_user_details_on_file_when_someone_borrows() throws InvalidArgumentException {
        //this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Borrow Book Shantaram");
        testHelper.fakeKeyboardInput("Whodunnit Book Shantaram");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfReturnMessage = testHelper.getIndexInOutput("Dr Spock has that item");
        assertNotEquals(-1, indexOfReturnMessage);
    }

    @Test
    public void check_that_we_delete_user_details_on_file_when_someone_returns() throws InvalidArgumentException {
        //this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Borrow Book Shantaram");
        testHelper.fakeKeyboardInput("Return Book Shantaram");
        testHelper.fakeKeyboardInput("Whodunnit Book Shantaram");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfReturnMessage = testHelper.getIndexInLastPartOfOutput("It's mine! If you want it, you've gotta be nice to me.");
        assertNotEquals(-1, indexOfReturnMessage);
    }

    @Test
    public void check_that_we_have_no_user_details_on_file_when_book_not_checked_out() throws InvalidArgumentException {
        //this should output the welcome message + main menu to system.out
        testHelper.fakeKeyboardInput("Whodunnit Book Shantaram");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfReturnMessage = testHelper.getIndexInLastPartOfOutput("It's mine! If you want it, you've gotta be nice to me.");
        assertNotEquals(-1, indexOfReturnMessage);
    }

}
