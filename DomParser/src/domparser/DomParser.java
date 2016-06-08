package domparser;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

/**
 *
 * @author Marko
 */
public class DomParser {

    public   List<Knife> domParser(String xml) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(xml);
        Document document = (Document) builder.build(xmlFile);
        Element rootNode = document.getRootElement();
        List<Knife> knifes = new ArrayList<>();
        List<Element> listKnife = rootNode.getChildren("knife");
        for (Element e : listKnife) {
            Knife knife = new Knife();
            String id = e.getAttributeValue("id");
            knife.setId(id);
            Element type = e.getChild("type");
            String typeValue = type.getText();
            knife.setType(typeValue);
            Element hangry = e.getChild("hangry");
            String hangryValue=hangry.getText();
            knife.setHangry(hangryValue);
            Element origin = e.getChild("origin");
            String originValue = origin.getText();
            knife.setOrigin(originValue);
            Element visual = e.getChild("visual");
            knife.setVisual(new Knife.Visual());
            Element handle = visual.getChild("handle");
            knife.getVisual().setHandle(handle.getText());
            Element value = visual.getChild("value");
            knife.getVisual().setValue(new Boolean(value.getText()));
            Element blade = visual.getChild("blade");
            knife.getVisual().setBlade(new Knife.Visual.Blade());
            Element lenght = blade.getChild("lenght", Namespace.getNamespace("http://ddd.com/blalbab"));
            int lenghtValue = Integer.valueOf(lenght.getText());
            knife.getVisual().getBlade().lenght = lenghtValue;
            Element width = blade.getChild("width", Namespace.getNamespace("http://ddd.com/blalbab"));
            int widthValue = Integer.valueOf(width.getText());
           knife.getVisual().getBlade().width = widthValue;

            Element material = blade.getChild("material", Namespace.getNamespace("http://ddd.com/blalbab"));
            String materialValue = material.getText();
           knife.getVisual().getBlade().material = materialValue;
            Element blood_groove = blade.getChild("blood_groove", Namespace.getNamespace("http://ddd.com/blalbab"));
            boolean blood_grooveValue = new Boolean(blood_groove.getText());
            knife.getVisual().getBlade().blood_groove = blood_grooveValue;
            knifes.add(knife);

        }
return knifes;
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
