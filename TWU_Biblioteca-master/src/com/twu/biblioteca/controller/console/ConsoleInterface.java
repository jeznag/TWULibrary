package com.twu.biblioteca.controller.console;

/**
 * Created by jeremynagel on 5/03/15.
 */
public interface ConsoleInterface {

    public void writeToOutput(String line);

    public String readLineFromInput();

    public String getLastLineFromOutput();

    public void injectLineToInput(String line);
}
