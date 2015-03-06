package com.twu.biblioteca.integration;


import com.twu.biblioteca.controller.console.FakeConsole;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FakeConsoleTest {

    @Test
    public void check_that_fake_console_input_works(){
        FakeConsole fakeConsole = new FakeConsole();
        fakeConsole.injectLineToInput("Test");
        fakeConsole.injectLineToInput("Test2");
        fakeConsole.injectLineToInput("Test3");

        assertEquals(fakeConsole.readLineFromInput(), "Test");
        assertEquals(fakeConsole.readLineFromInput(), "Test2");
        assertEquals(fakeConsole.readLineFromInput(), "Test3");
    }
}
