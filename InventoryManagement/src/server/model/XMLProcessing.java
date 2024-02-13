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
import utility.Item;
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
                        String role = userElement.getAttribute("role");
                        return new User(username, password, role);
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
            newUser.setAttribute("role",userToCreate.getRole());

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

    /**
     * Adds an item to the XML file.
     *
     * @param itemToAdd The item to add.
     * @return True if the item is successfully added; false otherwise.
     */
    public static synchronized boolean addItem(Item itemToAdd) {
        try {
            File itemXML = new File("InventoryManagement/src/server/res/items.xml");
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dBF.newDocumentBuilder();
            Document document = documentBuilder.parse(itemXML);

            Element root = document.getDocumentElement();

            Element newItem = document.createElement("item");

            Element name = document.createElement("name");
            name.setTextContent(itemToAdd.getName());

            Element quantity = document.createElement("quantity");
            quantity.setTextContent(String.valueOf(itemToAdd.getQty()));

            Element type = document.createElement("type");
            type.setTextContent(itemToAdd.getType());

            Element id = document.createElement("id");
            id.setTextContent(String.valueOf(itemToAdd.getId()));

            Element price = document.createElement("price");
            price.setTextContent(String.valueOf(itemToAdd.getPrice()));

            newItem.appendChild(name);
            newItem.appendChild(quantity);
            newItem.appendChild(type);
            newItem.appendChild(id);
            newItem.appendChild(price);

            root.appendChild(newItem);

            writeDOMToFile(root, "InventoryManagement/src/server/res/items.xml");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes an item from the XML file based on its ID.
     *
     * @param itemId The ID of the item to remove.
     * @return True if the item is successfully removed; false otherwise.
     */
    public static synchronized boolean removeItem(int itemId) {
        try {
            File itemXML = new File("InventoryManagement/src/server/res/items.xml");
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dBF.newDocumentBuilder();
            Document document = documentBuilder.parse(itemXML);

            Element root = document.getDocumentElement();
            NodeList itemList = root.getElementsByTagName("item");

            for (int i = 0; i < itemList.getLength(); i++) {
                Element itemElement = (Element) itemList.item(i);
                Element idElement = (Element) itemElement.getElementsByTagName("id").item(0);
                int id = Integer.parseInt(idElement.getTextContent());
                if (id == itemId) {
                    root.removeChild(itemElement);
                    writeDOMToFile(root, "InventoryManagement/src/server/res/items.xml");
                    return true;
                }
            }
            return false; // Item not found
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
