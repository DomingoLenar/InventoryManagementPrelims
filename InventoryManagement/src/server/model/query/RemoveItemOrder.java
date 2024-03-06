package server.model.query;

import server.model.XMLProcessing;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class RemoveItemOrder {
    public static void process(int itemOrderID, ObjectOutputStream objectOutputStream) {
        try{
            boolean success = XMLProcessing.removeItemOrder(itemOrderID);
            objectOutputStream.writeBoolean(success);
            objectOutputStream.flush();
        } catch (IOException ioException){
            throw new RuntimeException("Error removing item order", ioException);
        }
    }
}
