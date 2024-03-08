package server.model.query;

import server.model.XMLProcessing;
import utility.revision.Item;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class RequestPurchaseDashboard {

    public static void process(ObjectOutputStream objectOutputStream) throws IOException {

        objectOutputStream.writeObject(getLowStockItems());
        objectOutputStream.flush();

        objectOutputStream.writeObject(getUnitsSold());
        objectOutputStream.flush();

    }

    public static List<Item> getLowStockItems(){
        Stack<Item> items = XMLProcessing.fetchItems();

        items.sort(Comparator.comparingInt(Item::getTotalQty));
        int highestIndex = Math.min(5, items.size());
        List<Item> lowestItems = items.subList(0,highestIndex);
        return lowestItems;
    }

    public static int[] getUnitsSold(){
        int[] unitsSAndMaxUnits = {0, 0};

        ArrayList<ItemOrder> saleOrders = XMLProcessing.fetchItemOrders("sales");
        for(int x=0;x<saleOrders.size();x++){
            ArrayList<OrderDetails> orderDetails = XMLProcessing.fetchOrderDetails(saleOrders.get(x).getOrderId());
            unitsSAndMaxUnits[0]+= orderDetails.stream().mapToInt(OrderDetails::getUnits).sum();
        }

        Stack<Item> items = XMLProcessing.fetchItems();
        unitsSAndMaxUnits[1] += items.stream().mapToInt(Item::getTotalQty).sum();
        return unitsSAndMaxUnits;
    }
}
