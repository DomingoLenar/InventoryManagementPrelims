package server.model.query;

import server.model.XMLProcessing;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;
import utility.revision.Stock;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreatePurchaseOrder {
    public static void process(ItemOrder itemOrder, OrderDetails orderDetail, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        try {
            String orderDate = itemOrder.getDate();
            XMLProcessing.addItemOrder(itemOrder);

            ArrayList<OrderDetails> orderDetails = new ArrayList<>();
            orderDetails.add(orderDetail);
            for (int x = 0; x < orderDetails.size(); x++) {
                OrderDetails order = orderDetails.get(x);
                order.setBatchNo(order.getSupplier() + "_" + orderDate + "_" + order.getUnitPrice());
                XMLProcessing.addOrderDetails(order);

                int itemId = order.getItemID();
                String batchNo = order.getBatchNo();
                float cost = order.getUnitPrice();
                int qty = order.getUnits();
                float price = (float) (cost + (cost * .20));

                Stock newStock = new Stock(batchNo, cost, price, qty, order.getSupplier());

                XMLProcessing.addStock(itemId, newStock);
            }
            objectOutputStream.writeObject(true);
        } catch (Exception e) {
            objectOutputStream.writeObject(false);
            throw new RuntimeException("Error creation of purchase order in xml", e);
        }
    }


}
