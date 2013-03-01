/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest.cards;

import java.util.ArrayList;

/**
 *
 * @author Tyler
 */
public class Subject {
    private ArrayList<Cards> noteCards=new ArrayList<Cards>();
    private String subjectName;
    private int id;
    
    public Subject(){
        
    }
    public ArrayList<Cards> getCards(){
        return noteCards;
    }
    public String getSubjectName(){
        return subjectName;
    }
    public void addCard(Cards card){
        noteCards.add(card);
    }
    public void setSubjectName(String subjectName){
        this.subjectName=subjectName;
    }
    public ArrayList<String> getCardNames(){
        ArrayList<String> cardNames=new ArrayList<String>();
        if(noteCards.size()>0){
        for(Cards card:noteCards){
            cardNames.add(card.getCardFront());
        }
        return cardNames;
    }else{
          //cardNames.add("");
         return cardNames;
        }
}
}