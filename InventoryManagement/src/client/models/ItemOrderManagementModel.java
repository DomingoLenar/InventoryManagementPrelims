package client.models;

import utility.ItemOrder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This is a client-side model that is used for managing item orders (addition/fetching).
 */
public class ItemOrderManagementModel {

    /**
     * This method is used to add a new item order and waits for a boolean
     * from the server that confirm whether the addition is successful or not.
     * @param id Item associated with the order
     * @param date Date when the item was made
     * @param purPrice Purchase price of the item
     * @param type The type of item
     * @param itemId ID of the Item
     * @param username Username of the user executing the operation
     * @return a boolean
     */
    public static boolean addItemOrder (int id, String date, float purPrice, String type, int itemId, String username, ObjectOutputStream oOs, ObjectInputStream oIs ){

        try {

            ItemOrder newItemOrder = new ItemOrder(id, date, purPrice, type, itemId, username);

            ItemManagementModel.sendAction("addItemOrder", oOs);

            oOs.writeObject(newItemOrder);
            oOs.flush();
            System.out.println("Item Order: "+newItemOrder.getItemId() + " addition has been sent to the server...");
            try  {
                boolean success = (boolean) oIs.readObject();
                System.out.println("Server response: " + success);
                return success;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//end of addItemOrder

    /**
     * This method is used to fetch a list of item orders using the sendAction()
     * from the ItemManagementModel. It returns an ArrayList of ItemOrder objects.
     * @param date The date associated with the item orders to be fetched
     * @return an ArrayList "listOfItemOrders"
     */
    public static ArrayList<ItemOrder> fetchItemOrder (String date, ObjectOutputStream oOs, ObjectInputStream oIs){

        try {
            ItemManagementModel.sendAction("fetchItemOrders",oOs );

            try {
                ArrayList<ItemOrder> listOfItemOrders = (ArrayList<ItemOrder>) oIs.readObject();
                System.out.println(" List of item orders have been fetched.");
                return listOfItemOrders;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//end of fetchItemOrder

}//end of class

