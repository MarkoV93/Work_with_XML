package domparser;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.xml.transform.TransformerException;
import org.jdom2.JDOMException;

public class Main {

    public static void main(String[] args) throws IOException, JDOMException, TransformerException {
DomParser domParser=new DomParser();
        List<Knife> knifes = domParser.domParser("../XMLof3Pro/knifes.xml");
        Collections.sort(knifes, new Knife());
        for (Knife k : knifes) {
            System.out.println(k);
        }

        System.out.println(DomParser.validateXMLSchema("../XMLof3Pro/knifes.xsd", "../XMLof3Pro/knifes.xml"));
        domParser.convertToHTML("../XMLof3Pro/knifes.xsl", "../XMLof3Pro/knifes.xml");
    }

}
