import java.io.*;
import java.util.ArrayList;

public class Backwords{
    public Backwords() {
    }
    
    public static void main(String[] args) throws Exception{
        File newFile = new File("mytext.txt");
        FileReader reading = new FileReader(newFile);
        BufferedReader br = new BufferedReader(reading);
        
        
        
        File copyOfFile =  new File("copyOfmytext.txt");
        FileWriter writing = new FileWriter(copyOfFile);
        BufferedWriter bw = new BufferedWriter(writing);
        
        String str;
        ArrayList<String> strL = new ArrayList<String>();
        
        while ((str = br.readLine()) != null) {
            str = str.trim();
            int i = 0;
            while ((i = str.indexOf(" "))!= -1){
                strL.add(str.substring(0,i+1));
                str = str.substring(i+1);
            }    
            strL.add(str + " ");
            strL.add("newLine");
        }
        for(int i = strL.size()-2; i >= 0; i--){
            if(strL.get(i).equals("newLine"))
                bw.newLine();
            else
               bw.write(strL.get(i)); 
        }    
        bw.close();
            
        
       
    }    
    
}  