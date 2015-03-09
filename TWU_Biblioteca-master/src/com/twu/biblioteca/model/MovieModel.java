package com.twu.biblioteca.model;

/**
 * Created by jeremynagel on 6/03/15.
 */
public class MovieModel extends LibraryItem{

    String director;
    int rating;

    public MovieModel(String director, int yearPublished, String title, int rating){
        super(yearPublished, title);
        this.director = director;
        this.rating = rating;
    }

    @Override
    public String getSummary(){
        if (!checkedOut)
            return "Film: '" + title + "' directed by " + director + " released in " + yearPublished + ". Rating: " + rating + "\n";
        return "";
    }

}