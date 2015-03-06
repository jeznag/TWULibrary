package com.twu.biblioteca.unit;


import com.twu.biblioteca.model.Book;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest {


    @Test
    public void check_that_book_summary_is_correct() {
        Book testBook = new Book("Matthew Reilly", 1984, "Scarecrow");
        assertEquals(testBook.getSummary(), "'Scarecrow' by Matthew Reilly published in 1984\n");
    }


}
