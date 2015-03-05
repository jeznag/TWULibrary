package com.twu.biblioteca.controller.console;

import com.twu.biblioteca.controller.console.ConsoleInterface;

import java.util.LinkedList;

/**
 * Created by jeremynagel on 5/03/15.
 */
public class FakeConsole implements ConsoleInterface {

    LinkedList<String> outputBuffer = new LinkedList<String>();
    LinkedList<String> inputBuffer = new LinkedList<String>();

    public FakeConsole(){
    }

    @Override
    public void writeToOutput(String line) {
        outputBuffer.add(line);
    }

    @Override
    public String readLineFromInput() {
        String lastLineFromInput = inputBuffer.poll();
        return lastLineFromInput;
    }

    @Override
    public void injectLineToInput(String line){
        inputBuffer.add(line);
    }

    @Override
    public String getLastLineFromOutput() {
        if (outputBuffer.size() > 0)
            return outputBuffer.getLast();
        return "";
    }

    public String getOutput(){
        return outputBuffer.toString();
    }
}
