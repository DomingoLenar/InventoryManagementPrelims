package server.model.query;

import server.model.XMLProcessing;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateSalesInvoice {

    public static void process(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        ItemOrder itemOrder = (ItemOrder)objectInputStream.readObject();
        ArrayList<OrderDetails> orderDetails = (ArrayList<OrderDetails>) objectInputStream.readObject();

        int orderID = XMLProcessing.addItemOrder(itemOrder);

        orderDetails.forEach(orderDetail -> {
            orderDetail.setItemOrderID(orderID);
        });

        orderDetails.forEach(XMLProcessing::addOrderDetail);
        orderDetails.forEach(orderDetail -> XMLProcessing.removeStockUnits(orderDetail.getItemID(),orderDetail.getBatchNo(), orderDetail.getUnits()));
        }


}
