package com.twu.biblioteca.model;

/**
 * Created by jeremynagel on 6/03/15.
 */
public abstract class LibraryItem {
    protected String title;
    protected int yearPublished;
    protected boolean checkedOut;
    protected UserModel userWhoCheckedOutItem;

    public LibraryItem(int yearPublished, String title) {
        this.yearPublished = yearPublished;
        this.title = title;
    }

    public abstract String getSummary();

    public String getTitle(){
        return title;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public String borrowLibraryItem(UserModel userWhoCheckedOutItem){
     if (!checkedOut){
         checkedOut = true;
         this.userWhoCheckedOutItem = userWhoCheckedOutItem;
         return "The book's yours!\n";
     }
     return "That book ain't there sucker\n";
    }

    public String returnLibraryItem(){
     if (checkedOut){
         checkedOut = false;
         this.userWhoCheckedOutItem = null;
         return "Thank you kindly most respectable of all citizens. I worship the ground you stand on.\n";
     }
     return "Who you trying to fool? You never borrowed that book and now you're saying you're returning it? What's wrong with you?\n";
    }


    public UserModel getUserWhoCheckedOutItem() {
        return userWhoCheckedOutItem;
    }

}
