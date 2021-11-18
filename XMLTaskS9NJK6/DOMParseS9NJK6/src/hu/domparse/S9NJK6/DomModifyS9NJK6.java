package hu.domparse.S9NJK6;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class DomModifyS9NJK6 {
    public static void main(String[] args) {
        Document doc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse("src/hu/domparse/S9NJK6/XMLS9NJK6.xml");
            doc.getDocumentElement().normalize();
            XPath xPath =  XPathFactory.newInstance().newXPath();
            Node rootNode = doc.getChildNodes().item(0);

            //Character Gátierő's name change to MuscleFbrain
            NodeList characterNamesNodeList=(NodeList) xPath.compile("//*[@character_name]").evaluate(doc, XPathConstants.NODESET);
            NodeList friendCharNameNodeList=(NodeList) xPath.compile("//*[@friendCharacter_name]").evaluate(doc, XPathConstants.NODESET);
            NodeList myCharNameNodeList=(NodeList) xPath.compile("//*[@myCharacter_name]").evaluate(doc, XPathConstants.NODESET);

            changeAttribute(characterNamesNodeList,"character_name","Gátierő","MuscleFbrain");
            changeAttribute(friendCharNameNodeList,"friendCharacter_name","Gátierő","MuscleFbrain");
            changeAttribute(myCharNameNodeList,"myCharacter_name","Gátierő","MuscleFbrain");

            //Change character Legoolass's damage done from 15000 to 16000,rund from 5 to 6 and deaths from 3 to 4 from in statistics with the date 2021-11-10
           NodeList damageChangeNodeList=(NodeList) xPath.compile("//statistic[@character_name='Legoolass']").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < damageChangeNodeList.getLength(); i++) {

                Node characterNode = damageChangeNodeList.item(i);

                if(characterNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element characterElement = (Element) characterNode;

                    if (characterElement.getElementsByTagName("date").item(0).getTextContent().equals("2021-11-10")){
                        NodeList damageNodeList=characterElement.getElementsByTagName("damage_done");

                        for (int j=0;j< damageNodeList.getLength();j++){
                            Node damageNode=damageNodeList.item(j);

                            if (damageNode.getNodeType()==Node.ELEMENT_NODE){
                                damageNode.setTextContent("16000");

                            }

                        }
                        NodeList deathsNodeList=characterElement.getElementsByTagName("deaths");

                        for (int j=0;j< deathsNodeList.getLength();j++){
                            Node deathsNode=deathsNodeList.item(j);

                            if (deathsNode.getNodeType()==Node.ELEMENT_NODE){
                                deathsNode.setTextContent("4");

                            }

                        }
                        NodeList runsNodeList=characterElement.getElementsByTagName("completed_runs");

                        for (int j=0;j< runsNodeList.getLength();j++){
                            Node runsNode=runsNodeList.item(j);

                            if (runsNode.getNodeType()==Node.ELEMENT_NODE){
                                runsNode.setTextContent("6");

                            }

                        }

                    }


                }
            }
            Transformer tf = TransformerFactory.newInstance().newTransformer();

            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(rootNode);
            StreamResult console = new StreamResult(System.out);

            System.out.println("----------------- Modified File -----------------");
            tf.transform(source, console);


        }catch(SAXException | IOException | ParserConfigurationException | NumberFormatException e) {
            System.err.println(e.getMessage());

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void changeAttribute(NodeList nodeList,String attributeName,String from,String to){
        for (int i=0;i< nodeList.getLength();i++){

            Node charNamesNode=nodeList.item(i);

            if(charNamesNode.getNodeType() == Node.ELEMENT_NODE) {
                Element charNameElement=(Element) charNamesNode;
                if (charNameElement.getAttribute(attributeName).equals(from)){
                    charNameElement.setAttribute(attributeName,to);
                }
            }
        }
    }
}
