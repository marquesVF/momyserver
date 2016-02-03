package com.momy.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.momy.bean.ServerBean;


public class ConfigurationsLoader {
	public static final String CONFIG = "cfg/config.xml";
	
	private ServerBean server_bean;
	
	public ConfigurationsLoader(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			
			File configuration_file = new File(CONFIG);
			Document doc = builder.parse(configuration_file);
			doc.getDocumentElement().normalize();
			
			Node config = doc.getElementsByTagName("server").item(0);
			if(config.getNodeType() == Node.ELEMENT_NODE){
				Element el = (Element) config;
				this.server_bean = new ServerBean();
				this.server_bean.setServerHost(el.getElementsByTagName("host").item(0).getTextContent());
				this.server_bean.setServerPort(el.getElementsByTagName("port").item(0).getTextContent());
				this.server_bean.setServerRoot(el.getElementsByTagName("root").item(0).getTextContent());
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			
			e.printStackTrace();
		}		
	}
	
	public ServerBean getServerConfiguration(){
		return this.server_bean;
	}
}
