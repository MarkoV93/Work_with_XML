package staxparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

public class Louncher {

    public static void main(String[] args) throws FileNotFoundException, IOException, TransformerException {

        StaxParser staxParser = new StaxParser();
        List<Knife> knifes = staxParser.parseee("../XMLof3Pro/knifes.xml");
        Collections.sort(knifes, new Knife());
        for (Knife k : knifes) {
            System.out.println(k);
        }
        System.out.println(staxParser.validateXMLSchema("../XMLof3Pro/knifes.xsd", "../XMLof3Pro/knifes.xml"));
staxParser.convertToHTML("../XMLof3Pro/knifes.xsl", "../XMLof3Pro/knifes.xml");

    }
}
