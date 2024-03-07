package server.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utility.Item;
import utility.ItemOrder;
import utility.User;
import utility.revision.OrderDetails;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class XMLProcessing {
    public synchronized static User authenticate(User userToAuth){
        User localUser = findUser(userToAuth.getUsername());
        if(localUser != null){
            if(localUser.getPassword().equals(userToAuth.getPassword())){
                setActiveStatus(localUser, true);
                return localUser;
            }
        }
        return null;
    }

    public synchronized static void setActiveStatus(User user,boolean status){
        try{
            Document document = getXMLDocument("InventoryManagement/src/server/res/users.xml");

            Element rootElement = document.getDocumentElement();

            NodeList nodeList = rootElement.getElementsByTagName("user");
            for(int x = 0; x<nodeList.getLength(); x++){
                Element curUser = (Element) nodeList.item(x);
                if(curUser.getElementsByTagName("username").item(0).getTextContent().equals(user.getUsername())){
                    curUser.removeAttribute("active");
                    curUser.setAttribute("active",String.valueOf(status));
                    break;
                }
            }
            cleanXMLElement(rootElement);
            writeDOMToFile(rootElement, "InventoryManagement/src/server/res/users.xml");
        }catch(Exception e){
            e.printStackTrace();
        }
        if (status) {
            System.out.println(user.getUsername() + "is now active");
        } else {
            System.out.println(user.getUsername() + "is inactive");
        }
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
                        boolean active = userElement.getAttribute("active").equals("true");
                        return new User(username, password, role, active);
                    }

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public  static  synchronized  Stack<User> fetchListOfUsers (){
        Stack<User> listOfUsers = new Stack<>();
        try {
            Document document = getXMLDocument("InventoryManagement/src/server/res/users.xml");

            Element rootElement = document.getDocumentElement();
            NodeList users = rootElement.getElementsByTagName("user");
            for (int i = 0; i < users.getLength(); i++){
                Element currentElement = (Element) users.item(i);

                String username = currentElement.getElementsByTagName("username").item(0).getTextContent();
                String password = currentElement.getElementsByTagName("password").item(0).getTextContent();
                String role = currentElement.getAttribute("role");
                boolean online = Boolean.parseBoolean(currentElement.getAttribute("active"));

                if (online) {
                    listOfUsers.add(new User(username, password, role, true));
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listOfUsers;
    }

    /**
     * Method for creating a new user inside the xml file of the server
     * @param userToCreate     Object of user to create
     * @return                 Returns a boolean value if success or not
     */
    public static User createUser(User userToCreate){
        try{
            Document document = getXMLDocument("InventoryManagement/src/server/res/users.xml");

            Element root = document.getDocumentElement();

            Element newUser = document.createElement("user");
            newUser.setAttribute("role",userToCreate.getRole());
            newUser.setAttribute("active", "false");

            Element username = document.createElement("username");
            username.setTextContent(userToCreate.getUsername());

            Element password = document.createElement("password");
            password.setTextContent(userToCreate.getPassword());

            newUser.appendChild(username);
            newUser.appendChild(password);

            root.appendChild(newUser);

            cleanXMLElement(root);
            writeDOMToFile(root, "InventoryManagement/src/server/res/users.xml" );

        }catch(Exception e){
            throw new RuntimeException(e);
        }

        User newUser = findUser(userToCreate.getUsername());
        setActiveStatus(newUser, false);
        return newUser;
    }

    /**
     * Method to write new changes into the xml file
     * @param node      Root node to add
     * @param fileName
     */

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
    public static synchronized boolean addItem(Item itemToAdd) { // TODO: problem: id of an item not auto increment
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
            id.setTextContent(String.valueOf(itemToAdd.getItemId()));

            Element price = document.createElement("price");
            price.setTextContent(String.valueOf(itemToAdd.getPrice()));

            Element cost = document.createElement("cost");
            cost.setTextContent(String.valueOf(itemToAdd.getCost()));

            Element batchNo = document.createElement("batchNo");
            batchNo.setTextContent(String.valueOf(itemToAdd.getBatchNo()));

            newItem.appendChild(name);
            newItem.appendChild(quantity);
            newItem.appendChild(type);
            newItem.appendChild(id);
            newItem.appendChild(price);
            newItem.appendChild(cost);
            newItem.appendChild(batchNo);

            root.appendChild(newItem);

            cleanXMLElement(root);
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
    public static synchronized boolean removeItem(int itemId) { // TODO: id of an item must be in sequence
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

    public static synchronized boolean addItemOrder(ItemOrder itemOrder){ // TODO: id of item order not auto increment
        try{
            Document document = getXMLDocument("InventoryManagement/src/server/res/itemorders.xml");

            Element rootElement = document.getDocumentElement();

            Element newItemOrder = document.createElement("itemorder");
            newItemOrder.setAttribute("id", String.valueOf(itemOrder.getId()));
            newItemOrder.setAttribute("date", itemOrder.getDate());
            newItemOrder.setAttribute("price", String.valueOf(itemOrder.getPurchasePrice()));
            newItemOrder.setAttribute("orderType",itemOrder.getStatus());
            newItemOrder.setAttribute("item", String.valueOf(itemOrder.getItemId()));
            newItemOrder.setAttribute("byUser",itemOrder.getUsername());
            newItemOrder.setAttribute("quantity", String.valueOf(itemOrder.getQuantity()));


            Element id = document.createElement("id");
            id.setTextContent(String.valueOf(itemOrder.getId()));

            Element date = document.createElement("date");
            date.setTextContent(itemOrder.getDate());

            Element price = document.createElement("price");
            price.setTextContent(String.valueOf(itemOrder.getPurchasePrice()));

            Element status = document.createElement("orderType");
            status.setTextContent(itemOrder.getStatus());

            Element item = document.createElement("item");
            item.setTextContent(String.valueOf(itemOrder.getItemId()));

            Element byUser = document.createElement("byUser");
            byUser.setTextContent(itemOrder.getUsername());

            Element amount = document.createElement("amount");
            amount.setTextContent(String.valueOf(itemOrder.getQuantity()));  //Refactor ItemOrder first to take into account amount



            newItemOrder.appendChild(id);
            newItemOrder.appendChild(date);
            newItemOrder.appendChild(price);
            newItemOrder.appendChild(status);
            newItemOrder.appendChild(item);
            newItemOrder.appendChild(byUser);
            newItemOrder.appendChild(amount);



            rootElement.appendChild(newItemOrder);

            cleanXMLElement(rootElement);
            writeDOMToFile(rootElement, "InventoryManagement/src/server/res/itemorders.xml");
            return true;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static synchronized boolean removeItemOrder(int itemOrderID) { // TODO: id of item order must be in sequence
        try {
            Document document = getXMLDocument("InventoryManagement/src/server/res/itemorders.xml");

            Element root = document.getDocumentElement();
            NodeList itemOrderList = root.getElementsByTagName("itemorder");

            for (int i = 0; i < itemOrderList.getLength(); i++) {
                Element itemOrderElement = (Element) itemOrderList.item(i);
                Element idElement = (Element) itemOrderElement.getElementsByTagName("id").item(0);
                int ioID = Integer.parseInt(idElement.getTextContent());
                if (ioID == itemOrderID) {
                    root.removeChild(itemOrderElement);
                    writeDOMToFile(root, "InventoryManagement/src/server/res/itemorders.xml");
                    return true;
                }
            }
            return false; // Item not found
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized ArrayList<ItemOrder> fetchItemOrders(String orderFilter){
        ArrayList<ItemOrder> itemOrderList = new ArrayList<>();
        try{
            Document document = getXMLDocument("InventoryManagement/src/server/res/itemorders.xml");

            Element rootElement = document.getDocumentElement();
            NodeList itemOrders = rootElement.getElementsByTagName("itemorder");
            for(int x = 0; x < itemOrders.getLength(); x++){
                Element currentElement = (Element) itemOrders.item(x);

                int id = Integer.parseInt(currentElement.getElementsByTagName("id").item(0).getTextContent());
                String date = currentElement.getAttribute("date");
                float price = Float.parseFloat(currentElement.getElementsByTagName("price").item(0).getTextContent());
                String orderType = currentElement.getAttribute("orderType");
                int itemId = Integer.parseInt(currentElement.getElementsByTagName("item").item(0).getTextContent());
                String byUser = currentElement.getAttribute("byUser");
                int qty = Integer.parseInt(currentElement.getElementsByTagName("amount").item(0).getTextContent());
                if(orderFilter.equals("none")){
                    itemOrderList.add(new ItemOrder(id, date, price, orderType, itemId,byUser, qty));
                }else{
                    if(orderType.equalsIgnoreCase(orderFilter)){
                        itemOrderList.add(new ItemOrder(id, date, price, orderType, itemId,byUser, qty));
                    }
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return itemOrderList;
    }

    public static synchronized Stack<Item> fetchItems(){ // TODO: better approach is to have filterKey
        Stack<Item> itemList = new Stack<>();
        try{
            Document document = getXMLDocument("InventoryManagement/src/server/res/items.xml");
            Element element = document.getDocumentElement();

            NodeList items = element.getElementsByTagName("item");
            for(int x = 0; x < items.getLength(); x++){
                Element currentItem = (Element) items.item(x);

                String name = currentItem.getElementsByTagName("name").item(0).getTextContent();
                int quantity = Integer.parseInt(currentItem.getElementsByTagName("quantity").item(0).getTextContent());
                String type = currentItem.getElementsByTagName("type").item(0).getTextContent();
                int id = Integer.parseInt(currentItem.getElementsByTagName("id").item(0).getTextContent());
                float price = Float.parseFloat(currentItem.getElementsByTagName("price").item(0).getTextContent());
                float cost = Float.parseFloat(currentItem.getElementsByTagName("cost").item(0).getTextContent());
                float batchNo = Float.parseFloat(currentItem.getElementsByTagName("batchNo").item(0).getTextContent());


                itemList.add(new Item(name, quantity, type, id, price, cost, batchNo));
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


    /**
     * Method for changing a user's password
     *
     * @param userName The username of the user whose password is to be changed.
     * @param newPassword The new password to set for the user.
     * @return True if the password change was successful, false otherwise.
     */
    public static boolean changePassword(String userName, String newPassword, String oldPassword) {
        try {
            // Load XML document
            Document document = getXMLDocument("InventoryManagement/src/server/res/users.xml");
            Element root = document.getDocumentElement();
            NodeList userList = root.getElementsByTagName("user");

            // Iterate through user list
            for (int i = 0; i < userList.getLength(); i++) {
                Element userElement = (Element) userList.item(i);
                String name = userElement.getElementsByTagName("username").item(0).getTextContent();
                if (name.equals(userName)) {
                    // Found the user, update password if old password matches
                    Element passwordElement = (Element) userElement.getElementsByTagName("password").item(0);
                    String password = passwordElement.getTextContent();
                    if (password.equals(oldPassword)) {
                        passwordElement.setTextContent(newPassword);
                        // Write changes to XML file
                        cleanXMLDocument(document);
                        writeDOMToFile(document, "InventoryManagement/src/server/res/users.xml");
                        return true;
                    }
                }
            }
            // User not found or old password didn't match
            return false;
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
            return false;
        }
    }

    public static  boolean changeUserRole (String userName, String newRole) {
        try {

            Document document = getXMLDocument("InventoryManagement/src/server/res/users.xml");
            Element root = document.getDocumentElement();
            NodeList userList = root.getElementsByTagName("user");

            for (int i = 0; i < userList.getLength(); i++) {

                Element userElement = (Element) userList.item(i);
                String name = userElement.getElementsByTagName("username").item(0).getTextContent();

                if (name.equals(userName)) {

                    Element roleElement = (Element) userElement.getElementsByTagName("role").item(0);
                    String role = roleElement.getAttribute("role");

                    if (!role.equals(newRole)) {
                        roleElement.setAttribute("role", newRole);
                        cleanXMLDocument(document);
                        writeDOMToFile(document, "InventoryManagement/src/server/res/users.xml");
                        return true;
                    }

                }
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static ArrayList<OrderDetails> fetchOrderDetails(int searchByOrderID){
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        try {
            Document xmlDocument = getXMLDocument("InventoryManagement/src/server/res/orderdetails.xml");
            Element rootElement = xmlDocument.getDocumentElement();
            NodeList orderDetailsNodeList = rootElement.getElementsByTagName("orderdetail");

            for(int x=0; x<orderDetailsNodeList.getLength(); x++){
                Element currentOrderDetail = (Element)orderDetailsNodeList.item(x);
                int orderID =  Integer.parseInt(currentOrderDetail.getElementsByTagName("itemorderid").item(0).getTextContent());
                if(orderID==searchByOrderID){
                    int itemID = Integer.parseInt(currentOrderDetail.getElementsByTagName("itemid").item(0).getTextContent());
                    int units = Integer.parseInt(currentOrderDetail.getElementsByTagName("units").item(0).getTextContent());
                    String batchNo = currentOrderDetail.getElementsByTagName("batchNo").item(0).getTextContent();
                    float unitPrice = Float.parseFloat(currentOrderDetail.getElementsByTagName("unitPrice").item(0).getTextContent());
                    String supplier = currentOrderDetail.getElementsByTagName("supplier").item(0).getTextContent();
                    orderDetails.add(new OrderDetails(orderID,itemID,units,batchNo,unitPrice,supplier));
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return orderDetails;
    }


    private static void cleanXMLDocument(Document doc)
            throws XPathExpressionException {
    /* SOURCE on the code to clean the xml document:
        https://stackoverflow.com/questions/978810/how-to-strip-whitespace-only-text-nodes-from-a-dom-before-serialization
    */
        XPathFactory xpathFactory = XPathFactory.newInstance();
        // XPath to find empty text nodes
        XPathExpression xpathExp = xpathFactory
                .newXPath()
                .compile("//text()[normalize-space(.) = '']");
        NodeList emptyTextNodes = (NodeList) xpathExp
                .evaluate(doc, XPathConstants.NODESET);

        // Remove each empty text node from document.
        for (int i = 0; i < emptyTextNodes.getLength(); i++) {
            Node emptyTextNode = emptyTextNodes.item(i);
            emptyTextNode.getParentNode().removeChild(emptyTextNode);
        }
    }

    private static void cleanXMLElement(Element element) throws XPathExpressionException {
        // Initialize XPath
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        // XPath expression to find empty text nodes within the subtree of the given element
        XPathExpression xpathExp = xpath.compile(".//text()[normalize-space(.) = '']");

        // Evaluate the expression on the given element
        NodeList emptyTextNodes = (NodeList) xpathExp.evaluate(element, XPathConstants.NODESET);

        // Remove each empty text node found
        for (int i = 0; i < emptyTextNodes.getLength(); i++) {
            Node emptyTextNode = emptyTextNodes.item(i);
            emptyTextNode.getParentNode().removeChild(emptyTextNode);
        }
    }


}
