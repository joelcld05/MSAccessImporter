/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasynch;

import java.io.BufferedReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class CoreFunctions extends Crud {
    private Document doc;
    private DocumentBuilder dBuilder;
    private DocumentBuilderFactory dbFactory;
    private File fXmlFile;
    private NodeList nListmessages;
    public boolean cancelProcess;
    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    public CoreFunctions(){
        try {
            fXmlFile = new File("localData.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            nListmessages = doc.getElementsByTagName("mconten");
        } catch (IOException | ParserConfigurationException | SAXException e) {
            //e.printStackTrace();
        }
    }
    
    public String getMessageValue(int id){
        return nListmessages.item(id).getTextContent();
    }

    public boolean RunProcess(javax.swing.JTextArea log, String strSeparator, int eventType, String whereCondition){
        String message = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.txtPath),"ISO-8859-1"));
            String line;
            cancelProcess = false;
            if (eventType == 1){
                message = this.deletion(whereCondition);
                log.append(message); 
            }
            int i=0;
                        
            while ((line = br.readLine()) != null && cancelProcess == false) {

                switch (eventType) {
                    case 1:
                        message = onlyInsert(line,strSeparator);
                        break;
                    case 2:
                        message = deleteAndInsert(line,strSeparator);
                        break;
                    case 3:
                        message = insertAndUpdate(line,strSeparator);
                        break;
                    case 4:
                        message = onlyInsert(line,strSeparator);
                        break;
                }                
                log.append(message);
                //Thread.sleep(5/10);
            }
            
            br.close();
        } catch (IOException x) {
            
        }
        log.append( df.format(new Date()) + " "+ getMessageValue(9) +"\n");
        return true;
    }
        
    
    public boolean runSqlCommand(javax.swing.JTextArea log, String sql){
        String message = "";

        message=sqlCommand(sql);
       
        log.append( df.format(new Date()) + " "+ message +"\n");
        return true;
    }
    
    public String deleteAndInsert(String rawLine, String strSeparator){
        String message;
        String[] values;
        
        String result;
        String resultdelete;
        String stringValues;
        
        stringValues = rawLine+" "+strSeparator;
        values = stringValues.split(strSeparator);
        
        try{
            resultdelete = df.format(new Date()) +" - " +this.deletion(this.tablePk +"="+values[0]);                // Improve here where have to decide which is the pk value
            result = df.format(new Date()) +" - " +this.insertion(values); 
            message = result+"\n"+resultdelete+"\n"; 
        }catch(Exception x){
            message = df.format(new Date()) +" "+ x+" - "+ rawLine +"\n"; 
        }
        return message;
    }
    
    public String onlyInsert(String rawLine, String strSeparator){
        String message = null;
        String[] values = null;       

        String result;
        String stringValues;
        stringValues = rawLine+" "+strSeparator;
        values = stringValues.split(strSeparator);
        try{ 
            result = this.insertion(values); 
            message = df.format(new Date()) + " "+ result +"\n"; 
        }catch(Exception x){
            message = df.format(new Date()) +" "+ x+" - "+ rawLine +"\n"; 
        }
       return message;
    }

    
    public String insertAndUpdate(String rawLine, String strSeparator){
        String message = null;
        String[] values = null;
        String[] valuesResult;
        
        ArrayList<String[]> valuesFromSelect;
        String result;
        String stringValues;
        
        stringValues = rawLine+" "+strSeparator;
        values = stringValues.split(strSeparator);
        try{
            
            valuesFromSelect = this.selection(values[0]);
            
            if (!valuesFromSelect.isEmpty()){
                valuesResult = new String[valuesFromSelect.get(0).length];
                valuesResult = valuesFromSelect.get(0);
                result = this.update(values, values[0]);
                message = df.format(new Date()) + " "+ result +"\n";
            }else{
                result = this.insertion(values); 
                message = df.format(new Date()) + " "+ result +"\n"; 
            }
        }catch(Exception x){
            message =  df.format(new Date()) +" "+ x+" - "+ rawLine +"\n"; 
        }
       return message;
    }

}
