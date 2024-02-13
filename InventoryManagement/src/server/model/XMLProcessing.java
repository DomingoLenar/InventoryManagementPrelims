package server.model;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import utility.ItemOrder;
import utility.User;

public class XMLProcessing {



    public synchronized static boolean authenticate(User userToAuth){
        User localUser = findUser(userToAuth.getUsername());
        assert localUser != null;
        return localUser.equals(userToAuth);
    }

    public synchronized static User findUser(String userName) {
        try{
           Document doc = getXMLDocument("InventoryManagement/src/server/res/users.xml");

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
            Document document = getXMLDocument("InventoryManagement/src/server/res/users.xml");

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
            Document document = getXMLDocument("InventoryManagement/src/server/res/items.xml");

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
            Document document = getXMLDocument("InventoryManagement/src/server/res/items.xml");

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

    public static synchronized ArrayList<ItemOrder> fetchItemOrders(String dateFilter){
        ArrayList<ItemOrder> itemOrderList = new ArrayList<>();
        try{
            Document document = getXMLDocument("InventoryManagement/src/server/res/itemorders.xml");

            Element rootElement = document.getDocumentElement();
            NodeList itemOrders = rootElement.getElementsByTagName("itemorder");
            for(int x = 0; x < itemOrders.getLength(); x++){
                Element currentElement = (Element) itemOrders.item(x);

                int id = Integer.parseInt(currentElement.getAttribute("id"));
                String date = currentElement.getAttribute("date");
                float price = Float.parseFloat(currentElement.getElementsByTagName("price").item(0).getTextContent());
                String orderType = currentElement.getAttribute("orderType");
                int itemId = Integer.parseInt(currentElement.getElementsByTagName("id").item(0).getTextContent());
                if(dateFilter.equals("none")){
                    itemOrderList.add(new ItemOrder(id, date, price, orderType, itemId));
                }else{
                    if(date.equals(dateFilter)){
                        itemOrderList.add(new ItemOrder(id, date, price, orderType, itemId));
                    }
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return itemOrderList;
    }

    public static synchronized ArrayList<Item> fetchItems(){
        ArrayList<Item> itemList = new ArrayList<>();
        try{
            Document document = getXMLDocument("server/res/items.xml");
            Element element = document.getDocumentElement();

            NodeList items = element.getElementsByTagName("item");
            for(int x = 0; x < items.getLength(); x++){
                Element currentItem = (Element) items.item(x);

                String name = currentItem.getElementsByTagName("name").item(0).getTextContent();
                int amount = Integer.parseInt(currentItem.getElementsByTagName("amount").item(0).getTextContent());
                int id = Integer.parseInt(currentItem.getElementsByTagName("id").item(0).getTextContent());
                int price = Integer.parseInt(currentItem.getElementsByTagName("price").item(0).getTextContent());
                String type = currentItem.getElementsByTagName("type").item(0).getTextContent();
                itemList.add(new Item(name, amount, type,id,price));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return itemList;
    }

    private static synchronized Document getXMLDocument(String path) throws Exception{
       File xmlFile = new File(path);
       DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
       DocumentBuilder dB = dBF.newDocumentBuilder();
        return dB.parse(xmlFile);
    }
}
