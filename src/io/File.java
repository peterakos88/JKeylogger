package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.MyDate;
import static debug.DebugSettings.debug;
import static debug.DebugSettings.debugl;

public class File {
    
    private String Filename;
    
    public File(String name){
        Filename = name;
    }
    public File(){
        Filename = null;
    }
    
    public void ReWrite(String content){
        try{
            // Create file 
            FileWriter fstream = new FileWriter(Filename);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(content);
            //Close the output stream
            out.close();
            }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch
    }//Write
    
    public boolean Exists(){
    	if (new java.io.File(Filename).exists())
    		return true;
    	else
    		return false;
    }//Exists
    
    public void WriteLine(String content){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter((Filename),true));
            bw.write(content);
            bw.newLine();
            bw.flush();
            
            if (bw!=null)
                bw.close();
        }//try
        catch (Exception e) {
            e.printStackTrace();
        }//catch
    }//WriteLine
    
    public void Write(String content){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter((Filename),true));
            bw.write(content);
            bw.flush();
            
            if (bw!=null)
                bw.close();
        }//try
        catch (Exception e) {
            e.printStackTrace();
        }//catch
    }//Append    
    
    public String Read(){
        java.io.File f = new java.io.File(Filename);
        String Answer=null;
        
        if (Filename == null){
            debug("The File has no filename");
            return null;
        }//if filename =null
        else if (f.exists()&&f.isFile()){
            try{            
                Answer="";
                FileInputStream fstream = new FileInputStream(Filename);
            
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
            
                while ((strLine = br.readLine()) != null)   {
                    debug(strLine);
                    Answer+=strLine;
                    Answer+="\n";
                }//while
                in.close();}
            catch (Exception e){
                System.err.println("Error: " + e.getMessage());
            }//catch
        }//else
        else
            debug(Filename+" does not exist or it isn't a File.");
        return Answer;
    }//Read
    
    public void Delete(){
        java.io.File temp = new java.io.File(Filename);
        if (temp.delete())
            debug(temp.getAbsoluteFile()+"/"+Filename+" successfuly Deleted.");
        else{
            debugl(temp.getAbsoluteFile()+"/"+Filename+" wasn't Deleted ");
            if (!temp.exists())
                debug("because it doesn't exist.");
            else if (temp.isDirectory())
                debug("because it is a directory.");
            else
                debug(" for Filesystem reasons.");
        }//else
    }//Delete
    
    
    
    //----------------------------------------------------------------------------------------
    
    //    A subroutine to find the correct filename
    
    //Logs will be names after the date or randomly.
    
    public static String FindName(boolean Random){
    	String Answer="";
    	if (Random){
    		File f;
    		int i=0;
    		while(true){
    			f = new File("log"+i);
    			if (!f.Exists()){
    				Answer = ("log"+i);
    				break;
    			}//if
    			i++;
    		}//true
    		return Answer;
    	}//if Random name is selected
    	else{	
    		MyDate d = new MyDate();
    		Answer += d.GetDay();
    		Answer += d.GetMonth();
    		Answer += d.GetYear();
    		debug(Answer);
    	}//give a date name
    	
    	return Answer;
    }//FindName
    public static String FindDateImageName(){
    	Date d = new Date();
		Format formatter;
		formatter = new SimpleDateFormat("dd_MM_yyyy_HH-mm-ss");    	
    	return formatter.format(d);
    }//FindName    
    
    
    
    public void SetFilename(String f){Filename = f;}
    public String GetFilename(){return Filename;}
    
}//File