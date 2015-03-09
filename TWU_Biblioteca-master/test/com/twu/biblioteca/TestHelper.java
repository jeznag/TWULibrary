package com.twu.biblioteca;


import com.twu.biblioteca.controller.BibliotecaAppController;
import com.twu.biblioteca.controller.console.FakeConsole;
import com.twu.biblioteca.model.factory.LibraryFactory;

import static org.junit.Assert.assertEquals;

/**
**  Helper methods for test classes
**/
public class TestHelper {

    private final FakeConsole fakeConsole = new FakeConsole();

    public BibliotecaAppController getApp() {
        return app;
    }

    private BibliotecaAppController app;

    public void startAppWithSampleParameters() {
        app = new BibliotecaAppController(LibraryFactory.getSampleBookAndMovieLibrary(), fakeConsole);
    }

    public void fakeKeyboardInput(String input) {
        fakeConsole.injectLineToInput(input);
    }

    /**
     * Checks if the specified string was just sent to system.out
     */
    public int getIndexInLastPartOfOutput(String expectedOutput) {
        return getOutputBeforeQuit().indexOf(expectedOutput);
    }

    /**
     * Checks if the specified string was in the initial part of the output
     */
    public int getIndexInFirstPartOfOutput(String expectedOutput) {
        return getOutputAtStart().indexOf(expectedOutput);
    }

    /**
     * Checks if the specified string was sent to system.out at some point
     */
    public int getIndexInOutput(String expectedOutput) {
        return getOutput().indexOf(expectedOutput);
    }

    public void exit() {
        //say quit twice because that pesky Librarian wants to see that you really want to quit
        fakeKeyboardInput("Quit");
        fakeKeyboardInput("Quit");
    }

    /**
     * Gets the output from the FakeConsole mock
     *
     * @return String with all the output that was sent to the FakeConsole mock object
     */
    public String getOutput() {
        return fakeConsole.getOutput();
    }

    /**
     * We don't want to muddy the waters by asserting on the whole output, we only want to
     * see the output from the very last command otherwise our test results could be bogus
     * E.g. we're expecting to see a successful borrow at the end
     * but it's actually working incorrectly - successful borrow happens at the start when we
     * try to return the book for some reason
     * and an unsuccessful borrow happens when we actually try to borrow
     * In this case, the test would pass with a standard check on the getOutput() method
     *
     * @return A string representing the final output after the last non-quit command and before the quit command
     */
    public String getOutputBeforeQuit() {
        String bitUntilQuit = getOutput().substring(0, getOutput().indexOf("Kthanxbye"));
        String outputBetweenLastCommandAndBitBeforeQuit = bitUntilQuit.substring(bitUntilQuit.indexOf("already?\n" +
                "==========================="), bitUntilQuit.length());
        return outputBetweenLastCommandAndBitBeforeQuit;
    }

    /**
     * In this case, we only want to see the very start of the string
     *
     * @return A string representing the final output after the last non-quit command and before the quit command
     * @see TestHelper#getOutputBeforeQuit
     */
    public String getOutputAtStart() {
        String bitUntilFirstMenuList = getOutput().substring(1, getOutput().indexOf("====="));
        return bitUntilFirstMenuList;
    }

    /**
     * Login using valid credentials
     */
    public void loginSuccessfully() {
        fakeKeyboardInput("Login 123-4567 reallySecurePassword");
    }
}
