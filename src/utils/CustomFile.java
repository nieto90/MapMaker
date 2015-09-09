/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nieto
 */
public class CustomFile {
    
    public static List<String> getLinesFromFile(String file){
        List<String> ls = new ArrayList<>();
        BufferedReader br = null;
        try{
            String sCurrentLine;

            br = new BufferedReader(new FileReader(file));

            while ((sCurrentLine = br.readLine()) != null) {
                ls.add(sCurrentLine);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return ls;
    }
    
    
    public static void writeObject(Object data, String file){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(data);
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static Object readObject(String file) {
        Object obj = null;
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            obj = in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            return obj;
        }
    }
        
}
