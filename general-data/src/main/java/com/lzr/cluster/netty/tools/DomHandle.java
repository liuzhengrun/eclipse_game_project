package com.lzr.cluster.netty.tools;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * DOM方式解析xml
 */
public class DomHandle {

    /**
     * calssName => codeType
     */
    private static Map<String,Long> map = new HashMap<>();

    public static void handle(String commandUrl) {
        //1、创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //2、创建一个DocumentBuilder的对象
        try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //3、通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            /*注意导入Document对象时，要导入org.w3c.dom.Document包下的*/
            Document document = db.parse(commandUrl);//传入文件名可以是相对路径也可以是绝对路径
            //获取所有scheduler节点的集合
            NodeList nodeList = document.getElementsByTagName("scheduler");
            //通过nodelist的getLength()方法可以获取长度
            //遍历每一个节点
            for (int i = 0; i < nodeList.getLength(); i++) {
                //通过 item(i)方法 获取一个node节点，nodelist的索引值从0开始
                Node node = nodeList.item(i);
                //获取node节点的所有属性集合
                NodeList childNodesList = node.getChildNodes();
                for (int j = 0; j < childNodesList.getLength(); j++) {
                    Node item_j = childNodesList.item(j);
                    if (item_j.getNodeType() == Node.ELEMENT_NODE) {// 是element类型的node
                        NodeList childNodes = item_j.getChildNodes();
                        for (int k = 0; k < childNodes.getLength(); k++) {
                            Node item_k = childNodes.item(k);
                            if (item_k.getNodeType() == Node.ELEMENT_NODE) {// 是element类型的node
                                NamedNodeMap attributes = item_k.getAttributes();
                                long codeType = -1;
                                String calssName = null;
                                for (int m = 0; m < attributes.getLength(); m++) {
                                    //通过item(index)方法获取book节点的某一个属性
                                    Node attr = attributes.item(m);
                                    if (attr.getNodeName().equals("code")){
                                        String nodeValue = attr.getNodeValue();
                                        codeType = Integer.parseInt(nodeValue.substring(2, nodeValue.length()), 16);
                                    }else if (attr.getNodeName().equals("class-name")){
                                        calssName = attr.getNodeValue();
                                    }
                                }
                                if (codeType==-1||calssName==null){
                                    throw new Error("文件中有空数据,请检查!!!");
                                }
                                if (map.containsKey(calssName)){
                                    throw new Error("文件中消息类名["+calssName+"]重复!!!");
                                }
                                map.put(calssName,codeType);
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据类名获取codeType
     */
    public static long getCodeType(String className){
        Long codeType = map.get(className);
        if (codeType==null){
            return -1;
        }
        return codeType;
    }

}