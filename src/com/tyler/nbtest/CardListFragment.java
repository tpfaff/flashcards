/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.tyler.nbtest.cards.Subject;
import java.util.ArrayList;

import com.tyler.nbtest.cards.CardListAdapter;;
/**
 *
 * @author Tyler
 */
@SuppressLint("ValidFragment")
public class CardListFragment extends ListFragment{
    private ArrayList<String> cards=new ArrayList<String>();
  private boolean mTwoPane;
  private int parentPosition;
  private Subject parentSubject;
  private ArrayList<Subject> masterList=new ArrayList<Subject>();
  private ArrayList<String> words=new ArrayList<String>();
  private ListView lv;
  //MainActivity act;
    public CardListFragment(){}
    public CardListFragment(ArrayList<String> cards,boolean mTwoPane, int parentPosition, ArrayList<Subject> masterList){
        this.masterList=masterList;
        this.cards=cards;
        this.mTwoPane=mTwoPane;
        this.parentPosition=parentPosition;
       //just pass it in...masterList=getActivity()..getMasterSubjectList();
       parentSubject=masterList.get(parentPosition);
       
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        CardListAdapter adapter =new CardListAdapter(getActivity(),android.R.layout.simple_list_item_activated_1,parentSubject.getCards());
    /*   if(savedInstanceState!=null){
           setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,savedInstanceState.getStringArrayList("cNames")));
       }else{
           setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,parentSubject.getCardNames()));
       }*/
     //  getListView().getChildAt(0).setBackground(getResources().getDrawable(R.drawable.notecard_background));
        setListAdapter(adapter);
    }


    
	
	@SuppressWarnings("unused")
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		//ListView lv=this.getListView();
		
		
		//lv.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.notecard_background));
	}
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
		@SuppressWarnings("unused")
		ListView lv=getListView();
        if(mTwoPane){
            CardContentFragment frag=new CardContentFragment();
            getFragmentManager().beginTransaction().add(R.id.content_container, frag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            l.setItemChecked(position, true);
            
           
        }
       
       
       
    }

    @Override
    public void onStart() {
        super.onStart();
        boolean noCards=false;
        Resources res=getResources();
        Drawable background=res.getDrawable(R.drawable.notecard_background);
       
      //  View childView=getListView().getChildAt(0);
      //  childView.setBackground(getResources().getDrawable(R.drawable.notecard_background));
      //  getListView().getChildAt(0).setBackground(background);
       // if(masterList.get(parentPosition).getCardNames()!=null){
        
       // }
      
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            
        }
    }
    
    public int getParentPosition(){
        Toast.makeText(getActivity(), Integer.toString(parentPosition), Toast.LENGTH_LONG).show();
        return parentPosition;
    }
    public void refreshList(ArrayList<String> words){
        this.words=words;
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,words));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("cNames", words);
    }

    
}
