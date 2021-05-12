/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 *
 * @author jonpaulcarlo
 */
public class Controller implements ActionListener {
    
    View view;
    Model model;
    
    String search;
    int allMovieCount;  //counts all the movies on the database
    String[][] allMovies;
    String[][] moviesActive; // for sorting
    int countSearch; //counts the search result
    String[][] searchMovieResult;
    int categoriesCount;
    String [][] categoriesResult;
    Movies[] movies;
    
    public Controller(){
      this.view = new View(this);
      this.model = new Model();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("rent")){
            
            view.headerLabel = "All Movies"; //can be changed to "Most Recent/Best seller"
//            allMovieCount = model.allMoviesCount();
//            System.out.println("CA "+allMovieCount);///////////
//            allMovies = model.allMovies();
//            searchMovieResult = null;
//            System.out.println("CA "+allMovieCount);///////////
//            for(int i=0;i<allMovies.length;i++)       //for the rows
//                {
//                    for(int j=0;j<6;j++)   //for the columns
//                    {
//                        System.out.println(allMovies[i][j]);         //initalizing the value of squares into 0;
//
//                    }  
//                    System.out.println("");
//                }

            allMovieCount = model.allMoviesCount();
        
            movies = new Movies[allMovieCount];

            allMovies = model.allMovies();

            for(int i=0;i<allMovies.length;i++){
                movies[i] = new Movies(allMovies[i][0],allMovies[i][1], Integer.parseInt(allMovies[i][2]),Integer.parseInt(allMovies[i][3]),Integer.parseInt(allMovies[i][4]),Integer.parseInt(allMovies[i][5]));
            }

            for(int i=0;i<movies.length;i++){
                System.out.println(movies[i].getTitle());
            }
            view.welcome.dispatchEvent(new WindowEvent(view.welcome, WindowEvent.WINDOW_CLOSING));//disposes the other frame when this frame opens
//            view.welcome.remove();
            view.main();                                                        
            
        }
        
        if(e.getActionCommand().equals("search")){
            
            model = new Model();

            search = view.searchBar.getText();
            countSearch = model.countSearchTitle(search); //count the results
            System.out.println("CS1 "+countSearch);////////////////
            
            if(search.isBlank() || search.isEmpty())
            {
                model.allMoviesCount();
                allMovies = model.allMovies(); 
                searchMovieResult = null;

                for(int i=0;i<allMovies.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(allMovies[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
                
                view.movieSectionL.show(view.movieSection, "moviesHome");

            }
            else{
                
                System.out.println("CS2 "+countSearch);////////////////
                if(countSearch==0)
                {
//                    System.out.println("No Results Found ");

//                    view.movieSectionL.show(view.movieSection, "searchResult");
                }
                else
                {
                    view.headerLabel = "Search Results for \""+search+"\""; //assign the input into the label
                    searchMovieResult = model.searchTitle(search); 
                    allMovies = null;

                    for(int i=0;i<searchMovieResult.length;i++)       //for the rows
                    {
                        for(int j=0;j<6;j++)   //for the columns
                        {
                            System.out.println(searchMovieResult[i][j]);         //initalizing the value of squares into 0;

                        }  
                        System.out.println("");
                    }
                    view.movieSection.add(view.movieSearchPanel(),"searchResult");
                    view.movieSectionL.show(view.movieSection, "searchResult");
                }
                
            }
        }
        
        
        if(e.getActionCommand().equalsIgnoreCase("sort")){
            
            moviesActive = null; //container for the sorting function
            String active=null;
            String sortType = view.sort.getSelectedItem().toString();
            
            
            if(allMovies == null){  //switches the data inside of the container on what data is active.
                moviesActive = searchMovieResult;
                active = "search";

                System.out.println("Search active");
            }else if(searchMovieResult == null){
                moviesActive = allMovies;
                active = "all";
                System.out.println("All active");

            }
            if(active.equalsIgnoreCase("all")){  //Sorts the "All movies" result if the active identifier is "all"
                moviesActive = model.allMoviesSort(sortType);
                for(int i=0;i<allMovies.length;i++)       //for the rows
                    {
                        for(int j=0;j<6;j++)   //for the columns
                        {
                            System.out.println(moviesActive[i][j]);         //initalizing the value of squares into 0;

                        }  
                        System.out.println("");
                    }
                view.movieSection.add(view.moviePanelSort(),"allMoviesSort");
                view.movieSectionL.show(view.movieSection, "allMoviesSort");
            }
            else if(active.equalsIgnoreCase("search")){  //Sorts the search result if the active identifier is "search"
                moviesActive = model.searchTitleSort(search , sortType);
                for(int i=0;i<moviesActive.length;i++)       //for the rows
                    {
                        for(int j=0;j<6;j++)   //for the columns
                        {
                            System.out.println(moviesActive[i][j]);         //initalizing the value of squares into 0;

                        }  
                        System.out.println("");
                    }
                view.movieSection.add(view.movieSearchPanelSort(),"searchResult");
                view.movieSectionL.show(view.movieSection, "searchResult");
            }
            
        }
        
        
        String categories = null;
        
        if(e.getActionCommand().equals("action")){
            System.out.println("action category");
            categories="action";
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        if(e.getActionCommand().equals("comedy")){
            System.out.println("comedy category");
            categories="comedy";
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        if(e.getActionCommand().equals("drama")){
            System.out.println("drama category");
            categories="drama"; //categories identifier
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        if(e.getActionCommand().equals("fantasy")){
            System.out.println("fantasy category");
            categories="fantasy";
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        if(e.getActionCommand().equals("horror")){
            System.out.println("horror category");
            categories="horror";
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        if(e.getActionCommand().equals("mystery")){
            System.out.println("mystery category");
            categories="mystery";
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        if(e.getActionCommand().equals("romance")){
            System.out.println("romance category");
            categories="romance";
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        if(e.getActionCommand().equals("scifi")){
            System.out.println("scifi category");
            categories="scifi";
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        if(e.getActionCommand().equals("thriller")){
            System.out.println("thriller category");
            categories="thriller";
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        if(e.getActionCommand().equals("western")){
            System.out.println("western category");
            categories="western";
            categoriesCount=model.categoriesMoviesCount(categories);
            categoriesResult=model.categoriesMovies(categories);
            System.out.println("count="+ categoriesCount);
            for(int i=0;i<categoriesResult.length;i++)       //for the rows
                {
                    for(int j=0;j<6;j++)   //for the columns
                    {
                        System.out.println(categoriesResult[i][j]);         //initalizing the value of squares into 0;

                    }  
                    System.out.println("");
                }
            view.movieSection.add(view.movieCategories(), "movieCategories");
            view.movieSectionL.show(view.movieSection, "movieCategories");
        }
        
        
        
        
        if(e.getActionCommand().equals("faq")){
            System.out.println("FAQ frame for help");
            view.moviePanel();
        }
  
        
    }
    
    
    
    
    
    
    
    
    
    
//    public void searchMovie(){
//        
//        int countSearch;
//        String searchMovieResult[][];
//        
//        Model model = new Model();
//        Scanner kb = new Scanner(System.in);
//        
//        System.out.print("Welcome! \nSearch: ");
//        String search = kb.nextLine();
//        
//        Movies searchMovie = new Movies(search);
//        
//        model.countSearchTitle(searchMovie);
//        
//        countSearch = model.countSearchTitle(searchMovie);
//        if(countSearch==0)
//        {
//            System.out.println("No Results Found");
//        }
//        else
//        {
//            searchMovieResult = model.searchTitle(searchMovie); 
//           
//            for(int i=0;i<searchMovieResult.length;i++)       //for the rows
//            {
//                for(int j=0;j<4;j++)   //for the columns
//                {
//                    System.out.println(searchMovieResult[i][j]);         //initalizing the value of squares into 0;
//                    
//                }  
//                System.out.println("");
//            }
//
//        }
//
//
}

    
    

