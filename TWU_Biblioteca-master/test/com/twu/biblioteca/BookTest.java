package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest {

    //private TestHelper testHelper;

    /*@Before
    public void beforeTest() {
        testHelper = new TestHelper();
        testHelper.setUpStreams();
    }


    @AfterR
    public void afterTest() {
        testHelper.cleanUpStreams();
    }*/

    @Test
    public void bookSummaryCorrect() {
        Book testBook = new Book("Matthew Reilly", 1984, "Scarecrow");
        assertEquals(testBook.getSummary(), "'Scarecrow' by Matthew Reilly published in 1984\n");
    }


}
