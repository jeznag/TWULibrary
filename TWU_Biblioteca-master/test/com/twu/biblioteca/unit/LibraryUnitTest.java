package com.twu.biblioteca.unit;

import com.twu.biblioteca.TestHelper;
import com.twu.biblioteca.controller.BibliotecaAppController;
import com.twu.biblioteca.model.LibraryModel;
import com.twu.biblioteca.view.LibraryView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jeremynagel on 6/03/15.
 */
public class LibraryUnitTest {
    protected TestHelper testHelper;

    @Before
    public void beforeTest() {
	    testHelper = new TestHelper();
    }

    @After
    public void afterTest() {
        testHelper.exit();
    }

    @Test
    public void check_that_library_summary_is_correct() {
        LibraryModel testLibrary = BibliotecaAppController.getSampleLibrary();
        LibraryView libraryView = new LibraryView(testLibrary);
        assertEquals(libraryView.listBooks(), "'Shantaram' by Gregory David Roberts published in 2003\n'The Art of War' by Sun Tzu published in 1910\n");
    }
}
