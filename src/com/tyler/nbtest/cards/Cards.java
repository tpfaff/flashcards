/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest.cards;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 *
 * @author Tyler
 */
public class Cards {
    private String cardFront;
    private String cardBack;
    private Drawable cardImage;
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
    
    public void setCardImage(int drawableId,Activity parent){
    	Resources res=parent.getResources();
    	cardImage=res.getDrawable(drawableId);
    }
}
