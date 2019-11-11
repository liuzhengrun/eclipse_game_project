package com.lzr.cluster.utils.prototoxml;

import java.io.*;
import java.util.Vector;
import java.util.prefs.Preferences;

public class FileConfig{

    public FileConfig(){
    }

    public static void readDestFile(String fileName){
        File file = new File((new StringBuilder(String.valueOf(USER_DIR))).append(File.separator).append(fileName).toString());
        if(file.exists()){
            BufferedReader br = null;
            try{
                br = new BufferedReader(new FileReader(file));
                String line;
                if((line = br.readLine()) != null)
                    destPath = line;
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void readFile(String fileName){
        File file = new File((new StringBuilder(String.valueOf(USER_DIR))).append(File.separator).append(fileName).toString());
        if(file.exists()){
            excludesVector.clear();
            BufferedReader br = null;
            try{
                br = new BufferedReader(new FileReader(file));
                String line;
                while((line = br.readLine()) != null) 
                    excludesVector.add(line.trim());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static String USER_DIR = System.getProperty("user.dir");
    public static Preferences prefs;
    public static String ProtocolCode_Last_PathValue = "";
    public static String destPath = "command.xml";
    public static Vector excludesVector = new Vector();

}
