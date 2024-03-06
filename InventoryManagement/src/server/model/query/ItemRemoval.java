package server.model.query;

import server.model.XMLProcessing;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ItemRemoval {
    /**
     * Handles the removal of an item from the server.
     *
     * @param id            The ID of the item to be removed.
     * @param objectOutputStream  The output stream for sending responses.
     * @throws IOException  If an I/O error occurs.
     */
    public static synchronized void process(int id, ObjectOutputStream objectOutputStream) throws IOException {
        try {
            boolean success = XMLProcessing.removeItem(id);
            objectOutputStream.writeBoolean(success);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
