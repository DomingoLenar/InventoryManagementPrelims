package client.common.models;

import utility.ItemOrder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AddItemOrders {

    /**
     * Sends a request to the server to add an item order.
     *
     * @param id        The ID of the item order.
     * @param date      The date of the item order.
     * @param price     The price of the item order.
     * @param orderType The type of the order.
     * @param itemID    The ID of the item.
     * @param byUser    The user who is adding the item order.
     * @param qty       The quantity of the item order.
     * @param oOs       The ObjectOutputStream for sending data to the server.
     * @param oIs       The ObjectInputStream for receiving data from the server.
     * @throws RuntimeException If an IOException occurs during the process.
     */

    public static void process(int id, String date, float price, String orderType, int itemID, String byUser, int qty, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {

            ItemOrder itemOrder = new ItemOrder(id, date, price, orderType, itemID, byUser, qty);

            String action = "addItemOrder";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

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
}
