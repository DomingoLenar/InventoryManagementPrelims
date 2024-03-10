package client.sales.models;

import client.api.ClientApi;
import utility.User;
import utility.revision.Item;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalesCreateCustomerOrderModel {
    public SalesCreateCustomerOrderModel() {

    }

    // TODO: incomplete implementation
    public boolean OnGenerate(String prod, float prc, int qnty_bought, String createdBy, String dateCreated, String orderType, int itemId, String type, String batchNo, int qnty_stock, String supplier, ObjectInputStream oIs, ObjectOutputStream oOs) {
        User user = new User(createdBy);
        Item item = new Item(prod,itemId ,type);
        ItemOrder itemOrder = new ItemOrder(user, -1, dateCreated, orderType);
        OrderDetails orderDetails = new OrderDetails(itemOrder.getOrderId(), item.getId(), qnty_bought, batchNo, prc, supplier);
        try {
            ClientApi.sendAction("createSalesInvoice", oOs);

            oOs.writeObject(itemOrder);
            oOs.writeObject(orderDetails);

            try {
                boolean generateSuccess = (boolean) oIs.readObject();
                System.out.println("Server response: " + generateSuccess);
                return generateSuccess;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error changing password", e);
        }
    }
}
