package com.twu.biblioteca.unit;

import com.twu.biblioteca.model.BookModel;
import com.twu.biblioteca.model.MovieModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jeremynagel on 9/03/15.
 */
public class MovieModelTest {

        @Test
        public void check_that_movie_summary_is_correct() {
            MovieModel testLibraryItem = new MovieModel("Radu Mihaileanu", 1998, "Zug des Lebens", 10);
            assertEquals(testLibraryItem.getSummary(), "Film: 'Zug des Lebens' directed by Radu Mihaileanu released in 1998. Rating: 10\n");
        }


}
