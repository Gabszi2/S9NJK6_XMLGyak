package domS9NJK61026;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomWriteS9NJK6 {
    public static void main(String[] args) throws ParserConfigurationException, TransformerConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc=builder.newDocument();

        Element root=doc.createElementNS("domkprlnb","users");
        doc.appendChild(root);

        root.appendChild(createUser(doc,"1","Pal","Kiss","progtamer"));
        root.appendChild(createUser(doc,"2","Pal","Kiss","progtamer"));
        root.appendChild(createUser(doc,"3","Pal","Kiss","progtamer"));

        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");

        DOMSource source=new DOMSource();

        File myFile=new File("users1.xml");

        StreamResult console=new StreamResult(System.out);
        StreamResult file=new StreamResult(myFile);




    }
    public static Node createUser(Document doc,String id,String firstName,String lastName,String profession){
        Element user=doc.createElement("user");

        user.setAttribute("id",id);
        user.appendChild(createUserElement(doc,"firstname",firstName));
        user.appendChild(createUserElement(doc,"lastname",lastName));
        user.appendChild(createUserElement(doc,"profession",profession));

    }
}
