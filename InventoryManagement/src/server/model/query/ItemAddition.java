package server.model.query;

import server.model.XMLProcessing;
import utility.Item;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ItemAddition {
    /**
     * Handles the addition of an item to the server.
     *
     * @param itemObject    The item object to be added.
     * @param objectOutputStream  The output stream for sending responses.
     * @throws IOException  If an I/O error occurs.
     */
    public static synchronized void process(Item itemObject, ObjectOutputStream objectOutputStream) throws IOException {
        try {
            boolean success = XMLProcessing.addItem(itemObject);
            objectOutputStream.writeBoolean(success);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
