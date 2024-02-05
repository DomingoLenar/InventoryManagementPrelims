package server.model;

import java.io.File;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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



    public synchronized static boolean authenticate(User userToAuth){
        User localUser = findUser(userToAuth.getUsername());
        assert localUser != null;
        return localUser.equals(userToAuth);
    }

    public synchronized static User findUser(String userName) {
        try{
           File xmlFile = new File("InventoryManagement/src/server/res/users.xml");
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

    public static boolean createUser(User userToCreate){
        try{
            File userXML = new File("InventoryManagement/src/server/res/users.xml");
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dBF.newDocumentBuilder();
            Document document = documentBuilder.parse(userXML);

            Element root = document.getDocumentElement();

            Element newUser = document.createElement("user");

            Element username = document.createElement("username");
            username.setTextContent(userToCreate.getUsername());

            Element password = document.createElement("password");
            password.setTextContent(userToCreate.getPassword());

            newUser.appendChild(username);
            newUser.appendChild(password);

            root.appendChild(newUser);

            writeDOMToFile(root, "InventoryManagement/src/server/res/users.xml" );

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }

    private static void writeDOMToFile(Node node, String fileName) {
        try {
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(node);

            PrintWriter fileWriter = new PrintWriter(fileName);
            StreamResult result = new StreamResult(fileWriter);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
