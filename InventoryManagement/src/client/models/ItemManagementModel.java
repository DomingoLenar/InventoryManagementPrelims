package client.models;

import utility.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ItemManagementModel {


    /**
     * Sends an action to the server.
     *
     * @param action The action to be sent.
     */
    public void sendAction(String action, ObjectOutputStream oos) {
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
    public boolean addItems(String name, int qty, String type, int itemId, int price, Socket clientSocket) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            Item newItem = new Item(name, qty, type, itemId, price);

            sendAction("addItem", oos);

            oos.writeObject(newItem);
            System.out.println("Item: " + newItem.getName() + " addition has been sent to the server");

            try (ObjectInputStream oIs = new ObjectInputStream(clientSocket.getInputStream())) {
                boolean addItemSuccess = (boolean) oIs.readObject();
                System.out.println("Server Response: " + addItemSuccess);
                return addItemSuccess;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error adding item", e);
        }
    }

    /**
     * Removes an item from the inventory based on its ID.
     *
     * @param itemId The ID of the item to be removed.
     * @return True if the item is successfully removed; false otherwise.
     */
    public boolean removeItem(int itemId, Socket clientSocket) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            sendAction("removeItem", oos);
            oos.writeInt(itemId);
            oos.flush();

            System.out.println("Item removal request has been sent to the server.");

            try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
                boolean removalSuccess = ois.readBoolean();
                System.out.println("Server response: " + removalSuccess);
                return removalSuccess;
            }

        } catch (IOException e) {
            throw new RuntimeException("Error removing item", e);
        }
    }

}
