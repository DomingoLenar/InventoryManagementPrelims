package client.common.models;

import utility.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AddItems {
    /**
     * Adds an item to the inventory.
     *
     * @param name   The name of the item.
     * @param qty    The quantity of the item.
     * @param type   The type of the item.
     * @param itemId The ID of the item.
     * @param price  The price of the item.
     * @return True if the item is successfully added; false otherwise.
     */
    public static void process(String name, int qty, String type, int itemId, float price, ObjectOutputStream oOs, ObjectInputStream oIs) {

        try {

            Item newItem = new Item(name, qty, type, itemId, price);

            String action = "addItem";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

            oOs.writeObject(newItem);
            oOs.flush();
            System.out.println("Item: " + newItem.getName() + " addition has been sent to the server");


        } catch (IOException e) {
            throw new RuntimeException("Error adding item", e);
        }
    }
}
