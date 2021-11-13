package domS9NJK61026;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DomReadKPRLNB {

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {



            File xmlFile = new File("usersS9NJK6.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc=dBuilder.parse(xmlFile);


            for (int i=0;i<nList.getLenght;i++) {
                Node nNode = nList.item(i);
                System.out.println("\nCurrent Element" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    String uid = elem.getAttribute("id");

                    Node node1 = elem.getElementsByTagName("firstname").item(0);
                    String firstname = node1.getTextContent();

                    Node node2 = elem.getElementsByTagName("lastname").item(0);
                    String lasttname = node2.getTextContent();

                    Node node3 = elem.getElementsByTagName("profession").item(0);
                    String profession = node3.getTextContent();

                    System.out.printf("User id: %s%n",uid);
                    System.out.printf("First name: %s%n",uid);
                    System.out.printf("Last name: %s%n",uid);
                    System.out.printf("Profession: %s%n",uid);

                }

            }
    }
}
