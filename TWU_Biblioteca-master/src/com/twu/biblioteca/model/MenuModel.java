package com.twu.biblioteca.model;

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

}
