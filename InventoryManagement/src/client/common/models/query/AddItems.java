package client.common.models.query;

import utility.revision.Item;
import utility.revision.Stock;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class AddItems {
    /**
     * Adds an item to the inventory.
     *
     * @param name   The name of the item.
     * @param id    The id of the item.
     * @param totalQty The quantity of the item.
     * @param type   The type of the item.
     * @param stocks The stocks of the item.
     * @return True if the item is successfully added; false otherwise.
     */
    public static void process(String name, int id, int totalQty, String type, LinkedList<Stock> stocks, ObjectOutputStream oOs, ObjectInputStream oIs) {

        try {

            Item newItem = new Item(name, id, totalQty, type, stocks);

            String action = "addItem";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

            oOs.writeObject(newItem);
            oOs.flush();
            System.out.println("Item: " + newItem.getName() + " addition has been sent to the server");

            try  {
                boolean addItemOrderSuccess = oIs.readBoolean();
                System.out.println("Server Response: " + addItemOrderSuccess);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error adding item", e);
        }
    }
}
