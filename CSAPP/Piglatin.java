import java.io.*;
import java.util.ArrayList;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.dnd.*;  
class Piglatin extends JFrame implements ItemListener, ActionListener, KeyListener{ 
    private static Choice c; 
    private static JFrame f; 
    private static Label l; 
    private static Label output;
    private static Label output1;
    private static JTextField t; 
    private static JButton b; 
    private static String file;
    private static Wordlist wl;
    Piglatin() { 
    } 
    public static void main(String args[]) { 
        //set up interface
        f = new JFrame("English To Piglatin Translator"); 
        file = "";
        //main pannel
        Piglatin pl = new Piglatin(); 
        JPanel p = new JPanel(); 
        // create wordlist object
        wl = new Wordlist("wordlist.txt");
        //text box
        t = new JTextField(16);
        t.addKeyListener(pl);
        //set character limit
        t.setDocument(new CharLimit(50));
        
        //layout manager
        p.setLayout(new GridBagLayout()); 
        GridBagConstraints gc = new GridBagConstraints(); 
        
        //submit button
        b = new JButton("Submit"); 
        b.addActionListener(pl);
        
        //top choice box
        c = new Choice(); 
        c.add("Text Translator"); 
        c.add("English to Piglatin File Translator"); 
        c.add("Piglatin to English File Translator"); 
        c.addItemListener(pl); 
        //instruction label
        l = new Label(); 
        l.setText(" Manually Input Text to be Translated Into PigLatin:");
        //output display
        output = new Label(" Output: ");
        output1 = new Label("");
        
        //horizontal alignment
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.VERTICAL;
        
        gc.gridwidth = 1;
        gc.gridheight = 1;
        
        //set grid positions
        gc.gridx = 0; 
        gc.gridy = 0; 
        gc.ipadx = 0; 
        gc.ipady = 1; 
        p.add(c,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 1; 
        p.add(l,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 2; 
        p.add(t,gc);
        
        gc.gridx = 1; 
        gc.gridy = 2; 
        p.add(b,gc);
        
        gc.gridx = 0; 
        gc.gridy = 3; 
        p.add(output,gc);
        
        gc.gridx = 0; 
        gc.gridy = 4; 
        p.add(output1,gc);
        
        f.add(p); 

        f.show(); 
        f.setSize(1000, 500); 
        f.setResizable(false);
    } 
    // change selected item
    public void itemStateChanged(ItemEvent e) { 
        t.setText("");
        if(c.getSelectedItem().equals("Text Translator")){
            l.setText(" Manually Input Text to be Translated Into PigLatin:");
            output1.setText("");
        }else {
            l.setText(" Input File Name Then Enter Name of New File:");
            output1.setText("");
        }
    } 
    //submit button on click
    public void actionPerformed(ActionEvent e) { 
        String s = e.getActionCommand(); 
        if(s.equals("Submit")) { 
            if(l.getText().equals(" Input File Name Then Enter Name of New File:")){
                l.setText(" Input New File Name:");
                file = t.getText();
            }else{   
                
                submit(file, t.getText()); 
            }    
        } 
    } 
    //submit button with enter key
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            if(l.getText().equals(" Input File Name Then Enter Name of New File:")){
                l.setText(" Input New File Name:");
                file = t.getText();
            }else{    
                submit(file, t.getText()); 
            }    
        }  
    }
    //statisfying abstract class, but do nothing
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    //submit logic
    public static void submit(String input, String input2){
        //option 1
        if(c.getSelectedItem().equals("Text Translator")){
                output1.setText(" " + englishToPiglatin(input2)); 
                output1.setSize(1000,100);
        //option 2        
        }else if (c.getSelectedItem().equals("English to Piglatin File Translator")){
             try{
                    //read and write the file
                    File newFile = new File(input);
                    FileReader reading = new FileReader(newFile);
                    BufferedReader br = new BufferedReader(reading);
                    
                    File copyOfFile =  new File(input2);
                    FileWriter writing = new FileWriter(copyOfFile);
                    BufferedWriter bw = new BufferedWriter(writing);
                    
                    String str; 
                    while ((str = br.readLine()) != null) {
                        bw.write(englishToPiglatin(str));
                        bw.newLine();
                    }
                    bw.close();
                    output1.setText(" File Translated and Copied to New File Named '" + input2 + "' Sucessfully"); 
                }  catch(FileNotFoundException e){
                    output1.setText(" Error: Make Sure the Name Written Matches the Name of the File and that the File is in the Correct Folder"); 
                }   catch(Exception e){
                    output1.setText(" Error: Unknown. Make Sure the File is Saved as a .txt"); 

                }   finally{
                    output1.setSize(1000,100);
                    l.setText(" Input File Name Then Enter Name of New File:");
                }        
        //option 3        
        } else if (c.getSelectedItem().equals("Piglatin to English File Translator")){
             try{
                    //read and write the file
                    File newFile = new File(input);
                    FileReader reading = new FileReader(newFile);
                    BufferedReader br = new BufferedReader(reading);
                    
                    File copyOfFile =  new File(input2);
                    FileWriter writing = new FileWriter(copyOfFile);
                    BufferedWriter bw = new BufferedWriter(writing);
                    
                    String str; 
                    while ((str = br.readLine()) != null) {
                        bw.write(piglatinToEnglish(str));
                        bw.newLine();
                    }
                    bw.close();
                    output1.setText(" File Translated and Copied to New File Named '" + input2  + "' Sucessfully"); 
                }  catch(FileNotFoundException e){
                    output1.setText(" Error: Make Sure the Name Written Matches the Name of the File and that the File is in the Correct Folder"); 
                    
                }   catch(Exception e){
                    output1.setText(" Error: Unknown. Make Sure the File is Saved as a .txt"); 
                    
                }  finally{
                    output1.setSize(1000,100);
                    l.setText(" Input File Name Then Enter Name of New File:");
                }    
        }
    }    
    //piglatin string logic
    public static String englishToPiglatin(String s){
        String[] strA = s.split(" "); 
        String str = "";
        for(int i = 0; i< strA.length; i ++){
            if(!isMostlyLetters(strA[i])){
                str += strA[i] + " ";
            }else if(isConsonant(strA[i].substring(0,1))){
                String SC = "";
                while(endsInSpecialCharacter(strA[i])){
                    SC = strA[i].substring(strA[i].length()-1) + SC;
                    strA[i] = strA[i].substring(0,strA[i].length()-1);
                }    
                if(Character.isUpperCase(strA[i].charAt(0))){
                    str += Character.toUpperCase(strA[i].charAt(1)) + strA[i].substring(2)
                     + Character.toLowerCase(strA[i].charAt(0)) + "ay" +SC + " " ;
                }else{
                     str += strA[i].substring(1) + strA[i].charAt(0) + "ay" +SC + " ";
                }     
            } else  {
                String SC = "";
                while(endsInSpecialCharacter(strA[i])){
                    SC = strA[i].substring(strA[i].length()-1) + SC;
                    strA[i] = strA[i].substring(0,strA[i].length()-1);
                }  
                str += strA[i] + "way" +SC + " ";
            }    
        }    
        return str;
    }  
    public static String piglatinToEnglish(String str){
        str = str.trim();
        String[] strA = str.split(" "); 
        str = "";
        for(int i = 0; i< strA.length; i ++){
            if(!isMostlyLetters(strA[i])){
                str += strA[i] + " ";
            }else{
                String SC = "";
                while(endsInSpecialCharacter(strA[i])){
                    SC = strA[i].substring(strA[i].length()-1) + SC;
                    strA[i] = strA[i].substring(0,strA[i].length()-1);
                } 
                if(isPiglatin(strA[i])){
                    String temp = "";
                    String temp2 = "";
                    if(strA[i].substring(strA[i].length()-3,strA[i].length()-2).equalsIgnoreCase("w") &&
                        !isConsonant(strA[i].substring(0,1))){
                        temp += (strA[i].substring(0,strA[i].length()-3));
                        
                    }    
                        if(Character.isUpperCase(strA[i].charAt(0))){
                             temp2 += Character.toUpperCase(strA[i].charAt(strA[i].length()-3)) + "" 
                                + Character.toLowerCase(strA[i].charAt(0)) +
                                (strA[i].substring(1,strA[i].length()-3));
                        }else{    
                            temp2 +=(strA[i].substring(strA[i].length()-3,strA[i].length()-2) +
                              (strA[i].substring(0,strA[i].length()-3)));
                        }     
                        if(wl.checkWord(temp2) && !wl.checkWord(temp) || temp.equals("")){
                            str += temp2 + SC + " ";
                        } else{
                            str += temp + "/" + temp2 + SC + " ";
                        }    
                }else{
                    str += strA[i] + SC + " ";
                }    
            }     
        }    
        return str;
    }
    public static boolean isPiglatin(String str){
        if(str.lastIndexOf("ay") == str.length()-2)
            return true;
        return false;    
    }    
    public static boolean isConsonant(String str){
        if(str.equalsIgnoreCase("a") || str.equalsIgnoreCase("e") ||
            str.equalsIgnoreCase("i") || str.equalsIgnoreCase("o") ||
            str.equalsIgnoreCase("u")){
             return false;
        }        
        return true;
    }
    public static boolean endsInSpecialCharacter(String str){
        Character c = str.charAt(str.length()-1);
        if (Character.isLetter(c)){
            return false;
        }
        return true;
    }    
    //if the majority of characters are letters
    public static boolean isMostlyLetters(String str){
        double letter = 0;
        for(int i = 0; i <str.length(); i++){
            Character c = str.charAt(i);
            if (Character.isLetter(c)){
                letter ++;
            }
        }  
        if(str.length() - letter <= letter){
            return true;
        }    
        return false;
    }    
    //set character limit for text
    public static class CharLimit extends PlainDocument {
      private int limit;
      CharLimit(int limit) {
       super();
       this.limit = limit;
       }
      public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;
    
        if ((getLength() + str.length()) <= limit) {
          super.insertString(offset, str, attr);
        }
      }
    }
} 
