package hu.domparse.S9NJK6;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class DomQuerryS9NJK6 {
    public static void main(String[] args) {
        Document doc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse("src/hu/domparse/S9NJK6/XMLS9NJK6.xml");
            doc.getDocumentElement().normalize();

            System.out.println("Root element: "+doc.getDocumentElement().getNodeName()+"\n-------------------------\n");

            XPath xPath =  XPathFactory.newInstance().newXPath();

            String accountInfos="//account";

            //Accounts e-mail, user name, password
            NodeList accountNodeList=(NodeList) xPath.compile(accountInfos).evaluate(doc,XPathConstants.NODESET);

            for (int i=0;i< accountNodeList.getLength();i++) {
                Node accountNode = accountNodeList.item(i);

                if (accountNode.getNodeType() == Node.ELEMENT_NODE && accountNode.getNodeName().equals("account")) {
                    Element accountElement=(Element) accountNode;

                    String accountEmail=accountElement.getAttribute("email");
                    String accountUserName=accountElement.getElementsByTagName("user_name").item(0).getTextContent();
                    String password=accountElement.getElementsByTagName("password").item(0).getTextContent();

                    System.out.println("Current Element: "+accountNode.getNodeName());
                    System.out.println("E-mail address: "+accountEmail);
                    System.out.println("User name: "+accountUserName);
                    System.out.println("Password: "+password+"\n");
                }


            }

            //Greather or equal than 12000 damage done in statistic with character name
            NodeList damageNodeList= (NodeList) xPath.compile("//statistic/damage_done").evaluate(doc,XPathConstants.NODESET);

            for (int i=0;i< damageNodeList.getLength();i++) {
                Node damageDone = damageNodeList.item(i);
                int damage = Integer.parseInt(damageDone.getTextContent());
                Element damageElement = (Element) damageDone;
                if (damage >= 12000){
                    Element nameElement = (Element) damageElement.getParentNode();
                System.out.println("Character: " + nameElement.getAttribute("character_name"));
                System.out.println("Damage done: " + damage + "\n");
            }

            }


        } catch(SAXException | IOException | ParserConfigurationException | NumberFormatException e) {
            System.err.println(e.getMessage());

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
