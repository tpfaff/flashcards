/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest.cards;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.EditText;
import com.tyler.nbtest.CardListFragment;
import com.tyler.nbtest.SubjectListFragment;
import java.util.ArrayList;
import com.tyler.nbtest.R;

/**
 *
 * @author Tyler
 */
public class RemoveContentMenuHandler {
    boolean confirmed;
    Context parent;
    ArrayList<Subject> subjects;
    SubjectListFragment list;
    int selectedItemIndex;
    MenuItem item;
    public RemoveContentMenuHandler(Context parent, ArrayList<String> subjects,CardListFragment list){
  //  list.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
    public RemoveContentMenuHandler(Context parent,ArrayList<Subject> subjects,SubjectListFragment list,int selectedItemIndex,MenuItem item){
       this.parent=parent;
       this.subjects=subjects;
       this.list=list;
       this.selectedItemIndex=selectedItemIndex;
       this.item=item;
                        confirmDeletionBuilder(parent);
                       
   /* if(list.getAdvancingMode()==true){
    list.setAdvancingMode(false);
    list.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    
    }else{
       
        list.setAdvancingMode(true);
        list.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
    }*/
    }
    private void confirmDeletionBuilder(final Context parent){
        AlertDialog.Builder alert = new AlertDialog.Builder(parent);
        final EditText input = new EditText(parent);
        
        

        // Set an EditText view to get user input 
        alert.setTitle("Confirm");
        alert.setMessage("Delete forever?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            
            public void onClick(DialogInterface dialog, int whichButton) {
                ArrayList<String> words=new ArrayList<String>();
                
                 subjects.remove(selectedItemIndex);
                         item.setIcon(R.drawable.content_remove);
                        ArrayList<String> subjectNames=new ArrayList<String>();
                         for(Subject subject:subjects){
                             subjectNames.add(subject.getSubjectName());
                         }
                         list.refreshList(subjectNames);
                //subjectSelected.getCards().add(card); //add the card to the subject
               // for(Cards eCard:subjectSelected.getCards()){
                 //   words.add(eCard.getCardFront());
               // }
               // cList.refreshList(words);
               
            }
            
        });
        
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                item.setIcon(R.drawable.content_remove);
            confirmed=false;
            }
        });
    
        alert.show();
}
    public void deleteSubject(){
        
    }
   
    
}
