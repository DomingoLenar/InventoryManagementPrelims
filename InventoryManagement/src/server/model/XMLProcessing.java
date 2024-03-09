package server.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utility.User;
import utility.revision.Item;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;
import utility.revision.Stock;

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
import java.util.LinkedList;
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

            Element id = document.createElement("id");
            id.setTextContent(String.valueOf(itemToAdd.getId()));

            Element quantity = document.createElement("totalqty");
            quantity.setTextContent(String.valueOf(itemToAdd.getTotalQty()));

            Element type = document.createElement("type");
            type.setTextContent(itemToAdd.getType());

            Element stocks = document.createElement("stocks");

                // Check if there are no stocks available
            if (itemToAdd.getAllStocks().isEmpty()) {
                // Create an empty <stock> element
                Element emptyStock = document.createElement("stock");
                stocks.appendChild(emptyStock);
            } else {
                // If there are stocks available, add them to the <stocks> element
                for (Stock stock : itemToAdd.getAllStocks()) {
                    Element stockElement = document.createElement("stock");

                    Element batchNo = document.createElement("batchNo");
                    batchNo.setTextContent(stock.getBatchNo());

                    Element supplier = document.createElement("supplier");
                    supplier.setTextContent(stock.getSupplier());

                    Element cost = document.createElement("cost");
                    cost.setTextContent(String.valueOf(stock.getCost()));

                    Element price = document.createElement("price");
                    price.setTextContent(String.valueOf(stock.getPrice()));

                    Element qty = document.createElement("qty");
                    qty.setTextContent(String.valueOf(stock.getQty()));

                    stockElement.appendChild(batchNo);
                    stockElement.appendChild(supplier);
                    stockElement.appendChild(cost);
                    stockElement.appendChild(price);
                    stockElement.appendChild(qty);

                    stocks.appendChild(stockElement);
                }
            }

            newItem.appendChild(name);
            newItem.appendChild(id);
            newItem.appendChild(quantity);
            newItem.appendChild(type);
            newItem.appendChild(stocks);

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

            newItemOrder.setAttribute("byUser",itemOrder.getCreatedBy().getUsername());
            newItemOrder.setAttribute("orderId", String.valueOf(itemOrder.getOrderId()));
            newItemOrder.setAttribute("date", itemOrder.getDate());
            newItemOrder.setAttribute("orderType",itemOrder.getOrderType());


            Element byUser = document.createElement("byUser");
            byUser.setTextContent(itemOrder.getCreatedBy().getUsername());

            Element id = document.createElement("id");
            id.setTextContent(String.valueOf(itemOrder.getOrderId()));

            Element date = document.createElement("date");
            date.setTextContent(itemOrder.getDate());

            Element orderType = document.createElement("orderType");
            orderType.setTextContent(itemOrder.getOrderType());



            newItemOrder.appendChild(byUser);
            newItemOrder.appendChild(id);
            newItemOrder.appendChild(date);
            newItemOrder.appendChild(orderType);


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

                int orderId = Integer.parseInt(currentElement.getElementsByTagName("orderId").item(0).getTextContent());
                String date = currentElement.getElementsByTagName("date").item(0).getTextContent();
                String orderType = currentElement.getElementsByTagName("orderType").item(0).getTextContent();
                String byUserName = currentElement.getElementsByTagName("createdby").item(0).getTextContent();


                if(orderFilter.equals("none") || orderType.equalsIgnoreCase(orderFilter)){
                    itemOrderList.add(new ItemOrder(new User(byUserName), orderId, date, orderType));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return itemOrderList;
    }


    /**
     * A method for fetching all items
     * @return itemList
     */
    public static synchronized Stack<Item> fetchItems() { // TODO: better approach is to have filterKey
        Stack<Item> itemList = new Stack<>();
        try {
            Document document = getXMLDocument("InventoryManagement/src/server/res/items.xml");
            Element element = document.getDocumentElement();

            NodeList items = element.getElementsByTagName("item");
            for (int x = 0; x < items.getLength(); x++) {
                Element currentItem = (Element) items.item(x);

                String name = currentItem.getElementsByTagName("name").item(0).getTextContent();
                int id = Integer.parseInt(currentItem.getElementsByTagName("id").item(0).getTextContent());
                int quantity = Integer.parseInt(currentItem.getElementsByTagName("totalqty").item(0).getTextContent());
                String type = currentItem.getElementsByTagName("type").item(0).getTextContent();

                LinkedList<Stock> stocks = new LinkedList<>();
                Element stocksRoot = (Element) currentItem.getElementsByTagName("stocks");
                NodeList stockList = stocksRoot.getElementsByTagName("stock");
                for (int i = 0; i < stockList.getLength(); i++) {
                    Element stockElement = (Element) stockList.item(i);
                    String batchNo = stockElement.getElementsByTagName("batchNo").item(0).getTextContent();
                    String supplier = stockElement.getElementsByTagName("supplier").item(0).getTextContent();
                    float cost = Float.parseFloat(stockElement.getElementsByTagName("cost").item(0).getTextContent());
                    float price = Float.parseFloat(stockElement.getElementsByTagName("price").item(0).getTextContent());
                    int qty = Integer.parseInt(stockElement.getElementsByTagName("qty").item(0).getTextContent());

                    Stock stock = new Stock(batchNo,cost ,price, qty, supplier);
                    stocks.add(stock);
                }

                itemList.add(new Item(name,id ,quantity ,type , stocks));
            }
        } catch (Exception e) {
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
    public static Item fetchItem(int itemID, boolean needStockData){
        try{
            Document xmlDocument = getXMLDocument("InventoryManagement/src/server/res/items.xml");
            Element rootElement = xmlDocument.getDocumentElement();
            NodeList itemList = rootElement.getElementsByTagName("item");

            for(int x=0; x<itemList.getLength();x++){
                Element currentItem = (Element)itemList.item(x);
                int currentItemID = Integer.parseInt(currentItem.getElementsByTagName("id").item(0).getTextContent());
                if(itemID == currentItemID){
                    String name = currentItem.getElementsByTagName("name").item(0).getTextContent();
                    int totalQty= Integer.parseInt(currentItem.getElementsByTagName("totalqty").item(0).getTextContent());
                    String type = currentItem.getElementsByTagName("type").item(0).getTextContent();
                    Item item = new Item(name, currentItemID, type);

                    if(needStockData) {
                        LinkedList<Stock> stocks = new LinkedList<>();
                        Element stocksRoot = (Element) currentItem.getElementsByTagName("stocks");
                        NodeList stockList = stocksRoot.getElementsByTagName("stock");

                        for (int i = 0;i<stockList.getLength();i++){
                            Element stockElement = (Element) stockList.item(i);
                            String batchNo = stockElement.getElementsByTagName("batchNo").item(0).getTextContent();
                            String supplier = stockElement.getElementsByTagName("supplier").item(0).getTextContent();
                            float cost = Float.parseFloat(stockElement.getElementsByTagName("cost").item(0).getTextContent());
                            float price = Float.parseFloat(stockElement.getElementsByTagName("price").item(0).getTextContent());
                            int qty = Integer.parseInt(stockElement.getElementsByTagName("qty").item(0).getTextContent());

                            Stock stock = new Stock(batchNo, cost, price, qty, supplier);
                            stocks.add(stock);
                        }
                      item.setStocks(stocks);

                    }
                    return item;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method for retrieving one Order
      * @param orderId
     * @return
     */
   public static ItemOrder fetchItemOrder(int orderId){
        try {
            Document document = getXMLDocument("InventoryManagement/src/server/res/itemorders.xml");

            Element rootElemetn = document.getDocumentElement();
            NodeList itemOrders = rootElemetn.getElementsByTagName("itemorder");

            for (int i=0; i< itemOrders.getLength();i++){
                Element currentElement = (Element)itemOrders.item(i);
                int currentOrderID = Integer.parseInt(currentElement.getElementsByTagName("id").item(0).getTextContent());

                if (orderId== currentOrderID ){
                    String date = currentElement.getElementsByTagName("date").item(0).getTextContent();
                    String orderType = currentElement.getElementsByTagName("orderType").item(0).getTextContent();
                    String byUserName = currentElement.getElementsByTagName("createdBy").item(0).getTextContent();

                    User createdBy = new User(byUserName);

                    return  new ItemOrder(createdBy, orderId,date,orderType);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    return null;
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
