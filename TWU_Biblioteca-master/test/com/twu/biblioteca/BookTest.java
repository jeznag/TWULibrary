package com.twu.biblioteca;


import com.twu.biblioteca.model.Book;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest {


    @Test
    public void bookSummaryCorrect() {
        Book testBook = new Book("Matthew Reilly", 1984, "Scarecrow");
        assertEquals(testBook.getSummary(), "'Scarecrow' by Matthew Reilly published in 1984\n");
    }


}
