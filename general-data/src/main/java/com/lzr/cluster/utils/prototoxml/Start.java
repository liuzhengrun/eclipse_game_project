package com.lzr.cluster.utils.prototoxml;

import java.util.Map;

/**
 * 暂时不使用
 * @author lzr
 * 2019年11月11日
 */
public class Start{

    public Start(){
    }

    public static void main(String args[]){
        FileConfig.readFile(exFileName);
        FileConfig.readDestFile(destFile);
        Map map = ProtoPackToXml.doProto(ProtoPackToXml.readProto());
        WriteXml.createApplicationConfigXML(map, FileConfig.destPath);
    }

    private static String exFileName = "exinclude.txt";
    private static String destFile = "dest.txt";

}
