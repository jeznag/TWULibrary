package com.twu.biblioteca.view;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.LibraryModel;

/**
 * Created by jeremynagel on 5/03/15.
 */
public class LibraryView {

    LibraryModel libraryModel;
    public LibraryView(LibraryModel libraryModel){
        this.libraryModel = libraryModel;
    }

    public String listBooks(){
        StringBuilder listOfBooks = new StringBuilder();
        for (Book book : libraryModel.getListBooks()){
            listOfBooks.append(book.getSummary());
        }
        return listOfBooks.toString();
    }

}
