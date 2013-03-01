/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest.cards;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import com.tyler.nbtest.SubjectListFragment;
import java.util.ArrayList;

/**
 *
 * @author Tyler
 */
public class AddSubjectMenuHandler {
    private String subjectToAdd="";
    private final Context parent;
    private ArrayList<String> subjects;
    private Subject newSubject=new Subject();
    SubjectListFragment subjectListView;
    
    public AddSubjectMenuHandler(Context parent,ArrayList<String> subjects,SubjectListFragment subjectListView){
        this.parent=parent;
        this.subjects=subjects;
        this.subjectListView=subjectListView;
        SubjectAlertBuilder(parent);
    }

    private void SubjectAlertBuilder(final Context parent){
        AlertDialog.Builder alert = new AlertDialog.Builder(parent);
        final EditText input = new EditText(parent);
        

        // Set an EditText view to get user input 
        alert.setTitle("Enter A New Subject");
        alert.setView(input);
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            
            public void onClick(DialogInterface dialog, int whichButton) {
                
                newSubject.setSubjectName(input.getText().toString());
                subjects.add(newSubject.getSubjectName());
                subjectListView.refreshList(subjects);
            }
            
        });
        
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {}
        });
    
        alert.show();
}
    
 public Subject getNewSubject(){
     return newSubject;
 }
    
}
