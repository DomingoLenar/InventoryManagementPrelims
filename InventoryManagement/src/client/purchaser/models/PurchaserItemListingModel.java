package client.purchaser.models;

import client.api.ClientApi;
import utility.revision.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PurchaserItemListingModel {
    public PurchaserItemListingModel(){}

    public boolean OnCreate(ObjectInputStream oIs, ObjectOutputStream oOs, String prodName) {
        Item item = new Item(prodName, -1, "null"); // todo: temporary set as null
        try {
            ClientApi.sendAction("addNewItem", oOs);

            oOs.writeObject(item);
            oOs.flush();
            try {
                boolean success = (boolean) oIs.readObject();
                return success;
            } catch (IOException | ClassNotFoundException e ) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error adding a new item", e);
        }
    }
}
