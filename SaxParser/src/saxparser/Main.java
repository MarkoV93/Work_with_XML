/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        InputStream is = new FileInputStream(new File("../XMLof3Pro/knifes.xml"));
        SaxParser obj = new SaxParser();
        parser.parse(is, obj);
        List<Knife> knifes = obj.getKnifes();
        Collections.sort(knifes, new Knife());
        for (Knife k : knifes) {
            System.out.println(k);
        }
        System.out.println(obj.validateXMLSchema("../XMLof3Pro/knifes.xsd", "../XMLof3Pro/knifes.xml"));
        obj.convertToHTML("../XMLof3Pro/knifes.xsl", "../XMLof3Pro/knifes.xml");
    }

}
