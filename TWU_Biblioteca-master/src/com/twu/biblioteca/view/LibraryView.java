package com.twu.biblioteca.view;

import com.twu.biblioteca.model.LibraryItem;
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
        for (LibraryItem libraryItem : libraryModel.getListBooks()){
            listOfBooks.append(libraryItem.getSummary());
        }
        return listOfBooks.toString();
    }

    public String listFilms(){
        StringBuilder listOfBooks = new StringBuilder();
        for (LibraryItem libraryItem : libraryModel.getListFilms()){
            listOfBooks.append(libraryItem.getSummary());
        }
        return listOfBooks.toString();
    }

    public static final String MESSAGE_WHEN_ITEM_IN_LIBRARY = "It's mine! If you want it, you've gotta be nice to me.";
}
