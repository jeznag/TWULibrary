package com.twu.biblioteca.model;

import java.util.ArrayList;

public class Book{

   String author;
   String title;
   int yearPublished;
   boolean checkedOut;
   
   public Book(String author, int yearPublished, String title){
    this.author = author;
    this.yearPublished = yearPublished;
    this.title = title;
   }
   
   public String getSummary(){
    if (!checkedOut)
        return "'" + title + "' by " + author + " published in " + yearPublished + "\n";
    return "";
   }

    public String getTitle(){
        return title;
    }
   
   public String borrowBook(){
    if (!checkedOut){
        checkedOut = true;
        return "The book's yours!\n";
    }
    return "That book ain't there sucker\n";
   }
   
   public String returnBook(){
    if (checkedOut){
        checkedOut = false;
        return "Thank you kindly most respectable of all citizens. I worship the ground you stand on.\n";
    }
    return "Who you trying to fool? You never borrowed that book and now you're saying you're returning it? What's wrong with you?\n";
   }

}