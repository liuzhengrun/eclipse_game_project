package com.lzr.cluster.utils.prototoxml;

import java.io.*;
import java.util.*;

public class ProtoPackToXml{

    public ProtoPackToXml(){
    }

    public static List readProto(){
        fileLists.clear();
        File curFolder = new File(USER_DIR);
        if(curFolder.isDirectory()){
            File files[] = curFolder.listFiles();
            for(int i = 0; i < files.length; i++){
                boolean have = false;
                for(Iterator iterator = FileConfig.excludesVector.iterator(); iterator.hasNext();){
                    String fileName = (String)iterator.next();
                    if(fileName.equals(files[i].getName())){
                        have = true;
                        break;
                    }
                }
                if(!have && files[i].getName().endsWith(".proto"))
                    fileLists.add(files[i]);
            }

        }
        return fileLists;
    }

    public static Map doProto(List fileLists){
        map.clear();
        for(Iterator iterator = fileLists.iterator(); iterator.hasNext();){
            File file = (File)iterator.next();
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = null;
                String key = "";
                int j = 0;
                int r = 0;
                while((line = br.readLine()) != null) {
                    try{
                        int i = -1;
                        line = line.replace(" ", "");
                        if((i = line.lastIndexOf("=0x")) > -1){
                            String sub = line.substring(i + 1, line.length());
                            String sStr[] = sub.split(";");
                            key = sStr[0];
                            r = j;
                        } else if(line.startsWith("message") && r + 1 == j){
                            i = line.indexOf("message");
                            String sub = line.substring(i + 7, line.length()).trim();
                            int k = sub.lastIndexOf("{");
                            String ssub = sub.substring(0, k);
                            String v = (String)map.get(key);
                            if(v != null){
                                System.err.println((new StringBuilder(String.valueOf(file.getName()))).append(":有两个同样的协议::").append(v).append("====").append(ssub).toString());
                            } else{
                                map.put(key, ssub);
                                key = "";
                                r = 0;
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.err.print((new StringBuilder(String.valueOf(line))).append(":::文件名::").append(file.getName()).toString());
                    }
                    j++;
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return map;
    }

    public static Map map = new HashMap();
    public static List fileLists = new ArrayList();
    public static String USER_DIR = System.getProperty("user.dir");

}
