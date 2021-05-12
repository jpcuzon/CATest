/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catest;

import java.util.Random;

/**
 *
 * @author jonpaulcarlo
 */
public class Card {
    
    CardGenerator cg = new CardGenerator();
    private String[] cardDetails = new String[2]; 
   
    
    public String[] getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(String cardpin) {
        cardDetails[1] = cardpin;
    }

    
    public void generateCard(){
         this.cardDetails = cg.cardDetails();
    }

    //Innerclass for a card generator
    public class CardGenerator{
        
        Random cardGen = new Random();
        String[] newCard = cardDetails();
    
        public String[] cardDetails(){
            
            String numGen = "4"; //Visa cards always starts with "4"
            String pinGen = "";
            String[] newCard = new String [2];
                        
            for(int i = 0; i<15;i++){
            numGen = numGen + cardGen.nextInt(10);
            }
            
            for(int i = 0; i<3;i++){
            pinGen = pinGen + cardGen.nextInt(10);
            }
            
            newCard[0] = numGen;
            newCard[1] = pinGen;
            
            return newCard;
            
        }
        
        
    }
    
    
}
