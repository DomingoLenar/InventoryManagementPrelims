package server.model;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utility.User;

public class XMLProcessing {




    public static User findUser(String userName) {
        try{
           File xmlFile = new File("InventoryManagement/src/server/data/users.xml");
           DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
           DocumentBuilder dB = dbF.newDocumentBuilder();
           Document doc = dB.parse(xmlFile);

           Element rootElement = doc.getDocumentElement();
           NodeList userList = rootElement.getElementsByTagName("user");

            for (int i = 0; i < userList.getLength(); i++) {
                Node userNode = userList.item(i);

                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) userNode;

                    // Get username and password from user element
                    String username = userElement.getElementsByTagName("username").item(0).getTextContent();
                    if(username.equals(userName)){
                        String password = userElement.getElementsByTagName("password").item(0).getTextContent();
                        return new User(username, password);
                    }

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
