package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;


import java.io.*;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

/**
**  Helper methods for test classes
**/
public class TestHelper {

    @Rule
    public final TextFromStandardInputStreamJeremyEdit systemInMock
            = TextFromStandardInputStreamJeremyEdit.emptyStandardInputStream();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    public void setUpStreams() {

        System.setOut(new PrintStream(outContent));
        try {
            systemInMock.before();
        }
        catch (Throwable e){
            e.printStackTrace();
        }
    }

    public void cleanUpStreams() {
        System.setOut(null);
    }

    public void startAppWithSampleParameters(){
        new BibliotecaApp(BibliotecaApp.getSampleLibrary(), System.in);
    }

    public void fakeKeyboardInput(String input){
        systemInMock.provideText(input + "\n");
    }

    /**
    *Checks if the specified string was just sent to system.out
     */
    public int getIndexInLastLineOfOutput(String expectedOutput) {
        return getLastLineFromOutput().indexOf(expectedOutput);
    }


    /**
     *Checks if the specified string was sent to system.out
     */
    public int getIndexInOutput(String expectedOutput) {
        return outContent.toString().indexOf(expectedOutput);
    }

    public String getOutput(){
        return outContent.toString();
    }

    public String getLastLineFromOutput(){
        String[] lines = outContent.toString().split("\n");
        if (lines.length == 0)
            return "";
        String lastLine = lines[lines.length - 1];
        return lastLine;
    }

    public void exit(){
        //say quit twice because he wants to see that you really want to quit
        fakeKeyboardInput("Quit");
        fakeKeyboardInput("Quit");
    }
}
