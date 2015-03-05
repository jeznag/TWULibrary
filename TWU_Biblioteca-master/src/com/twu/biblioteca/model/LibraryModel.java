package com.twu.biblioteca.model;

import com.twu.biblioteca.view.LibraryView;

import java.util.ArrayList;

/**
 * Created by jeremynagel on 5/03/15.
 */
public class LibraryModel {

    public LibraryModel(){
    }

    public void addBook(Book bookToAdd){
        listBooks.add(bookToAdd);
    }

    public void removeBook(Book bookToRemove){
        listBooks.remove(bookToRemove);
    }

    private Book getBookByTitle(String title){
        for (Book book: listBooks){
            if (book.getTitle().equalsIgnoreCase(title))
                return book;
        }
        return null;
    }

    public String returnBook(String title){
        Book bookToReturn = getBookByTitle(title);
        if (bookToReturn == null)
            return "Who you trying to fool? There's no such book\n All we have is ";
        return bookToReturn.returnBook();
    }


    public String borrowBook(String title){
        Book bookToBorrow = getBookByTitle(title);
        if (bookToBorrow == null)
            return "Who you trying to fool? There's no such book\n";
        return bookToBorrow.borrowBook();
    }


    public ArrayList<Book> getListBooks() {
        return listBooks;
    }

    private ArrayList<Book> listBooks = new ArrayList<Book>();


}
