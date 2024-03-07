package client.common.models.query;

import utility.User;
import utility.revision.ItemOrder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AddItemOrders {

    /**
     * Sends a request to the server to add an item order.
     *
     * @param createdBy     The user who is adding the item order.
     * @param date          The date of the item order.
     * @param orderType     The type of the order.
     * @param itemId        The ID of the item.
     * @param batchNo       The batch number of the item order.
     * @param supplier      The supplier of the item order.
     * @param price         The price of the item order.
     * @param qty           The quantity of the item order.
     * @param oOs           The ObjectOutputStream for sending data to the server.
     * @param oIs           The ObjectInputStream for receiving data from the server.
     * @throws RuntimeException If an IOException occurs during the process.
     */

    public static void process(User createdBy, String date, String orderType, int itemId, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {

            ItemOrder itemOrder = new ItemOrder(createdBy, itemId, date,orderType );

            String action = "addItemOrder";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

            oOs.writeObject(itemOrder);
            oOs.flush();
            System.out.println("Item addition has been sent to the server by: " + createdBy.getUsername());

            try  {
                boolean addItemSuccess = oIs.readBoolean();
                System.out.println("Server Response: " + addItemSuccess);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error adding item", e);
        }
    }
}
