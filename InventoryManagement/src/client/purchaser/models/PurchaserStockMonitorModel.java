package client.purchaser.models;

import client.api.ClientApi;
import utility.revision.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class PurchaserStockMonitorModel {

    public PurchaserStockMonitorModel(){}

    public Stack<Item> fetchItems(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        try {
            ClientApi.sendAction("fetchItems", objectOutputStream);

            try {
                Stack<Item> itemStack = (Stack<Item>) objectInputStream.readObject();
                return itemStack;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException ioException) {
            throw new RuntimeException("Error Fetching Items", ioException);
        }
    }

}
