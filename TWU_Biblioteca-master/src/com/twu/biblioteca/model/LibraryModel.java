package com.twu.biblioteca.model;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.twu.biblioteca.view.LibraryView;

import java.awt.print.Book;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by jeremynagel on 5/03/15.
 */
public class LibraryModel {

    public LibraryModel(){
    }

    public void addLibraryItem(LibraryItem libraryItemToAdd) throws InvalidArgumentException{
        if (libraryItemToAdd instanceof BookModel)
            getListBooks().add((BookModel)libraryItemToAdd);
        else if (libraryItemToAdd instanceof MovieModel)
            getListFilms().add((MovieModel)libraryItemToAdd);
        else
            throw new InvalidArgumentException(new String[]{"Invalid argument: " + libraryItemToAdd});
    }

    public void removeLibraryItem(LibraryItem libraryItemToRemove) throws InvalidArgumentException{
        if (libraryItemToRemove instanceof BookModel)
            getListBooks().remove((BookModel) libraryItemToRemove);
        else if (libraryItemToRemove instanceof MovieModel)
            getListFilms().remove((MovieModel) libraryItemToRemove);
        else
            throw new InvalidArgumentException(new String[]{"Invalid argument: " + libraryItemToRemove});
    }

    private LibraryItem getLibraryItemByTitle(String title, ArrayList<? extends LibraryItem> listOfItems){
        for (LibraryItem libraryItem : listOfItems){
            if (libraryItem.getTitle().equalsIgnoreCase(title))
                return libraryItem;
        }
        return null;
    }

    private LibraryItem getBookByTitle(String title){
        return getLibraryItemByTitle(title, getListBooks());
    }

    public String returnLibraryItem(String title, ArrayList<? extends LibraryItem> listOfItems) throws InvalidArgumentException{
        LibraryItem libraryItemToReturn = getLibraryItemByTitle(title, listOfItems);
        if (libraryItemToReturn == null)
            throw new InvalidArgumentException(new String[]{"Who you trying to fool? We don't stock " + title + "\nHave a look at the list of items and try again\n"});
        return libraryItemToReturn.returnLibraryItem();
    }


    public String borrowLibraryItem(String title, ArrayList<? extends LibraryItem> listOfItems, UserModel userWhoWantsToBorrow) throws InvalidArgumentException{
        LibraryItem libraryItemToBorrow = getLibraryItemByTitle(title, listOfItems);
        if (libraryItemToBorrow == null)
            throw new InvalidArgumentException(new String[]{ "Who you trying to fool? We don't stock " + title + "\nHave a look at the list of items and try again\n"});
        return libraryItemToBorrow.borrowLibraryItem(userWhoWantsToBorrow);
    }

    public String whoBorrowedLibraryItem(String title, ArrayList<? extends LibraryItem> listOfItems) throws InvalidArgumentException{
        LibraryItem libraryItemToCheck = getLibraryItemByTitle(title, listOfItems);
        if (libraryItemToCheck == null)
            throw new InvalidArgumentException(new String[]{ "Who you trying to fool? We don't stock " + title + "\nHave a look at the list of items and try again\n"});
        UserModel userWhoBorrowed = libraryItemToCheck.getUserWhoCheckedOutItem();
        if (userWhoBorrowed == null)
            return LibraryView.MESSAGE_WHEN_ITEM_IN_LIBRARY;
        return userWhoBorrowed.getSummaryForWhoDunnit();
    }

    public ArrayList<BookModel> getListBooks() {
        return listBooks;
    }

    public ArrayList<MovieModel> getListFilms() {
        return listFilms;
    }

    public LibraryModel mergeLibraries(LibraryModel toMerge){
        getListBooks().addAll(toMerge.getListBooks());
        getListFilms().addAll(toMerge.getListFilms());
        return this;
    }

    public ArrayList<LibraryItem> getAppropriateListOfItems(String borrowType){
        Method method;
        try {
            method = this.getClass().getMethod("getList" + capitaliseFirstLetter(borrowType) + "s");
            return (ArrayList<LibraryItem>) method.invoke(this);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String capitaliseFirstLetter(String input){
        String output = input.substring(0, 1).toUpperCase() + input.substring(1);
        return output;
    }

    private ArrayList<BookModel> listBooks = new ArrayList<BookModel>();

    private ArrayList<MovieModel> listFilms = new ArrayList<MovieModel>();

}
