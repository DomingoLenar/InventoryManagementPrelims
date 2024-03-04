package client.common.models;

import client.api.ClientApi;
import utility.Item;
import utility.ItemOrder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;

// TODO: separation of intent, fix other todos, and bugs!!
@Deprecated
public class ItemManagementModel {
    /**
     * Sends an action to the server.
     * @param action The action to be sent.
     */

    /**
     * Adds an item to the inventory.
     *
     * @param name  The name of the item.
     * @param qty   The quantity of the item.
     * @param type  The type of the item.
     * @param itemId    The ID of the item.
     * @param price The price of the item.
     * @return True if the item is successfully added; false otherwise.
     */
    public static void addItems(String name, int qty, String type, int itemId, float price, ObjectOutputStream oOs, ObjectInputStream oIs) {

        try {

            Item newItem = new Item(name, qty, type, itemId, price);

            ClientApi.sendAction("addItem", oOs);

            oOs.writeObject(newItem);
            oOs.flush();
            System.out.println("Item: " + newItem.getName() + " addition has been sent to the server");

            try  {
                boolean addItemSuccess = oIs.readBoolean();
                System.out.println("Server Response: " + addItemSuccess);
//                return addItemSuccess;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error adding item", e);
        }
    }

    public static void addItemOrders(int id, String date, float price, String orderType, int itemID, String byUser, int qty,  ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {

            ItemOrder itemOrder = new ItemOrder(id, date, price, orderType, itemID, byUser, qty);

            ClientApi.sendAction("addItemOrder", oOs);

            oOs.writeObject(itemOrder);
            oOs.flush();
            System.out.println("Item addition has been sent to the server by: " + byUser);

            try  {
                boolean addItemSuccess = oIs.readBoolean();
                System.out.println("Server Response: " + addItemSuccess);
//                return addItemSuccess;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error adding item", e);
        }
    }

    /**
     * Removes an item from the inventory based on its ID.
     *
     * @param id The ID of the item to be removed.
     * @return True if the item is successfully removed; false otherwise.
     */
    public static boolean removeItem(int id, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            ClientApi.sendAction("removeItem", oOs);
            oOs.writeInt(id);
            oOs.flush();

            System.out.println("Item removal request has been sent to the server.");

            try  {
                boolean removalSuccess = oIs.readBoolean();
                System.out.println("Server response: " + removalSuccess);
                return removalSuccess;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error removing item", e);
        }
    }

    public static boolean removeItemOrder(int itemOrderID, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            ClientApi.sendAction("removeItemOrder", oOs);

            oOs.writeInt(itemOrderID);
            oOs.flush();

            System.out.println("Item order removal request has been sent to the server");

            try  {
                boolean removalSuccess = oIs.readBoolean();
                System.out.println("Server response: " + removalSuccess);
                return removalSuccess;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stack<Item> fetchItemsByUserType(String userType, ObjectOutputStream oOs, ObjectInputStream oIs){
        try {
            ClientApi.sendAction("fetchItems", oOs);

            try  {
                Stack<Item> listOfItems = (Stack<Item>) oIs.readObject();
                System.out.println("List of items have been fetched.");
                return listOfItems;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ItemOrder> fetchItemOrdersByUserType (String userType , ObjectOutputStream oOs, ObjectInputStream oIs){
        try {
            ClientApi.sendAction("fetchItemOrders", oOs);
            ClientApi.sendAction(userType, oOs);
            try  {
                ArrayList<ItemOrder> listOfItems = (ArrayList<ItemOrder>) oIs.readObject();
                System.out.println("List of items have been fetched.");
                return listOfItems;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
