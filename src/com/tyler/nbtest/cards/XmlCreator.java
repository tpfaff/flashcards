/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest.cards;

import android.content.Context;
import android.util.Xml;
import android.widget.Toast;
import java.io.StringWriter;
import java.util.ArrayList;
import org.xmlpull.v1.XmlSerializer;

/**
 *
 * @author Tyler
 */
public class XmlCreator {
    XmlSerializer serializer=Xml.newSerializer();
    StringWriter writer=new StringWriter();
    Context parent;
    public XmlCreator(Context parent){
        this.parent=parent;
    }
    public String subjectListToXml(ArrayList<Subject> masterList){
        try{
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "subjects");
            for(Subject subject:masterList){
                serializer.startTag("","subject");
                serializer.startTag("", "name");
                serializer.text(subject.getSubjectName());
                serializer.endTag("", "name");
                for(Cards card:subject.getCards()){
                    serializer.startTag("","card");
                     serializer.startTag("","word");
                     serializer.text(card.getCardFront());
                     serializer.endTag("","word");
                    serializer.endTag("", "card");
                }
                serializer.endTag("", "subject");
            }
                serializer.endTag("","subjects");
                serializer.endDocument();
                writer.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        Toast.makeText(parent, writer.toString(), Toast.LENGTH_LONG).show();
                                   return writer.toString();
    }
}
