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
                if (localUser.isActive()) {
                    return null;
                }
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
        User searchUser = findUser(userToCreate.getUsername());
        if (searchUser != null){ // user exist
            return null;
        }
        else { // create the user
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
            User newUser = findUser(userToCreate.getUsername()); // validate
            setActiveStatus(newUser, false);
            return newUser;
        }
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
    public static synchronized boolean addItem(Item itemToAdd) {
        try {
            Document document = getXMLDocument("InventoryManagement/src/server/res/items.xml");

            Element root = document.getDocumentElement();

            int lastId = 0;
            NodeList itemNodes = root.getElementsByTagName("item");
            for (int i = 0; i < itemNodes.getLength(); i++) {
                Element itemElement = (Element) itemNodes.item(i);
                int itemId = Integer.parseInt(itemElement.getElementsByTagName("id").item(0).getTextContent());
                if (itemId > lastId) {
                    lastId = itemId;
                }
            }

            int newId = lastId + 1;
            itemToAdd.setId(newId);

            Element newItem = document.createElement("item");

            Element name = document.createElement("name");
            name.setTextContent(itemToAdd.getName());

            Element id = document.createElement("id");
            id.setTextContent(String.valueOf(itemToAdd.getId()));

            Element quantity = document.createElement("totalqty");
            quantity.setTextContent(String.valueOf(itemToAdd.getTotalQty()));

            Element type = document.createElement("type");
            type.setTextContent(itemToAdd.getType());

            Element parentStocks = document.createElement("stocks");

            // Check if there are stocks available
            if (!itemToAdd.getAllStocks().isEmpty()) {
                // If there are stocks available, add them to the <stocks> element
                for (Stock stock : itemToAdd.getAllStocks()) {
                    Element childStocks = document.createElement("stock");
                    Element batchNo = document.createElement("batchNo");
                    Element supplier = document.createElement("supplier");
                    Element cost = document.createElement("cost");
                    Element price = document.createElement("price");
                    Element qty = document.createElement("qty");

                    batchNo.setTextContent(stock.getBatchNo());

                    supplier.setTextContent(stock.getSupplier());

                    cost.setTextContent(String.valueOf(stock.getCost()));

                    price.setTextContent(String.valueOf(stock.getPrice()));

                    qty.setTextContent(String.valueOf(stock.getQty()));

                    parentStocks.appendChild(childStocks);
                }
            }

            newItem.appendChild(name);
            newItem.appendChild(id);
            newItem.appendChild(quantity);
            newItem.appendChild(type);
            newItem.appendChild(parentStocks);

            root.appendChild(newItem);

            cleanXMLElement(root);
            writeDOMToFile(root, "InventoryManagement/src/server/res/items.xml");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean addOrderDetails(OrderDetails orderDetailsToAdd) {
        try {
            Document document = getXMLDocument("InventoryManagement/src/server/res/orderdetails.xml");

            Element root = document.getDocumentElement();

            int lastId = 0;
            NodeList orderDetailNodes = root.getElementsByTagName("orderdetail");
            for (int i = 0; i < orderDetailNodes.getLength(); i++) {
                Element orderDetailElement = (Element) orderDetailNodes.item(i);
                int orderDetailId = Integer.parseInt(orderDetailElement.getElementsByTagName("itemorderid").item(0).getTextContent());
                if (orderDetailId > lastId) {
                    lastId = orderDetailId;
                }
            }

            int newId = lastId + 1;
            orderDetailsToAdd.setItemOrderID(newId);

            Element newOrderDetail = document.createElement("orderdetail");

            Element itemOrderID = document.createElement("itemorderid");
            itemOrderID.setTextContent(String.valueOf(orderDetailsToAdd.getItemOrderID()));

            Element itemID = document.createElement("itemid");
            itemID.setTextContent(String.valueOf(orderDetailsToAdd.getItemID()));

            Element units = document.createElement("units");
            units.setTextContent(String.valueOf(orderDetailsToAdd.getUnits()));

            Element batchNo = document.createElement("batchNo");
            batchNo.setTextContent(orderDetailsToAdd.getBatchNo());

            Element unitPrice = document.createElement("unitPrice");
            unitPrice.setTextContent(String.valueOf(orderDetailsToAdd.getUnitPrice()));

            Element supplier = document.createElement("supplier");
            supplier.setTextContent(orderDetailsToAdd.getSupplier());

            newOrderDetail.appendChild(itemOrderID);
            newOrderDetail.appendChild(itemID);
            newOrderDetail.appendChild(units);
            newOrderDetail.appendChild(batchNo);
            newOrderDetail.appendChild(unitPrice);
            newOrderDetail.appendChild(supplier);

            root.appendChild(newOrderDetail);

            cleanXMLElement(root);
            writeDOMToFile(root, "InventoryManagement/src/server/res/orderdetails.xml");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean addStock(int itemId, Stock stockToAdd) {
        try {
            Document document = getXMLDocument("InventoryManagement/src/server/res/items.xml");
            Element root = document.getDocumentElement();

            NodeList items = root.getElementsByTagName("item");
            boolean itemFound = false;
            for (int i = 0; i < items.getLength(); i++) {
                Element currentItem = (Element) items.item(i);
                String currentItemID = currentItem.getElementsByTagName("id").item(0).getTextContent();
                if (Integer.parseInt(currentItemID) == itemId) {

                    Element stocksElement = (Element) currentItem.getElementsByTagName("stocks").item(0);
                    NodeList stockList = stocksElement.getElementsByTagName("stock");

                    int totalQty = 0;
                    for (int j = 0; j < stockList.getLength(); j++) {
                        Element stock = (Element) stockList.item(j);
                        int qty = Integer.parseInt(stock.getElementsByTagName("qty").item(0).getTextContent());
                        totalQty += qty;
                    }

                    boolean totalQtyExists = currentItem.getElementsByTagName("totalqty").getLength() > 0;

                    Element stockElement = document.createElement("stock");

                    Element batchNo = document.createElement("batchNo");
                    batchNo.setTextContent(stockToAdd.getBatchNo());

                    Element supplier = document.createElement("supplier");
                    supplier.setTextContent(stockToAdd.getSupplier());

                    Element cost = document.createElement("cost");
                    cost.setTextContent(String.valueOf(stockToAdd.getCost()));

                    Element price = document.createElement("price");
                    price.setTextContent(String.valueOf(stockToAdd.getPrice()));

                    Element qtyElement = document.createElement("qty");
                    qtyElement.setTextContent(String.valueOf(stockToAdd.getQty()));

                    int newQty;
                    if (totalQtyExists) {
                        int existingTotalQty = Integer.parseInt(currentItem.getElementsByTagName("totalqty").item(0).getTextContent());
                        newQty = existingTotalQty + stockToAdd.getQty();
                    } else {
                        newQty = stockToAdd.getQty();
                    }


                    stockElement.appendChild(batchNo);
                    stockElement.appendChild(supplier);
                    stockElement.appendChild(cost);
                    stockElement.appendChild(price);
                    stockElement.appendChild(qtyElement);

                    stocksElement.appendChild(stockElement);

                    if (!totalQtyExists) {
                        Element totalQtyElement = document.createElement("totalqty");
                        totalQtyElement.setTextContent(String.valueOf(newQty));
                        currentItem.appendChild(totalQtyElement);
                    } else {
                        currentItem.getElementsByTagName("totalqty").item(0).setTextContent(String.valueOf(newQty));
                    }

                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                System.out.println("No item found with the provided ID.");
                return false;
            }

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

    public static synchronized int addItemOrder(ItemOrder itemOrder){ // TODO: id of item order not auto increment
        try{
            Document document = getXMLDocument("InventoryManagement/src/server/res/itemorders.xml");

            Element rootElement = document.getDocumentElement();

            int lastId = 0;
            NodeList itemOrderNodes = rootElement.getElementsByTagName("itemorder");
            for (int i = 0; i < itemOrderNodes.getLength(); i++) {
                Element itemOrderElement = (Element) itemOrderNodes.item(i);
                int orderId = Integer.parseInt(itemOrderElement.getElementsByTagName("orderId").item(0).getTextContent());
                if (orderId > lastId) {
                    lastId = orderId;
                }
            }

            int newOrderId = lastId + 1;
            itemOrder.setOrderId(newOrderId);

            Element newItemOrder = document.createElement("itemorder");

            newItemOrder.setAttribute("byUser", itemOrder.getCreatedBy().getUsername());
            newItemOrder.setAttribute("orderId", String.valueOf(itemOrder.getOrderId()));
            newItemOrder.setAttribute("date", itemOrder.getDate());
            newItemOrder.setAttribute("orderType", itemOrder.getOrderType());

            Element byUser = document.createElement("createdby");
            byUser.setTextContent(itemOrder.getCreatedBy().getUsername());

            Element orderId = document.createElement("orderId");
            orderId.setTextContent(String.valueOf(itemOrder.getOrderId()));

            Element date = document.createElement("date");
            date.setTextContent(itemOrder.getDate());

            Element orderType = document.createElement("orderType");
            orderType.setTextContent(itemOrder.getOrderType());

            newItemOrder.appendChild(byUser);
            newItemOrder.appendChild(orderId);
            newItemOrder.appendChild(date);
            newItemOrder.appendChild(orderType);

            rootElement.appendChild(newItemOrder);

            cleanXMLElement(rootElement);
            writeDOMToFile(rootElement, "InventoryManagement/src/server/res/itemorders.xml");
            return newOrderId;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static synchronized void removeStockUnits(int itemID, String batchNo, int qty) {
        try {
            Document document = getXMLDocument("InventoryManagement/src/server/res/items.xml");

            Element rootElement = document.getDocumentElement();

            NodeList itemNodes = rootElement.getElementsByTagName("item");
            for (int i = 0; i < itemNodes.getLength(); i++) {
                Element itemElement = (Element) itemNodes.item(i);
                int itemIdXML = Integer.parseInt(itemElement.getElementsByTagName("id").item(0).getTextContent());
                if (itemIdXML == itemID) {
                    NodeList stockNodes = itemElement.getElementsByTagName("stock");
                    for (int j = 0; j < stockNodes.getLength(); j++) {
                        Element stockElement = (Element) stockNodes.item(j);
                        String batchNoXML = stockElement.getElementsByTagName("batchNo").item(0).getTextContent();
                        if (batchNoXML.equals(batchNo)) {
                            int oldQty = Integer.parseInt(stockElement.getElementsByTagName("qty").item(0).getTextContent());
                            int newQty = oldQty - qty;
                            if (newQty < 0) {
                                System.out.println("Error: The quantity should not be less than 0.");
                                return;
                            } else if (newQty == 0) {
//                                Node pNode = stockElement.getParentNode();
//                                pNode.removeChild(stockElement); // remove the stock head
                                System.out.println("The stock quantity is already zero. No amount of units can be removed.");
                            }

                            stockElement.getElementsByTagName("qty").item(0).setTextContent(String.valueOf(newQty));
                            Element totalQtyElement = (Element) itemElement.getElementsByTagName("totalqty").item(0);
                            int totalQty = Integer.parseInt(totalQtyElement.getTextContent());
                            totalQtyElement.setTextContent(String.valueOf(totalQty - qty));
                            break;
                        }
                    }
                    break;
                }
            }

            cleanXMLElement(rootElement);
            writeDOMToFile(rootElement, "InventoryManagement/src/server/res/items.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                NodeList stockList = currentItem.getElementsByTagName("stock");
                for (int i = 0; i < stockList.getLength(); i++) { // todo: handle exception when item not have stocks (i.e., stocks -> stock -> [child tags empty]
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
                    String byUserName = currentElement.getElementsByTagName("createdby").item(0).getTextContent();

                    User createdBy = new User(byUserName);

                    return  new ItemOrder(createdBy, orderId,date,orderType);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    return null;
   }

   public static void addOrderDetail(OrderDetails orderDetail){
       try{
           Document document = getXMLDocument("InventoryManagement/src/server/res/orderdetails.xml");

           Element rootElement = document.getDocumentElement();

           int lastId = 0;
           NodeList orderDetailsNodes = rootElement.getElementsByTagName("orderdetail");
           for (int i = 0; i < orderDetailsNodes.getLength(); i++) {
               Element orderDetailElement = (Element) orderDetailsNodes.item(i);
               int orderId = Integer.parseInt(orderDetailElement.getElementsByTagName("itemorderid").item(0).getTextContent());
               if (orderId > lastId) {
                   lastId = orderId;
               }
           }

           int newItemOrderId = lastId + 1;
           orderDetail.setItemOrderID(newItemOrderId);

           Element newItemOrder = document.createElement("orderdetail");

//           newItemOrder.setAttribute("byUser", orderDetail.getCreatedBy().getUsername());
//           newItemOrder.setAttribute("orderId", String.valueOf(itemOrder.getOrderId()));
//           newItemOrder.setAttribute("date", itemOrder.getDate());
//           newItemOrder.setAttribute("orderType", itemOrder.getOrderType());

           Element itemorderid = document.createElement("itemorderid");
           itemorderid.setTextContent(String.valueOf(orderDetail.getItemOrderID()));

           Element itemid = document.createElement("itemid");
           itemid.setTextContent(String.valueOf(orderDetail.getItemID()));

           Element units = document.createElement("units");
           units.setTextContent(String.valueOf(orderDetail.getUnits()));

           Element batchNo = document.createElement("batchNo");
           batchNo.setTextContent(orderDetail.getBatchNo());

           Element unitPrice = document.createElement("unitPrice");
           unitPrice.setTextContent(String.valueOf(orderDetail.getUnitPrice()));

           Element supplier = document.createElement("supplier");
           supplier.setTextContent(orderDetail.getSupplier());

           newItemOrder.appendChild(itemorderid);
           newItemOrder.appendChild(itemid);
           newItemOrder.appendChild(units);
           newItemOrder.appendChild(batchNo);
           newItemOrder.appendChild(unitPrice);
           newItemOrder.appendChild(supplier);

           rootElement.appendChild(newItemOrder);

           cleanXMLElement(rootElement);
           writeDOMToFile(rootElement, "InventoryManagement/src/server/res/orderdetails.xml");

       } catch (Exception e) {
           throw new RuntimeException("Error adding order details", e);
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

    public static ArrayList<String> fetchListOfSuppliers() {
       ArrayList<String> suppliers = new ArrayList<>();
        try {
            Document xmlDocument = getXMLDocument("InventoryManagement/src/server/res/suppliers.xml");
            Element root = xmlDocument.getDocumentElement();
            NodeList suppliersNodeList = root.getElementsByTagName("supplier");

            for (int i=0; i<suppliersNodeList.getLength(); i++) {
                Element currentSupplier = (Element) suppliersNodeList.item(i);
                suppliers.add(currentSupplier.getTextContent());
            }
            return suppliers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
