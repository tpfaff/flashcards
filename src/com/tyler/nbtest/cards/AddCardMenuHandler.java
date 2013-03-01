/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest.cards;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;
import com.tyler.nbtest.CardListFragment;
import com.tyler.nbtest.SubjectListFragment;
import java.util.ArrayList;

/**
 *
 * @author Tyler
 */
public class AddCardMenuHandler {
    Context parent;
    SubjectListFragment top=new SubjectListFragment();
    CardListFragment cList=new CardListFragment();
    Subject subjectSelected=new Subject();
    int parentPosition;
    ArrayList<Subject> masterList=new ArrayList<Subject>();
    
    public AddCardMenuHandler(Context parent,ArrayList<Subject> masterList,SubjectListFragment top,CardListFragment cList){
        this.parent=parent;
        this.masterList=masterList;
        this.cList=cList;
        this.top=top;
//        Toast.makeText(parent, Integer.toString(top.getSelectedItemPosition()), Toast.LENGTH_LONG).show();
        parentPosition=top.getSelectedItemPosition();
        subjectSelected=masterList.get(parentPosition);
        
        CardAlertBuilder(this.parent);
           
    }
    
    private void CardAlertBuilder(final Context parent){
        AlertDialog.Builder alert = new AlertDialog.Builder(parent);
        final EditText input = new EditText(parent);
        

        // Set an EditText view to get user input 
        alert.setTitle("Enter A New Card");
        alert.setView(input);
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            
            public void onClick(DialogInterface dialog, int whichButton) {
                ArrayList<String> words=new ArrayList<String>();
                
                Cards card=new Cards();
                card.setCardFront(input.getText().toString());
                subjectSelected.getCards().add(card); //add the card to the subject
                for(Cards eCard:subjectSelected.getCards()){
                    words.add(eCard.getCardFront());
                }
                cList.refreshList(words);
               
            }
            
        });
        
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {}
        });
    
        alert.show();
}
    public Subject getSubjectModified(){
        return subjectSelected;
    }
    public int getIndexToReplace(){
        return parentPosition;
    }
}
