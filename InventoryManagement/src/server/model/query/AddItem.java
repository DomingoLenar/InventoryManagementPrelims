package server.model.query;

import server.model.XMLProcessing;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AddItem {

    public static void process(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        ItemOrder order = (ItemOrder)objectInputStream.readObject();
        String orderDate = order.getDate();
        XMLProcessing.addItemOrder(order);

        ArrayList<OrderDetails> orderDetails = (ArrayList<OrderDetails>) objectInputStream.readObject();
        for(int x = 0; x<orderDetails.size();x++){
            OrderDetails orderDetail = orderDetails.get(x);
            orderDetail.setBatchNo(orderDetail.getSupplier()+"_"+orderDate+"_"+orderDetail.getUnitPrice());
            XMLProcessing.addOrderDetail(orderDetail);

            int itemId = orderDetail.getItemID();

            
        }

    }


}
