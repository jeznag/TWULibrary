package com.twu.biblioteca.unit;


import com.twu.biblioteca.model.BookModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookModelTest {


    @Test
    public void check_that_book_summary_is_correct() {
        BookModel testLibraryItem = new BookModel("Matthew Reilly", 1984, "Scarecrow");
        assertEquals(testLibraryItem.getSummary(), "'Scarecrow' by Matthew Reilly published in 1984\n");
    }


}
