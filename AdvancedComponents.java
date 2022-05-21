/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phase3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.*;

/**
 *
 * @author Satender
 */

public class AdvancedComponents extends BasicGameComponents implements Displayable {
    
    private String phrase;
    
    public AdvancedComponents()
    {
        super();
        phrase = "";
    }
    
    public AdvancedComponents(String p)
    {
        
        phrase = p;
    }
    
//    setter
    public void setPhrase(String p)
    {
        phrase = p;
    }
    
//    getter
    public String getPhrase()
    {
        return phrase;
    }
    
//    method which checks the length of the phrase
    public int getLength()
    {
        String str = getPhrase();
        
        return str.length();
    }
    
    public String generateStrongPwd(String input)
    {
        String afterShuffle="";
//        String firstAddition = "";
        String finalPwd ="";
        String initialStr = input;
        
        
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = upperCase.toLowerCase();
        String digits = "1234567890";
        String special = "!@#$&";
        String strongChars = upperCase + lowerCase + digits;
        String digiSpecial = digits + special;
        
  
        // Creating array of string length
        List <String> pwd = Arrays.asList(initialStr.split(""));
        
//      -------------------------------------------------
        StringBuilder firstAddition = new StringBuilder();
        int i = 0;
        Random rand = new Random();
        while (i < 3) {
            firstAddition.append(strongChars.charAt(rand.nextInt(strongChars.length())));
            i++;
        }
        
//      -------------------------------------------------

        StringBuilder secondAddition = new StringBuilder();
        int k = 0;
        Random randon = new Random();
        while (k < 3) {
            secondAddition.append(digiSpecial.charAt(randon.nextInt(digiSpecial.length())));
            k++;
        }
        
//        -------------------------------------------------
        
//        shuffling the characters
        Collections.shuffle(pwd);
        
//        adding to aftershuffle string
        for(String letter : pwd)
        {
            afterShuffle += letter;
        }
       
        finalPwd = firstAddition + afterShuffle + secondAddition ;
        
        return finalPwd;
    }
    
    public void writeToRAM(String str)
    {
        try {
            RandomAccessFile raf = new RandomAccessFile("write.txt", "rw");
            
            long fileSize = raf.length();
            
            raf.seek(fileSize);
            raf.writeUTF(str);
            
            raf.close();
            
            
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
        
        
    }
    
    
    @Override
    public String displayLengthMessage(int a)
    {
        int length = getLength();
        
        String msg;
        if(length < 15)
        {
            msg = "Enter minimum of 15 characters, lets try again!";
        }
        else
        {
            msg = "GOOD JOB! Now click on 'Generate a Strong Password' button";
        }
        
        return msg;
    }
    
}