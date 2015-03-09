package com.twu.biblioteca.unit;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.twu.biblioteca.TestHelper;
import com.twu.biblioteca.model.factory.LibraryFactory;
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
    public void check_that_library_book_summary_is_correct() throws InvalidArgumentException{
        LibraryModel testLibrary = LibraryFactory.getSampleBookLibrary();
        LibraryView libraryView = new LibraryView(testLibrary);
        assertEquals(libraryView.listBooks(), "'Shantaram' by Gregory David Roberts published in 2003\n'The Art of War' by Sun Tzu published in 1910\n");
    }

    @Test
    public void check_that_library_movie_summary_is_correct() throws InvalidArgumentException{
        LibraryModel testLibrary = LibraryFactory.getSampleMovieLibrary();
        LibraryView libraryView = new LibraryView(testLibrary);
        assertEquals(libraryView.listFilms(), "Film: 'Zug des Lebens' directed by Radu Mihaileanu released in 1998. Rating: 10\n" +
            "Film: 'Mad Max' directed by George Miller released in 1979. Rating: 8\n");
    }

    @Test
    public void check_that_library_movie_summary_does_not_contain_books() throws InvalidArgumentException{
        LibraryModel testLibrary = LibraryFactory.getSampleMovieLibrary();
        LibraryView libraryView = new LibraryView(testLibrary);
        assertEquals(libraryView.listBooks(), "");
    }

    @Test
    public void check_that_merged_library_summary_is_correct() throws InvalidArgumentException{
        LibraryModel testLibrary = LibraryFactory.getSampleBookAndMovieLibrary();
        LibraryView libraryView = new LibraryView(testLibrary);
        assertEquals(libraryView.listFilms(), "Film: 'Zug des Lebens' directed by Radu Mihaileanu released in 1998. Rating: 10\n" +
                "Film: 'Mad Max' directed by George Miller released in 1979. Rating: 8\n");
        assertEquals(libraryView.listBooks(), "'Shantaram' by Gregory David Roberts published in 2003\n'The Art of War' by Sun Tzu published in 1910\n");
    }

}
