package com.twu.biblioteca.controller.console;

import com.twu.biblioteca.controller.console.ConsoleInterface;

import java.util.Scanner;

/**
 * Created by jeremynagel on 5/03/15.
 */
public class RealConsole implements ConsoleInterface {

        Scanner scanner = new Scanner(System.in);

        @Override
        public void writeToOutput(String line) {
            System.out.print(line);
        }

        @Override
        public String readLineFromInput() {
            return scanner.nextLine();
        }

        @Override
        public void injectLineToInput(String line){
            return;
        }

        @Override
        public String getLastLineFromOutput() {
            return "not needed";
        }

}
