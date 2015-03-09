package com.twu.biblioteca.unit;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.twu.biblioteca.controller.BibliotecaAppController;
import com.twu.biblioteca.controller.MenuController;
import com.twu.biblioteca.controller.console.FakeConsole;
import com.twu.biblioteca.model.factory.LibraryFactory;
import com.twu.biblioteca.model.LibraryModel;
import com.twu.biblioteca.view.LibraryView;
import com.twu.biblioteca.view.MenuView;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by jeremynagel on 9/03/15.
 */
public class MenuUnitTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();
    @Test
    public void check_that_system_rejects_invalid_borrow_type() throws InvalidArgumentException{
        thrown.expect(InvalidArgumentException.class);
        thrown.expectMessage("You can't borrow a car");

        LibraryModel sampleLibrary = LibraryFactory.getSampleBookAndMovieLibrary();
        BibliotecaAppController app = new BibliotecaAppController(sampleLibrary, new FakeConsole());
        MenuController menuController = new MenuController(sampleLibrary, new MenuView(app), new LibraryView(sampleLibrary), app);
        menuController.processCommand("Borrow Car", null);
    }
}
