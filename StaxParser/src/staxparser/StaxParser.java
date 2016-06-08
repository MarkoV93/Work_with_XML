package staxparser;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.XMLConstants;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author Marko
 */
public class StaxParser {

    public List<Knife> parseee(String File) {
        List<Knife> knifes = new ArrayList<>();
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream(File);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            Knife knife = null;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startelement = event.asStartElement();
                    if (startelement.getName().getLocalPart() == "knife") {
                        knife = new Knife();
                        Iterator<Attribute> attributes = startelement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals("id")) {
                                knife.setId(attribute.getValue());
                                // System.out.println(attribute.getValue());
                            }
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals("type")) {
                            event = eventReader.nextEvent();
                            knife.setType(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals("hangry")) {
                        event = eventReader.nextEvent();
                        knife.setHangry(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("origin")) {
                        event = eventReader.nextEvent();
                        knife.setOrigin(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("visual")) {
                        event = eventReader.nextEvent();
                        knife.setVisual(new Knife.Visual());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("handle")) {
                        event = eventReader.nextEvent();
                        knife.getVisual().setHandle(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("value")) {
                        event = eventReader.nextEvent();
                        knife.getVisual().setValue(new Boolean(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("blade")) {
                        event = eventReader.nextEvent();
                        knife.getVisual().setBlade(new Knife.Visual.Blade());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("lenght")) {
                        event = eventReader.nextEvent();
                        knife.getVisual().getBlade().lenght = Integer.valueOf(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("width")) {
                        event = eventReader.nextEvent();
                        knife.getVisual().getBlade().width = Integer.valueOf(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("material")) {
                        event = eventReader.nextEvent();
                        knife.getVisual().getBlade().material = event.asCharacters().getData();
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("blood_groove")) {
                        event = eventReader.nextEvent();
                        knife.getVisual().getBlade().blood_groove = new Boolean(event.asCharacters().getData());
                        continue;
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart() == ("knife")) {
                        knifes.add(knife);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
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

    public void convertToHTML(String stylesheet, String sourceId) throws IOException, TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        File pricesHTML = new File("Knifes.html");
        FileOutputStream os = new FileOutputStream(pricesHTML);
        Transformer transformer = tFactory.newTransformer(new StreamSource(stylesheet));
        transformer.transform(new StreamSource(sourceId), new StreamResult(os));
        File htmlFile = new File("Knifes.html");
        Desktop.getDesktop().browse(htmlFile.toURI());
    }
}
