import java.util.*;
import java.io.*;
public class Wordlist{
    private Hashtable<String,Boolean> strA;
    private String filename;
    public Wordlist(String filename){
        this.filename = filename;
        strA = new Hashtable<String,Boolean>();
        try{
            writeAL();
        }catch(Exception e){
            System.out.println("l");
        }    
    }
    public void writeAL() throws Exception{
        File newFile = new File(filename);
        FileReader reading = new FileReader(newFile);
        BufferedReader br = new BufferedReader(reading);
        String str;
        while ((str = br.readLine()) != null) {
            strA.put(str, true);
        }
    }  
    public boolean checkWord(String str){
        if(strA.get(str) != null)
            return true;
        return false;
    }    
}
