package client.common.models.query;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RemoveItem {
    /**
     * Removes an item from the inventory based on its ID.
     *
     * @param id The ID of the item to be removed.
     */
    public static void process(int id, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            String action = "removeItem";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

            oOs.writeInt(id);
            oOs.flush();

            System.out.println("Item removal request has been sent to the server.");


        } catch (IOException e) {
            throw new RuntimeException("Error removing item", e);
        }
    }

}
