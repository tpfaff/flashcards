package com.tyler.nbtest;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.tyler.nbtest.cards.AddCardMenuHandler;
import com.tyler.nbtest.cards.AddSubjectMenuHandler;
import com.tyler.nbtest.cards.RemoveContentMenuHandler;
import com.tyler.nbtest.cards.Subject;
import com.tyler.nbtest.cards.XmlCreator;
import com.tyler.nbtest.cards.XmlReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParserException;

public class MainActivity extends Activity
{
     boolean mTwoPane;
     MenuItem deleteItem;
     final String LOCALSTORAGEFILE="note_cards_wajum.xml";
     private static ArrayList<Subject> subjectMasterList=new ArrayList<Subject>();
     SubjectListFragment top;
     boolean dataRetained=false;
     
     boolean deleteActivated=false;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
           XmlReader reader=new XmlReader(LOCALSTORAGEFILE,this);
            if(reader.fileExists()){
            try {     
                reader.parse();     
                subjectMasterList=reader.getData();
                dataRetained=true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                Toast.makeText(this,ex.toString(), Toast.LENGTH_LONG).show();
            } catch (XmlPullParserException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                Toast.makeText(this,ex.toString(), Toast.LENGTH_LONG).show();
            } catch (IOException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                Toast.makeText(this,ex.toString(), Toast.LENGTH_LONG).show();
            }
            }
                
              //  Toast.makeText(this, Boolean.toString(dataRetained), Toast.LENGTH_SHORT).show();
        
        
        if(savedInstanceState==null){
       setContentView(R.layout.main);

        if(findViewById(R.id.top_tablet_container)!=null){
           mTwoPane=true; 
        }else{
            mTwoPane=false;
        }
      
        if(mTwoPane){  
            top=new SubjectListFragment(getSubjectNames(),mTwoPane,true,subjectMasterList,deleteItem);//3rd param is advancing mode
            getFragmentManager().beginTransaction().add(R.id.list_container, top).commit();
        }else{       
            top=new SubjectListFragment(getSubjectNames(),mTwoPane,true,subjectMasterList,deleteItem); //3rd param is advancing mode
            getFragmentManager().beginTransaction().add(R.id.top_phone_container, top).commit();
            
        }
        }
        else{ 
            setContentView(R.layout.main);
        }
        
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        super.onOptionsItemSelected(item);
        
        int fragmentVisible=getFragmentManager().getBackStackEntryCount();
        int cardList=1;
        int subjectList=0;
        boolean isSubjectList=false;
        boolean isCardList=false;
        
        
        if(fragmentVisible==cardList){        
            isCardList=true;//card list is visible   
        }else{
        if(fragmentVisible==subjectList){
            isSubjectList=true;//subject list is visible
        }
        }
 
 //=-------------------------------------------------------------------       
      switch(item.getItemId()){
          
          case R.id.add_content:
          if(isSubjectList){    
             AddSubjectMenuHandler addNewSubject=new AddSubjectMenuHandler(this,getSubjectNames(),top); 
             subjectMasterList.add(addNewSubject.getNewSubject()); 
          }else{
              AddCardMenuHandler newCard=new AddCardMenuHandler(this,subjectMasterList,top,(CardListFragment)getFragmentManager().findFragmentByTag("cList"));
              subjectMasterList.set(newCard.getIndexToReplace(), newCard.getSubjectModified());
          }    
           break;
              
  //------------------------------------------------------------------            
          case R.id.remove_content:      
             
             top.saveDeleteItem(item);
                 if(deleteActivated){
                     item.setIcon(R.drawable.content_remove);
                     deleteActivated=false;
                 }else{
                     item.setIcon(R.drawable.content_remove_activated);
                     deleteActivated=true;
                     if(isSubjectList){
                         top.setAdvancingMode(false);
                     }else{
                     if(isCardList){
                         top.setAdvancingMode(true);
                       // RemoveContentMenuHandler remove=new RemoveContentMenuHandler(this,subjectMasterList,); 
                        }
                     }
             }
             break;
//=--------------------------------------------------------------------------------              
          case R.id.save_content:
             try {
                saveData();
             } catch (IOException ex) {
                 Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
             }
 //---------------------------------------------------------------------------------             
          case R.id.erase_file:
              try{
                  boolean deleted=deleteData();
                  Toast.makeText(this, Boolean.toString(deleted), Toast.LENGTH_SHORT).show();
              }catch(IOException ex){
                  ex.printStackTrace();
              }
              
      }
              
              return true;
      }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater=getMenuInflater();
       inflater.inflate(R.menu.action_bar, menu);
      // savedMenu=menu;
       return true;
    }
    private ArrayList<String> getSubjectNames(){
        ArrayList<String> subjectNames=new ArrayList<String>();
        for(Subject Subject:subjectMasterList){
            subjectNames.add(Subject.getSubjectName());
        }
        return subjectNames;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("names",getSubjectNames());   
    }
    
    public ArrayList<Subject> getMasterSubjectList(){
        return subjectMasterList;
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            saveData();
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    
    @Override
    public Object onRetainNonConfigurationInstance() { //deprecated method, replace with parcable 
        return getMasterSubjectList();
    }
   
    private void saveData() throws IOException{
         FileOutputStream fos=openFileOutput(LOCALSTORAGEFILE,Context.MODE_PRIVATE);
             XmlCreator create=new XmlCreator(this);
             fos.write(create.subjectListToXml(subjectMasterList).getBytes());
             fos.close();
    }
    private boolean deleteData() throws IOException{
       return new File(LOCALSTORAGEFILE).delete();
       
    }
    }
    
