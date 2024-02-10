package client.models;

import utility.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ItemManagementModel {
    private final Socket socket;
    private final ObjectOutputStream outputStream;

    /**
     * Constructs a new ItemManagementModel with the specified socket and output stream.
     *
     * @param socket        The socket connected to the server.
     * @param outputStream  The output stream used for communication with the server.
     */
    public ItemManagementModel(Socket socket, ObjectOutputStream outputStream) {
        this.socket = socket;
        this.outputStream = outputStream;
    }

    /**
     * Sends an action to the server.
     *
     * @param action The action to be sent.
     */
    public void sendAction(String action) {
        try {
            outputStream.writeObject(action);
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
     * @param id    The ID of the item.
     * @param price The price of the item.
     * @return True if the item is successfully added; false otherwise.
     */
    public boolean addItems(String name, int qty, String type, int id, int price) {
        try {
            Item newItem = new Item(name, qty, type, id, price);

            sendAction("addItem");

            outputStream.writeObject(newItem);
            System.out.println("Item: " + newItem.getName() + " addition has been sent to the server");

            try (ObjectInputStream oos = new ObjectInputStream(socket.getInputStream())) {
                boolean addItemSuccess = (boolean) oos.readObject();
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
     * @param id The ID of the item to be removed.
     * @return True if the item is successfully removed; false otherwise.
     */
    public boolean removeItem(int id) {
        try {
            sendAction("removeItem");
            outputStream.writeInt(id);
            outputStream.flush();

            System.out.println("Item removal request has been sent to the server.");

            try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                boolean removalSuccess = ois.readBoolean();
                System.out.println("Server response: " + removalSuccess);
                return removalSuccess;
            }

        } catch (IOException e) {
            throw new RuntimeException("Error removing item", e);
        }
    }

}
