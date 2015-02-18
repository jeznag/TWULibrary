package com.twu.biblioteca;


import java.util.ArrayList;

public class Library{
   
   List<Book> listBooks = new ArrayList<Book>();

   public Library(){
   }
   
   public void addBook(Book bookToAdd){
	listBooks.add(bookToAdd);
   }

   public void removeBook(Book bookToRemove){
	listBooks.remove(bookToRemove);
   }
   
   public Book getBookByTitle(String title){
   	for (Book book: listBooks){
   		if (book.getTitle().equalsIgnoreCase(title))
   			return book;
   	}
   	return null;
   }
   
   public String listBooks(){
	String listOfBooks = "";
	for (Book book : listBooks){
		listOfBooks += book.getSummary();
	}
	return listOfBooks;
   }

}