package client.common.models;

import utility.Item;
import utility.ItemOrder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ItemManagementModel {
    /**
     * Sends an action to the server.
     * @param action The action to be sent.
     */
    public static void sendAction(String action, ObjectOutputStream oos) {
        try {
            oos.writeUTF(action);
            oos.flush();
            System.out.println(action + " sent to server");
        } catch (IOException e) {
            throw new RuntimeException("Error sending action to server", e);
        }
    }

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
    public static void addItems(String name, int qty, String type, int itemId, int price, ObjectOutputStream oOs, ObjectInputStream oIs) {

        try {

            Item newItem = new Item(name, qty, type, itemId, price);

            sendAction("addItem", oOs);

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

    public static void addItemOrders(int id, String date, int price, String userType, int itemID, String byUser,  ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {

            ItemOrder itemOrder = new ItemOrder(id, date, price, userType, itemID, byUser);

            sendAction("addItemOrder", oOs);

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
     * @param itemId The ID of the item to be removed.
     * @return True if the item is successfully removed; false otherwise.
     */
    public static boolean removeItem(int itemId, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            sendAction("removeItem", oOs);
            oOs.writeInt(itemId);
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

    public static ArrayList<Item> fetchListOfItems (ObjectOutputStream oOs, ObjectInputStream oIs){

        try {

            sendAction("fetchItems", oOs);

            try  {
                ArrayList<Item> listOfItems = (ArrayList<Item>) oIs.readObject();
                System.out.println("List of items have been fetched.");
                return listOfItems;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ItemOrder> fetchItemOrders (ObjectOutputStream oOs, ObjectInputStream oIs){

        try {

            sendAction("fetchItemOrders", oOs);
            sendAction("none", oOs);
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
