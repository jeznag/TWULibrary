package com.twu.biblioteca.model;

import java.util.ArrayList;

public class BookModel extends LibraryItem {

   String author;

    public BookModel(String author, int yearPublished, String title){
        super(yearPublished, title);
        this.author = author;
    }
   
   @Override
   public String getSummary(){
    if (!checkedOut)
        return "'" + title + "' by " + author + " published in " + yearPublished + "\n";
    return "";
   }

}