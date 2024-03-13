package server.model.query;

import server.model.XMLProcessing;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateSalesInvoice {
    public static void process(ItemOrder itemOrder, OrderDetails orderDetail, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail);
        int orderID = XMLProcessing.addItemOrder(itemOrder);
        if (orderID == -1) {
            objectOutputStream.writeObject(false);
        } else {
            orderDetails.forEach(order -> {
                order.setItemOrderID(orderID);
            });
            orderDetails.forEach(XMLProcessing::addOrderDetails);
            orderDetails.forEach(order -> XMLProcessing.removeStockUnits(order.getItemID(), order.getBatchNo(), order.getUnits()));
            objectOutputStream.writeObject(true);
        }
    }
}
