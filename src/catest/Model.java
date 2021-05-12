/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jonpaulcarlo
 */
public class Model {
    int countResult;
    
    //counts the search through name results
    public int countSearchTitle(String search){
        
        int countResult = 0;
        
        try
        {
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
//            String countQuery = "select count(*) from barbers where full_name like '%" + search.getSearchBarber() + "%';"; 
            String countQuery = "Select count(*) from movie_list where movie_title like '%" + search + "%'";
            
            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(countQuery);
            rs.next();
            countResult = rs.getInt(1);
            
            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
   
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return countResult;
    }
    
    //search for a match on movie title
    public String[][] searchTitle(String search)
    {
        
        int countMovie = countSearchTitle(search);
        String[][] searchMovieResult = new String[countMovie][6];

        try
        {
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
            String searchQuery = "Select * from movie_list where movie_title like '%" + search + "%'";
            

            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            //takes the name and locaion information
            String[][] searchMovie = new String[countMovie][6];

            ResultSet rs = stmt.executeQuery(searchQuery);
            int row = 0;
            
            int num_avail;
            while (rs.next()) {                     
                
                searchMovie[row][0] = rs.getString("movie_title");
                searchMovie[row][1] = rs.getString("movie_genre");
                searchMovie[row][2] = rs.getString("release_year");
                searchMovie[row][3] = rs.getString("num_avail");
                searchMovie[row][4] = rs.getString("num_rented");
                searchMovie[row][5] = rs.getString("movie_code");
//                num_avail = rs.getInt("num_avail");
//                if(num_avail>0){
//                    
//                    searchMovie[row][6] = rs.getString("num_avail");
//                    
//                }else{System.out.println("Not Avaialble");}
                row++;
                
            }
            
            searchMovieResult = searchMovie;

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return searchMovieResult;
    }
    
    
    
    //assigns a card number to a customer
    public boolean UserCard(Card cardDetails)
    {
        boolean result = false;
        
        try
        {
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
            String update = "INSERT INTO movie_customers (card_num,card_pin)" +
                    "VALUES ('"+cardDetails.getCardDetails()[0]+"','"+cardDetails.getCardDetails()[1]+"');";

            //Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            //get a statement from the connection
            Statement stmt = conn.createStatement();
            
            //execute the query
            stmt.executeUpdate(update);
            
            stmt.close();
            conn.close();
        
        }catch (SQLException se) 
        {
            System.out.println("SQL Exception: ");
            
            //Loop through the Exceptions
            while(se != null) 
            {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                
                se = se.getNextException();
            } 
        }catch (Exception e)
        {
            System.out.println(e);
        }
        
        
        return result;
    }
    
    //check if there's a much of the pin
    public int countPin(Card pin){
        
        int countResult = 0;
        
        try
        {
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
//            String countQuery = "select count(*) from barbers where full_name like '%" + search.getSearchBarber() + "%';"; 
            String countQuery = "Select count(*) from movie_customers where card_pin like '" + pin.getCardDetails()[1] + "'";
            
            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(countQuery);
            rs.next();
            countResult = rs.getInt(1);
            
            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
   
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return countResult;
    }
    
    
    
    //checks for a match of pin
    public String[] checkPin(Card pin)
    {
        
//        int countMovie = countSearchTitle(search);
        String checkPin[] = new String[2];

        try
        {
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
            String searchQuery = "Select * from movie_customers where card_pin like '%" + pin.getCardDetails()[1] + "%'";
            

            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            //takes the name and locaion information
            String[] pinTemp = new String[2];

            ResultSet rs = stmt.executeQuery(searchQuery);
            int row = 0;
            
            while (rs.next()) {                     
                
                pinTemp[0] = rs.getString("card_num");
                pinTemp[1] = rs.getString("card_pin");                
            }
            
            checkPin = pinTemp;

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return checkPin;
    }
    
    
    
    
    //test for date
    public boolean testDate(Rent rentDate)
    {
        boolean result = false;
        
        try
        {
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
            String update = "INSERT INTO rent (id_customer,id_movie,date_rent,date_return)" +
                    "VALUES (1,1,'"+rentDate.getRentDate()+"','2021-05-01');";

            //Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            //get a statement from the connection
            Statement stmt = conn.createStatement();
            
            //execute the query
            stmt.executeUpdate(update);
            
            stmt.close();
            conn.close();
        
        }catch (SQLException se) 
        {
            System.out.println("SQL Exception: ");
            
            //Loop through the Exceptions
            while(se != null) 
            {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                
                se = se.getNextException();
            } 
        }catch (Exception e)
        {
            System.out.println(e);
        }
        
        
        return result;
    }
    
    //SELECT DATEDIFF((select date_return from rent where id_rent =4),(select date_rent from rent where id_rent =4)) as Penalty; day difference
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //counts the search through name results
    public int allMoviesCount(){
        
        countResult = 0;
        
        try
        {
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
//            String countQuery = "select count(*) from barbers where full_name like '%" + search.getSearchBarber() + "%';"; 
            String countQuery = "Select count(*) from movie_list";
            
            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(countQuery);
            rs.next();
            countResult = rs.getInt(1);
            
            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
   
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return countResult;
    }
    
    //search for a match on movie title
    public String[][] allMovies() 
    {
        
        int allMoviesCount = countResult;
        String[][] searchMovieResult = new String[allMoviesCount][6];

        try
        {
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
            String searchQuery = "Select * from movie_list order by movie_title";
            

            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            //takes the name and locaion information
            String[][] searchMovie = new String[allMoviesCount][6];

            ResultSet rs = stmt.executeQuery(searchQuery);
            int row = 0;
            
            int num_avail;
            while (rs.next()) {                     
                
                searchMovie[row][0] = rs.getString("movie_title");
                searchMovie[row][1] = rs.getString("movie_genre");
                searchMovie[row][2] = rs.getString("release_year");
                searchMovie[row][3] = rs.getString("num_avail");
                searchMovie[row][4] = rs.getString("num_rented");
                searchMovie[row][5] = rs.getString("movie_code");
//                num_avail = rs.getInt("num_avail");
//                if(num_avail>0){
//                    
//                    searchMovie[row][6] = rs.getString("num_avail");
//                     
//                }else{System.out.println("Not Avaialble");}
                row++;
                
            }
            
            searchMovieResult = searchMovie;

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return searchMovieResult;
    }
    
    
    //count the result of the sorting for all movies
//    public int allMoviesSortCount(Movies sort){
//        
//        countResult = 0;
//        
//        try
//        {
//            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
//            String dbUser = "Jon_2019395";
//            String dbPassword = "2019395";
////            String countQuery = "select count(*) from barbers where full_name like '%" + search.getSearchBarber() + "%';"; 
//            String countQuery = "Select count(*) from movie_list";
//            
//            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
//            
//            Statement stmt = conn.createStatement();
//            
//            ResultSet rs = stmt.executeQuery(countQuery);
//            rs.next();
//            countResult = rs.getInt(1);
//            
//            // Close the result set, statement and the connection
//            rs.close();
//            stmt.close();
//            conn.close();
//   
//        } catch (SQLException se) {
//            System.out.println("SQL Exception:");
//
//            // Loop through the SQL Exceptions
//            while (se != null) {
//                System.out.println("State  : " + se.getSQLState());
//                System.out.println("Message: " + se.getMessage());
//                System.out.println("Error  : " + se.getErrorCode());
//
//                se = se.getNextException();
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        
//        return countResult;
//    }
    
    //sorting the movies ///////////////////////////////////////////////////////////////////////////////////////////
    public String[][] allMoviesSort(String sort) 
    {
        
        int allMoviesCount = countResult;
        String[][] sortMoviesResult = new String[allMoviesCount][6];
        String sortType = sort;
        String searchQuery=null;
        switch(sortType){
            case "Name": searchQuery = "Select * from movie_list order by movie_title";break;
            case "Popularity": searchQuery = "Select * from movie_list order by num_rented";break;
            case "Genre": searchQuery = "Select * from movie_list order by movie_genre";break;
            case "Release Date": searchQuery = "Select * from movie_list order by release_year";break;
        }
        try
        {
//            String searchQuery=null;
//            String sortType = sort.getSortOrder();
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
//            switch(sortType){
//                case "Name": searchQuery = "Select * from movie_list order by movie_title";break;
//                case "Popularity": searchQuery = "Select * from movie_list order by movie_title";break;
//                case "Genre": searchQuery = "Select * from movie_list order by num_rented";break;
//                case "Release_year": searchQuery = "Select * from movie_list order by release_year";break;
//            }
            System.out.println("Sort Type: "+sortType+" Query: "+searchQuery);
            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            //takes the name and locaion information
            String[][] sortMovies = new String[allMoviesCount][6];

            ResultSet rs = stmt.executeQuery(searchQuery);
            int row = 0;
            
            int num_avail;
            while (rs.next()) {                     
                
                sortMovies[row][0] = rs.getString("movie_title");
                sortMovies[row][1] = rs.getString("movie_genre");
                sortMovies[row][2] = rs.getString("release_year");
                sortMovies[row][3] = rs.getString("num_avail");
                sortMovies[row][4] = rs.getString("num_rented");
                sortMovies[row][5] = rs.getString("movie_code");
//                num_avail = rs.getInt("num_avail");
//                if(num_avail>0){
//                    
//                    searchMovie[row][6] = rs.getString("num_avail");
//                     
//                }else{System.out.println("Not Avaialble");}
                row++;
                
            }
            
            sortMoviesResult = sortMovies;

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return sortMoviesResult;
    }
    
    
    
    //sorts the search results
    public String[][] searchTitleSort(String search, String sort)
    {
        
        int countMovie = countSearchTitle(search);
        String[][] searchMovieResult = new String[countMovie][6];
        String sortType = sort;
        String searchQuery=null;
        switch(sortType){
            case "Name": searchQuery = "Select * from movie_list where movie_title like '%" + search + "%'" + " order by movie_title";break;
            case "Popularity": searchQuery = "Select * from movie_list where movie_title like '%" + search + "%'" + " order by num_rented";break;
            case "Genre": searchQuery = "Select * from movie_list where movie_title like '%" + search + "%'" + " order by movie_genre";break;
            case "Release Date": searchQuery = "Select * from movie_list where movie_title like '%" + search + "%'" + " order by release_year";break;
        }
            
        try
        {
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";
//            String searchQuery = "Select * from movie_list where movie_title like '%" + search + "%'";
            

            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            //takes the name and locaion information
            String[][] searchMovie = new String[countMovie][6];

            ResultSet rs = stmt.executeQuery(searchQuery);
            int row = 0;
            
            int num_avail;
            while (rs.next()) {                     
                
                searchMovie[row][0] = rs.getString("movie_title");
                searchMovie[row][1] = rs.getString("movie_genre");
                searchMovie[row][2] = rs.getString("release_year");
                searchMovie[row][3] = rs.getString("num_avail");
                searchMovie[row][4] = rs.getString("num_rented");
                searchMovie[row][5] = rs.getString("movie_code");
//                num_avail = rs.getInt("num_avail");
//                if(num_avail>0){
//                    
//                    searchMovie[row][6] = rs.getString("num_avail");
//                    
//                }else{System.out.println("Not Avaialble");}
                row++;
                
            }
            
            searchMovieResult = searchMovie;

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return searchMovieResult;
    }
    
    
    public int categoriesMoviesCount(String categories) 
    {
        
        countResult=0;
        
        String cateTypes = categories;
        String searchQuery=null;
        System.out.println("count result"+ cateTypes);
        switch(cateTypes){
            case "action": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre='action' or movie_genre='adventure'"; break;
            case "comedy": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre ='comedy'";break;
            case "drama": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre='drama'";break;
            case "fantasy": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre='fantasy'";break;
            case "horror": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre='horror'";break;
            case "mystery": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre='mystery'";break;
            case "romance": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre='romance'";break;
            case "scifi": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre='sci-fi'";break;
            case "thriller": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre='thriller'";break;
            case "western": searchQuery = "SELECT count(*) FROM movie_list WHERE movie_genre='western'";break;
        }
        try
        {
//            String searchQuery=null;
//            String sortType = sort.getSortOrder();
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";

            System.out.println("Sort Type: "+cateTypes+" Query: "+searchQuery);
            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            //takes the name and locaion information
//            String[][] cateList = new String[countResult][6];

            ResultSet rs = stmt.executeQuery(searchQuery);
//            int row = 0;
//            
//            int num_avail;
            rs.next();
            countResult=rs.getInt(1);
//            while (rs.next()) {                     
//                
//                cateList[row][0] = rs.getString("movie_title");
//                cateList[row][1] = rs.getString("movie_genre");
//                cateList[row][2] = rs.getString("release_year");
//                cateList[row][3] = rs.getString("num_avail");
//                cateList[row][4] = rs.getString("num_rented");
//                cateList[row][5] = rs.getString("movie_code");
//                num_avail = rs.getInt("num_avail");
//                if(num_avail>0){
//                    
//                    searchMovie[row][6] = rs.getString("num_avail");
//                     
//                }else{System.out.println("Not Avaialble");}
//                row++;
                
//            }
            
//            cateResult = cateList;

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return countResult;
    }
    
    
    
    
    
    public String[][] categoriesMovies(String categories) 
    {
        
        int allMoviesCount = countResult;
        String[][] cateResult = new String[allMoviesCount][6];
        String cateTypes = categories;
        String searchQuery=null;
        switch(cateTypes){
            case "action": searchQuery = "SELECT * FROM movie_list WHERE movie_genre='action' or movie_genre='adventure'"; break;
            case "comedy": searchQuery = "SELECT * FROM movie_list WHERE movie_genre ='comedy'";break;
            case "drama": searchQuery = "SELECT * FROM movie_list WHERE movie_genre='drama'";break;
            case "fantasy": searchQuery = "SELECT * FROM movie_list WHERE movie_genre='fantasy'";break;
            case "horror": searchQuery = "SELECT * FROM movie_list WHERE movie_genre='horror'";break;
            case "mystery": searchQuery = "SELECT * FROM movie_list WHERE movie_genre='mystery'";break;
            case "romance": searchQuery = "SELECT * FROM movie_list WHERE movie_genre='romance'";break;
            case "scifi": searchQuery = "SELECT * FROM movie_list WHERE movie_genre='sci-fi'";break;
            case "thriller": searchQuery = "SELECT * FROM movie_list WHERE movie_genre='thriller'";break;
            case "western": searchQuery = "SELECT * FROM movie_list WHERE movie_genre='western'";break;
        }
        try
        {
//            String searchQuery=null;
//            String sortType = sort.getSortOrder();
            String dbServer = "jdbc:mysql://52.50.23.197:3306/Jon_2019395?useSSL=false";
            String dbUser = "Jon_2019395";
            String dbPassword = "2019395";

            System.out.println("Sort Type: "+cateTypes+" Query: "+searchQuery);
            Connection conn = DriverManager.getConnection(dbServer,dbUser,dbPassword);
            
            Statement stmt = conn.createStatement();
            
            //takes the name and locaion information
            String[][] cateList = new String[allMoviesCount][6];

            ResultSet rs = stmt.executeQuery(searchQuery);
            int row = 0;
            
            int num_avail;
            while (rs.next()) {                     
                
                cateList[row][0] = rs.getString("movie_title");
                cateList[row][1] = rs.getString("movie_genre");
                cateList[row][2] = rs.getString("release_year");
                cateList[row][3] = rs.getString("num_avail");
                cateList[row][4] = rs.getString("num_rented");
                cateList[row][5] = rs.getString("movie_code");
//                num_avail = rs.getInt("num_avail");
//                if(num_avail>0){
//                    
//                    searchMovie[row][6] = rs.getString("num_avail");
//                     
//                }else{System.out.println("Not Avaialble");}
                row++;
                
            }
            
            cateResult = cateList;

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return cateResult;
    }
    
    
    
}
