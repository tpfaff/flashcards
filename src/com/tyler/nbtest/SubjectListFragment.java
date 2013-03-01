/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest;

import android.R.menu;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.tyler.nbtest.cards.RemoveContentMenuHandler;
import com.tyler.nbtest.cards.Subject;
import java.util.ArrayList;
/**
 *
 * @author Tyler
 */
public class SubjectListFragment extends ListFragment {
    boolean mTwoPane;
    MenuItem item;
    boolean advancingMode;
    private ArrayList<String> subjectNames=new ArrayList<String>();
    private ArrayList<Subject> subjectList=new ArrayList<Subject>();
    static CardListFragment cardFrag;
    private int subjectPosition;
    
   public SubjectListFragment(ArrayList<String> subjectNames,boolean mTwoPane, boolean advancingMode,ArrayList<Subject> subjectList,MenuItem item){
        this.subjectNames=subjectNames;
        this.subjectList=subjectList;
        this.advancingMode=advancingMode;
        this.mTwoPane=mTwoPane; 
    
    }
   public SubjectListFragment(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  if(savedInstanceState!=null){
            //setListAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,savedInstanceState.getStringArrayList("sNames")));
       // }
        return super.onCreateView(inflater, container, savedInstanceState);
        
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public int getSelectedItemPosition() {
        return subjectPosition;
    }
    

    @Override
    public void onListItemClick(ListView l, View v, int subjectPosition, long id) {
        //super.onListItemClick(l, v, position, id);
        this.subjectPosition=subjectPosition;
        ArrayList<String> cards=new ArrayList<String>();

        if(advancingMode==true){
                cardFrag=new CardListFragment(cards,mTwoPane,subjectPosition,subjectList);
            if(mTwoPane){
                getFragmentManager().beginTransaction().replace(R.id.list_container, cardFrag,"cList").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
            }else{
                getFragmentManager().beginTransaction().replace(R.id.top_phone_container, cardFrag,"cList").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
            }
         }
        if(!advancingMode){
            //if(getListView())
            l.setItemChecked(subjectPosition, true);
            RemoveContentMenuHandler handler=new RemoveContentMenuHandler(getActivity(),subjectList,this,subjectPosition,item);
            advancingMode=true;
           
            
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        setListAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_activated_1,subjectNames));
        setRetainInstance(true);
    }
    public void refreshList(ArrayList<String> subjectNames){
        this.subjectNames=subjectNames;
        setListAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_activated_1,subjectNames));
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(savedInstanceState!=null){
            refreshList(savedInstanceState.getStringArrayList("sNames"));
        }
    }
    public void setAdvancingMode(boolean advancingMode){
        this.advancingMode=advancingMode;
    }
     public boolean getAdvancingMode(){
        return advancingMode;
    }
     public CardListFragment getCardFrag(){
         return cardFrag;
     }
     
   // @Override
   // public void onSaveInstanceState(Bundle outState) {
   //     super.onSaveInstanceState(outState);
   //     outState.putStringArrayList("sNames", subjectNames);
 //   }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }
     public void saveDeleteItem(MenuItem item){
         this.item=item;
     }
}
