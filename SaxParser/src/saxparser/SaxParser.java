/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser extends DefaultHandler{
	List<Knife> knifes=new ArrayList<>(); 
	int current=0; 
	Knife k;
	public List<Knife> getKnifes(){ 
		return knifes; 
		} 
		@Override 
		public void startDocument(){ 
		System.out.println("start parsing..."); 
		} 
		@Override 
		public void endDocument(){ 
		System.out.println("end parsing..."); 
		} 
		@Override 
		public void startElement(String uri, String localName, String qName, Attributes attr){ 
		switch(qName){ 
		case"knifes":{ 
		current=1; 
		break; 
		} 
		case"knife":{ 
		k=new Knife(); 
		k.setId(attr.getValue(0)); 
		current=2; 
		break; 
		} 
		case"type":{ 
		current=3; 
		break; 
		} 
		case"hangry":{ 
		current=4; 
		break; 
		} 
		case"origin":{ 
		current=5; 
		break; 
		} 
		
		case"visual":{ 
		k.setVisual(new Knife.Visual()); 
		current=6; 
		break; 
		} 
		case"handle":{ 
		current=7; 
		break; 
		} 
		case"value":{ 
		current=8; 
		break; 
		} 
		case"mysp:lenght":{ 
			current=9; 
			break; 
			} 
		case"mysp:width":{ 
			current=10; 
			break; 
			} 
		case"mysp:material":{ 
			current=11; 
			break; 
			} 
		case"mysp:blood_groove":{ 
			current=12; 
			break; 
			} 
		case"blade":{ 
			current=13;
			k.getVisual().setBlade(new Knife.Visual.Blade());
			break; 
			} 
		} 
		} 
		@Override
		public void characters(char[] buf,int start,int leng) {
			String text=new String(buf, start, leng);
			switch (current) {
			case 3:{
				k.setType(text);
				
				break;
			}
			case 4:{
				int i=0;
				if(text.equals("one-hended")){
					i=1;
				}
				else  if(text.equals("two-hended")){
					i=2;
				}
				k.setHangry(i);
				
				break;
			}
			case 5:{
				k.setOrigin(text);
				
				break;
			}
			case 7:{
				k.getVisual().setHandle(text);
				
				break;
			}
			case 8:{
			k.getVisual().setValue(new Boolean(text));	
				break;
			}
			case 9:{
				k.getVisual().getBlade().lenght=Integer.valueOf(text);	
					break;
				}
			case 10:{
				k.getVisual().getBlade().width=Integer.valueOf(text);	
					break;
				}
			case 11:{
				k.getVisual().getBlade().material=text;	
					break;
				}
			case 12:{
				k.getVisual().getBlade().blood_groove=new Boolean(text);	
					break;
				}
		
		}
			current=-1;
		}
		@Override
		public void endElement(String uri,String localName,String qName) {
			switch(qName) {
			case "knife":{
				knifes.add(k);
			}
			}
		}
		 public static String validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory
                    = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return "knifes.xml is not valid against knifes.xsd";
        } catch (SAXException e1) {
            System.out.println("SAX Exception: " + e1.getMessage());
            return "knifes.xml is not valid against knifes.xsd";
        }
        return "knifes.xml is valid against knifes.xsd";
    }
            public void convertToHTML(String stylesheet,String sourceId) throws IOException, TransformerException{
                TransformerFactory tFactory = TransformerFactory.newInstance(); 
File pricesHTML = new File("Knifes.html"); 
FileOutputStream os = new FileOutputStream(pricesHTML); 
Transformer transformer = tFactory.newTransformer(new StreamSource(stylesheet));   
transformer.transform(new StreamSource(sourceId), new StreamResult(os));  
File htmlFile = new File("Knifes.html");
Desktop.getDesktop().browse(htmlFile.toURI());
            }     
                 
}