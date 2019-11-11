package com.lzr.cluster.utils.prototoxml;

import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXml{

    public WriteXml(){}

    public static void createApplicationConfigXML(Map commandMap, String destPath){
    	FileOutputStream fileOutputStream = null;
        try{
        	// 解析器工厂类
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        	// 解析器
        	DocumentBuilder builder = dbf.newDocumentBuilder();
        	// 操作的Document对象
        	Document document = builder.newDocument();
        	// 设置XML的版本
        	document.setXmlVersion("1.0");
        	// 创建根节点
            Element root = document.createElement("scheduler");
            // 将根节点添加到Document对象中
            document.appendChild(root);
            root.setNodeValue("这是一个注释");
            // 设置一个proxys元素到
            Element proxys = document.createElement("proxys");
            Element proxy;
            for(Iterator iterator = commandMap.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entity = (java.util.Map.Entry)iterator.next();
                proxy = document.createElement("proxy");
                proxy.setAttribute("code", (String)entity.getKey());
                proxy.setAttribute("class-name", (String)entity.getValue());
                proxy.setAttribute("entry-bean", "worldServerControl");
                proxy.setAttribute("rights", "all");
                proxys.appendChild(proxy);
            }
            root.appendChild(proxys);
            // 开始把ducoment映射到文件
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // 设置输出结果
            DOMSource domSource = new DOMSource(document);
            // 生成xml文件
            File file = new File(destPath);
            if(!file.exists()) {
            	file.createNewFile();
            }
            // 文件输出流
            fileOutputStream = new FileOutputStream(file);
            // 设置输出源
            StreamResult xmlResult = new StreamResult(fileOutputStream);
            // 输出xml文件
            transformer.transform(domSource, xmlResult);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
        	if(fileOutputStream!=null) {
        		try {
					fileOutputStream.close();
				} catch (IOException e) {
				}
        	}
        }
    }
}
