package client.purchaser.models;

import client.api.ClientApi;
import utility.User;
import utility.revision.Item;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PurchaserCreatePurchaseOrderModel {

    public boolean OnCreate(String productName, float purchasePrice, int qnty_bought, String supplier, String type, String createdBy,
                            String dateCreated, int itemId, String orderType, ObjectOutputStream oOs, ObjectInputStream oIs) {
        User user = new User(createdBy);
        Item item = new Item(productName,itemId ,type);
        ItemOrder itemOrder = new ItemOrder(user, -1, dateCreated, orderType);
        OrderDetails orderDetails = new OrderDetails(itemOrder.getOrderId(), item.getId(), qnty_bought, null, purchasePrice, supplier);
        try {
            ClientApi.sendAction("createPurchaseOrder", oOs);

            oOs.writeObject(itemOrder);
            oOs.writeObject(orderDetails);
            try {
                boolean creationSuccess = (boolean) oIs.readObject();
                System.out.println("Server response: " + creationSuccess);
                return creationSuccess;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Failed creation of purchase order", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error sending a request", e);
        }
    }

    public ArrayList<String> fetchListOfSuppliers(ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            ClientApi.sendAction("fetchListOfSuppliers", oOs);
            try {
                ArrayList<String> suppliers = (ArrayList<String>) oIs.readObject();
                return suppliers;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error fetching suppliers",e);
        }
    }

}
