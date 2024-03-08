package server.model.query;

import server.model.XMLProcessing;
import utility.revision.Item;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RequestSalesDashboard {
    public static void process(){

    }
    private static ArrayList<String> getYearlyRevenueNCosts(){
        ArrayList<String> revenueNCosts = new ArrayList<>();

        ArrayList<ItemOrder> allPurchaseOrder = XMLProcessing.fetchItemOrders("sales");

        return revenueNCosts;
    }
    private static ArrayList<Item> getRecentlyAddedItems(){
        ArrayList<Item> recentlyAddedItems = new ArrayList<>();
        ArrayList<ItemOrder> allPurchaseOrders = XMLProcessing.fetchItemOrders("purchase");
        int length = allPurchaseOrders.size();

        for(int x = length; x > (length-5); x--){
            int orderID = allPurchaseOrders.get(x).getOrderId();
            ArrayList<OrderDetails> orderDetails = XMLProcessing.fetchOrderDetails(orderID);
            recentlyAddedItems.add(XMLProcessing.fetchItem(orderDetails.get(0).getItemID(), false));
        }

        return recentlyAddedItems;
    }

    private static ArrayList<ItemOrder> getSalesToday(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = localDate.format(formatter);

        ArrayList<ItemOrder> itemOrderList = XMLProcessing.fetchItemOrders("sales");
        ArrayList<ItemOrder> salesToday = new ArrayList<>();

        for(int x=0;x<itemOrderList.size();x++){
            ItemOrder currentOrder = itemOrderList.get(x);
            if(currentOrder.getDate().equals(currentDate)){
                salesToday.add(currentOrder);
            }
        }

        return salesToday;
    }

    private static float[] getRevenueNCostToday(){
        float revenue = getRevenueToday();
        float cogs = getCostOfGoodsSoldToday();
        return new float[]{revenue, cogs};
    }

    private static float getCostOfGoodsSoldToday() {
        float cogs = 0;

        ArrayList<ItemOrder> salesToday = getSalesToday();

        for(int x = 0; x<salesToday.size();x++){
            int itemOrderID = salesToday.get(x).getOrderId();
            ArrayList<OrderDetails> orderDetails = XMLProcessing.fetchOrderDetails(itemOrderID);
            for(int y = 0; y<orderDetails.size();y++){
                OrderDetails orderDetail = orderDetails.get(y);
                int itemId = orderDetail.getItemID();
                int units  = orderDetail.getUnits();
                String batchNo = orderDetail.getBatchNo();
                Item currentItem = XMLProcessing.fetchItem(itemId, true);
                cogs += units * (currentItem.getStock(batchNo).getCost());
            }
        }

        return cogs;
    }

    private static float getRevenueToday(){
        float revenue = 0;
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = localDate.format(formatter);

        ArrayList<ItemOrder> salesToday = getSalesToday();

        for(int x=0; x<purchaseOrders.size();x++){
            ItemOrder currentOrder = purchaseOrders.get(x);
            if(currentOrder.getDate().equals(currentDate)){
                salesToday.add(currentOrder);
            }
        }
        
        for(int x = 0; x < salesToday.size(); x++){
            int itemOrderID = salesToday.get(x).getOrderId();
            ArrayList<OrderDetails> orderDetails = XMLProcessing.fetchOrderDetails(itemOrderID);
            revenue += (float) orderDetails.stream().mapToDouble(OrderDetails::getUnitPrice).sum();
        }


        return revenue;
    }
}
