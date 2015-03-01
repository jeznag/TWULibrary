package com.twu.biblioteca;


import java.util.ArrayList;

public class Library{

    ArrayList<Book> listBooks = new ArrayList<Book>();

   public Library(){
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
           return "Who you trying to fool? There's no such book\n";
       return bookToReturn.returnBook();
   }


    public String borrowBook(String title){
        Book bookToBorrow = getBookByTitle(title);
        if (bookToBorrow == null)
            return "Who you trying to fool? There's no such book\n";
        return bookToBorrow.borrowBook();
    }

   public String listBooks(){
	String listOfBooks = "";
	for (Book book : listBooks){
		listOfBooks += book.getSummary();
	}
	return listOfBooks;
   }

}