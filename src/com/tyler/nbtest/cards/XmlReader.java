/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest.cards;

import android.content.Context;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 *
 * @author Tyler
 */
public class XmlReader {
    FileInputStream fis;
    Context parent;
    final String fileName;
    ArrayList<Subject> rebuiltData=new ArrayList<Subject>();
    public XmlReader(final String fileName, Context parent){
            this.fileName=fileName;
            this.parent=parent;
           
           
           //DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
           //DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
           //Document doc=dBuilder.parse(fis);
           //doc.getDocumentElement().normalize();
           
        
       
    }
    public boolean fileExists(){
       File file = parent.getFileStreamPath(fileName);
       if(file.exists()){
           return true;
       }
       else{
           return false;
       }    
}
    public void parse() throws FileNotFoundException, XmlPullParserException, IOException{
        int subjectIndex=0;
           InputStream fis =parent.openFileInput(fileName);
           XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
           factory.setNamespaceAware(true);
           XmlPullParser xpp=factory.newPullParser();
           xpp.setInput(fis,null);
           int eventType=xpp.getEventType();
           while(eventType!=XmlPullParser.END_DOCUMENT){
             if(eventType == XmlPullParser.START_TAG) {
             String tagType= xpp.getName();
              if(tagType.equals("subject")){
                  Subject sub=new Subject();
                  
                  xpp.nextTag();//go to name start tag                 
                  sub.setSubjectName(xpp.nextText());     
                  rebuiltData.add(sub);
                  //xpp.next();
                 // xpp.nextTag();//advance to end of name tag
              
              }
             if(tagType.equals("card")){
                 //Toast.makeText(parent,"it's a card",Toast.LENGTH_SHORT).show();
                  Cards card=new Cards();
                  xpp.nextTag();//go to word tag
                  String word=xpp.nextText();//gets the word
                  card.setCardFront(word);
                  
                  Subject s;
                  s=rebuiltData.get(rebuiltData.size()-1);
                  s.addCard(card);
                  rebuiltData.set(rebuiltData.size()-1, s);
                  //xpp.nextTag();
              }
               
           }
             eventType=xpp.next();
    }
    }
    
public ArrayList<Subject> getData(){
    return rebuiltData;
}
}