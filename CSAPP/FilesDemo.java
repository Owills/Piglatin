import java.io.*;


public class FilesDemo{
    public FilesDemo() {
    }
    
    public static void main(String[] args) throws Exception{
        File newFile = new File("mytext.txt");
        FileReader reading = new FileReader(newFile);
        BufferedReader br = new BufferedReader(reading);
        
        
        
        File copyOfFile =  new File("copyOfmytext.txt");
        FileWriter writing = new FileWriter(copyOfFile);
        BufferedWriter bw = new BufferedWriter(writing);
        
        String str; 
        while ((str = br.readLine()) != null) {
            bw.write(str);
            bw.newLine();
        }
        bw.close();
            
        
       
    }    
    
}  