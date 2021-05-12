/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author jonpaulcarlo
 */
public class CATest {

    
    
    
    public static void main(String[] args) 
    {
//       new CATest();
        new Controller();
        
        
        
    }
    
    public CATest(){
        
//        View view1 = new View();
//        view1.main();

//        searchMovie();
//          addCard();
        
        testDate();
        


        
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
//    }
    
    //assigning credit cards to users.
    public void addCard(){
        
        Model model = new Model();
        Card card = new Card();
        Scanner kb = new Scanner(System.in); //for string inputs
        Scanner pn = new Scanner(System.in); //for int inputs
        boolean check;
        String pin;
        
        
        System.out.print("Welcome! \nEnter Card (press enter): ");
        String enter = kb.nextLine(); //simulates entering the card by pressing enter key
        
        System.out.print("New User? (Y/N): "); //will be "enter pin/generate new card in the final"
        String yn = kb.nextLine();
        
        if(yn.equalsIgnoreCase("y")){  //make a little button to generate a card in the final build
//            user.generateCard(); //generates a random card
//            model.UserCard(user); 
            card.generateCard();
            
            while(card.getCardDetails()[1].equalsIgnoreCase(model.checkPin(card)[1])){ //checks if the randomized pin already exists on the database
                card.generateCard(); //generates a new card again
            }
            model.UserCard(card);

        
            System.out.println("Your Card Number is: " + card.getCardDetails()[0]);
            System.out.println("Your PIN: " + card.getCardDetails()[1]); 
            System.out.println("Please remember your PIN!");
        }
        else{
            check = true;  //initialize the check boolean
            
            while(check){  //loops the input stage if the user inputs an invalid pin 
                
                System.out.print("Please enter your 3-digit CVC: ");
                pin = pn.nextLine();
//                user.setCardPin(pin);
                card.setCardDetails(pin);  //assigns the pin on the user --- need a counter on model to confirm if it has a match on the db 
                if(!pin.matches("[a-zA-Z]*")&&(pin.length()==3)&&!pin.contains(" ")){  //  makes sure that the pin has only 3 digits, only contains numbers, and doesn't have a space on it.
                    check = false;
                }else{System.out.println("Please enter a valid pin!\n");}
            }

            if(model.countPin(card)>0){
                String[] tempUser = model.checkPin(card); //fetch the card num/pin on the database
                System.out.println("Your Card # is: "+tempUser[0] + "\nYour Pin: "+tempUser[1]);
            }
            else{
                System.out.println("Incorrect pin!");
            }
            
            
            
        }
        
        
    }
    
    public void testDate(){
        Model model = new Model();
        Rent rent = new Rent();
        Date date = new Date();
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        String sqlConvert = dateFormatter.format(date);
        
//        try{
//            sqlDate = dateFormat.parse(sqlConvert);
//        
//        }catch(ParseException e){
//            System.out.println(e);
//        }

        rent.setRentDate(sqlConvert);
        
        
        model.testDate(rent);
        
        System.out.println(sqlConvert);
        System.out.println(rent.rentDate);

        
    }

        
    
    
}
