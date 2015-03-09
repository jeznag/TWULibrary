package com.twu.biblioteca.model.factory;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.twu.biblioteca.model.BookModel;
import com.twu.biblioteca.model.LibraryItem;
import com.twu.biblioteca.model.LibraryModel;
import com.twu.biblioteca.model.MovieModel;

/**
 * Object Mother pattern
 * Created by jeremynagel on 9/03/15.
 */
public class LibraryFactory {
    public static LibraryModel getSampleBookLibrary(){
        try{
         LibraryModel library = new LibraryModel();
         BookModel testLibraryItem = new BookModel("Gregory David Roberts", 2003, "Shantaram");
         BookModel testLibraryItem2 = new BookModel("Sun Tzu", 1910, "The Art of War"); //first proper translation

         library.addLibraryItem(testLibraryItem);
         library.addLibraryItem(testLibraryItem2);
         return library;
        }
        catch (InvalidArgumentException e){
            e.printStackTrace();
        }
        return null;
    }

    public static LibraryModel getSampleMovieLibrary(){
        try {
            LibraryModel library = new LibraryModel();
            LibraryItem testLibraryItem = new MovieModel("Radu Mihaileanu", 1998, "Zug des Lebens", 10);
            LibraryItem testLibraryItem2 = new MovieModel("George Miller", 1979, "Mad Max", 8);

            library.addLibraryItem(testLibraryItem);
            library.addLibraryItem(testLibraryItem2);
            return library;
        }catch (InvalidArgumentException e){
            e.printStackTrace();
        }
        return null;
    }

    public static LibraryModel getSampleBookAndMovieLibrary(){
        LibraryModel combo = getSampleBookLibrary().mergeLibraries(getSampleMovieLibrary());
        return combo;
    }
}
