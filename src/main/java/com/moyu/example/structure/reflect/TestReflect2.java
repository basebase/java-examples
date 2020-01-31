package com.moyu.example.structure.reflect;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Created by Joker on 19/8/24.
 *
 * 反射的一个案例, 通过配置文件动态创建对象并调用方法
 *
 *   1. 创建一个配置xml文件
 *   2. 获取对应的className并创建对象
 *   3. 获取class配置的method
 *   4. 通过反射调用指定的方法
 *
 */
public class TestReflect2 {

    public static void main(String[] args) throws Exception {
        // 读取配置文件
        InputStream is = TestReflect2.class.getClassLoader().getResourceAsStream("class-config.xml");


        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder =
                documentBuilderFactory.newDocumentBuilder();

        // 解析配置xml配置文件
        Document document = builder.parse(is);

        NodeList nodeList = document.getElementsByTagName("class");

        String className = null;
        String methodName = null;
        Class[] paramTypes = null;
        Object[] ps = null;

        for (int i = 0 ; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                className = element.getAttribute("className");
                System.out.println("className = " + element.getAttribute("className"));

                NodeList childNodeList = element.getElementsByTagName("method");
                for (int j = 0; j < childNodeList.getLength(); j++) {
                    Node childNode = childNodeList.item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        methodName = childElement.getAttribute("methodName");
                        System.out.println("method Name = " + childElement.getAttribute("methodName"));

                        NodeList params = childElement.getElementsByTagName("params");
                        ps = new Object[params.getLength()];
                        paramTypes = new Class[params.getLength()];

                        for (int k = 0; k < params.getLength(); k++) {

                            Node paramNode = params.item(k);
                            if (paramNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element paramTypeElement = (Element) paramNode;

                                String type = paramTypeElement.getElementsByTagName("type").item(0).getTextContent();
                                String value = paramTypeElement.getElementsByTagName("value").item(0).getTextContent();

                                if (type.equals("String")) {
                                    paramTypes[k] = String.class;
                                    ps[k] = value;
                                } else if(type.equals("int")) {
                                    paramTypes[k] = int.class;
                                    ps[k] = Integer.parseInt(value);
                                }


                                System.out.println("method params type = " + type);
                                System.out.println("method params valye = " + value);

                            }


                        }
                    }
                }
            }
        }


        Class cls = Class.forName(className);
        Object obj = cls.newInstance();
        Method method = cls.getDeclaredMethod(methodName, paramTypes);
        method.setAccessible(true);
        method.invoke(obj, ps);

    }
}
