package client.common.models.query;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RemoveItemOrder {

    /**
     * Sends a request to the server to remove an item order.
     *
     * @param itemOrderID The ID of the item order to be removed.
     * @param oOs         The ObjectOutputStream for sending data to the server.
     * @param oIs         The ObjectInputStream for receiving data from the server.
     * @throws RuntimeException If an IOException occurs during the process.
     */

    public static void process(int itemOrderID, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            String action = "removeItemOrder";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

            oOs.writeInt(itemOrderID);
            oOs.flush();

            System.out.println("Item order removal request has been sent to the server");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
