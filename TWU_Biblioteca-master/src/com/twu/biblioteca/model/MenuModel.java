package com.twu.biblioteca.model;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jeremynagel on 5/03/15.
 */
public class MenuModel {

    private int invalidCommandsInARow = 0;
    private boolean exited = false;
    private final int MAX_INVALID_ENTRIES = 2;

    public boolean isTooManyInvalidCommandsInARow(){
        return invalidCommandsInARow > MAX_INVALID_ENTRIES ? true: false;
    }

    public void incrementInvalidCommands(){
        invalidCommandsInARow++;
    }
    
    public void resetInvalidCommands(){
        invalidCommandsInARow = 0;
    }

    public boolean isExited() {
        return exited;
    }

    public void setExited(boolean exited) {
        this.exited = exited;
    }

    public String isValidItemType(String borrowType) throws InvalidArgumentException{
        String lowerCaseType = borrowType.toLowerCase();
        if (VALID_BORROW_TYPES.contains(lowerCaseType))
            return borrowType;
        throw new InvalidArgumentException(new String[] {"You can't borrow a " + lowerCaseType});

    }

    private final List<String> VALID_BORROW_TYPES = Arrays.asList("book", "film");
}
