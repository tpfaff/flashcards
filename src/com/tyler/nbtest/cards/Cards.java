/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest.cards;

/**
 *
 * @author Tyler
 */
public class Cards {
    private String cardFront;
    private String cardBack;
    public Cards(){
    
    }
    public void setCardBack(String backText){
        cardBack=backText;
    }
    public void setCardFront(String frontText){
        cardFront=frontText;
    }
    public String getCardFront(){
        return cardFront;
    }
    public String getCardBack(){
        return cardBack;
    }
}
