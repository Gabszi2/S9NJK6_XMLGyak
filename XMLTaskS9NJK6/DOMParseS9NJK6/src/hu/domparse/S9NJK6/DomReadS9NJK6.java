package hu.domparse.S9NJK6;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DomReadS9NJK6 {
    public static void main(String[] args) {
        Document doc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();



        try {


            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse("src/hu/domparse/S9NJK6/XMLS9NJK6.xml");


            System.out.println("Root Element:" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            NodeList userNodeList = doc.getElementsByTagName("user");

            for (int i = 0; i < userNodeList.getLength(); i++) {

                Node userNode = userNodeList.item(i);

                if (userNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element userElement = (Element) userNode;

                    System.out.println("Current Element: "+userNode.getNodeName()+"\n");

                    //Account
                    NodeList accountNodeList=userElement.getElementsByTagName("account");
                    Node accountNode=accountNodeList.item(0);
                    Element accountElement=(Element)accountNode;

                    String accountEmail=accountElement.getAttribute("email");
                    String accountUserName=accountElement.getElementsByTagName("user_name").item(0).getTextContent();
                    String password=accountElement.getElementsByTagName("password").item(0).getTextContent();


                    System.out.println("Current Element: "+accountNode.getNodeName());
                    System.out.println("E-mail address: "+accountEmail);
                    System.out.println("User name: "+accountUserName);
                    System.out.println("Password: "+password+"\n");

                    //Characters
                    NodeList charactersNodeList=accountElement.getElementsByTagName("character");
                    for (int j=0;j<charactersNodeList.getLength();j++){
                        Node characterNode=charactersNodeList.item(j);
                        Element characterElement=(Element) characterNode;

                        String characterName=characterElement.getAttribute("character_name");
                        String characterClass=characterElement.getElementsByTagName("class").item(0).getTextContent();
                        String race=characterElement.getElementsByTagName("race").item(0).getTextContent();

                        System.out.println("Current Element: "+characterNode.getNodeName());
                        System.out.println("Name: "+characterName);
                        System.out.println("Class: "+characterClass);
                        System.out.println("Race: "+race+"\n");

                    }

                    //Statistics
                    NodeList statisticsNodeList=userElement.getElementsByTagName("statistic");

                    for (int j=0;j<statisticsNodeList.getLength();j++){
                        Node statisticNode=statisticsNodeList.item(j);
                        Element statisticElement=(Element) statisticNode;

                        String characterName=statisticElement.getAttribute("character_name");
                        String characterClass=statisticElement.getElementsByTagName("class").item(0).getTextContent();
                        String race=statisticElement.getElementsByTagName("race").item(0).getTextContent();
                        String damage=statisticElement.getElementsByTagName("damage_done").item(0).getTextContent();
                        String healing=statisticElement.getElementsByTagName("healing_done").item(0).getTextContent();
                        String deaths=statisticElement.getElementsByTagName("deaths").item(0).getTextContent();
                        String runs=statisticElement.getElementsByTagName("completed_runs").item(0).getTextContent();
                        String date=statisticElement.getElementsByTagName("date").item(0).getTextContent();

                        System.out.println("Current Element: "+statisticNode.getNodeName());
                        System.out.println("Name: "+characterName);
                        System.out.println("Class: "+characterClass);
                        System.out.println("Race: "+race);
                        System.out.println("Damage done: "+damage);
                        System.out.println("Healing done: "+healing);
                        System.out.println("Deaths: "+deaths);
                        System.out.println("Completed runs: "+runs);
                        System.out.println("Date: "+date+"\n");
                    }

                    //Leader board
                    NodeList leaderBoardNodeList=userElement.getElementsByTagName("leader_board");
                    Node leaderBoardNode=leaderBoardNodeList.item(0);
                    Element leaderBoardElement=(Element) leaderBoardNode;

                    String leaderEmail=leaderBoardElement.getAttribute("email");
                    String leaderUserName=leaderBoardElement.getElementsByTagName("user_name").item(0).getTextContent();
                    String leaderDamage=leaderBoardElement.getElementsByTagName("damage_done").item(0).getTextContent();
                    String leaderHealing=leaderBoardElement.getElementsByTagName("healing_done").item(0).getTextContent();
                    String leaderDeaths=leaderBoardElement.getElementsByTagName("deaths").item(0).getTextContent();
                    String place=leaderBoardElement.getElementsByTagName("place").item(0).getTextContent();

                    System.out.println("Current Element: "+leaderBoardNode.getNodeName());
                    System.out.println("E-mail address: "+leaderEmail);
                    System.out.println("User name: "+leaderUserName);
                    System.out.println("Damage done: "+leaderDamage);
                    System.out.println("Healing done: "+leaderHealing);
                    System.out.println("Deaths: "+leaderDeaths);
                    System.out.println("Place: "+place+"\n");

                    //Requests
                    NodeList requestsNodeList=userElement.getElementsByTagName("request");
                    for (int j=0;j< requestsNodeList.getLength();j++){
                        Node requestNode=requestsNodeList.item(j);
                        Element requestElement=(Element) requestNode;

                        String myCharacterName=requestElement.getAttribute("myCharacter_name");
                        String friendCharacterName=requestElement.getAttribute("friendCharacter_name");
                        String accepted=requestElement.getElementsByTagName("accepted").item(0).getTextContent();

                        System.out.println("Current Element: "+requestNode.getNodeName());
                        System.out.println("My character name: "+myCharacterName);
                        System.out.println("Friend character name: "+friendCharacterName);
                        System.out.println("Accepted: "+accepted+"\n");
                    }

                    //Friends
                    NodeList friendsNodeList=userElement.getElementsByTagName("friend");
                    for (int j=0;j< friendsNodeList.getLength();j++){
                        Node friendNode=friendsNodeList.item(j);
                        Element friendElement=(Element) friendNode;

                        String characterName=friendElement.getAttribute("character_name");
                        String online=friendElement.getElementsByTagName("online").item(0).getTextContent();
                        String lastOnline=friendElement.getElementsByTagName("last_online").item(0).getTextContent();

                        System.out.println("Current Element: "+friendNode.getNodeName());
                        System.out.println("Character name: "+characterName);
                        System.out.println("Online: "+online);
                        System.out.println("Last online: "+lastOnline+"\n");
                    }

                }


            }


        }catch(SAXException | IOException | ParserConfigurationException | NumberFormatException e) {
            System.err.println(e.getMessage());

        }
    }
}
