/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catest;

/**
 *
 * @author jonpaulcarlo
 */
public class Movies {
    
    protected String title;
    protected String genre;
    protected int release_year;
    protected int num_avail;
    protected int num_rented;
    protected int movie_code;
    
    protected String searchTitle;
    
    public Movies(){
        
    }
    public Movies (String searchTitle){
        this.searchTitle = searchTitle;
    }

    public Movies(String title, String genre, int release_year, int num_avail, int num_rented, int movie_code) {
        this.title = title;
        this.genre = genre;
        this.release_year = release_year;
        this.num_avail = num_avail;
        this.num_rented = num_rented;
        this.movie_code = movie_code;
    }
    
    

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getRelease_year() {
        return release_year;
    }

    public int getNum_avail() {
        return num_avail;
    }

    public int getNum_rented() {
        return num_rented;
    }

    public int getMovie_code() {
        return movie_code;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    @Override
    public String toString() {
        return "Movies{" + "title=" + title + ", genre=" + genre + ", release_year=" + release_year + ", num_avail=" + num_avail + ", num_rented=" + num_rented + ", movie_code=" + movie_code + '}';
    }
}
