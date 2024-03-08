package server.model.query;

import server.model.XMLProcessing;
import utility.revision.Item;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RequestSalesDashboard {
    public static void process(ObjectOutputStream objectOutputStream) throws IOException {
        float[] revenueAndCost = getRevenueNCostToday();
        objectOutputStream.writeObject(revenueAndCost);
        objectOutputStream.flush();
    }

    private static ArrayList<String> getYearlyRevenueNCosts(){
        ArrayList<String> revenueNCosts = new ArrayList<>();

        ArrayList<ItemOrder> allPurchaseOrder = XMLProcessing.fetchItemOrders("sales");

        return revenueNCosts;
    }

    public static float[] getRevenueCogsByMonth(String[] yearAndMonth, ArrayList<ItemOrder> allSalesOrder){
        float[] revenueAndCogs = {0,0};

        ArrayList<ItemOrder> ordersByMonthGiven = new ArrayList<>();

        for(int x =0; x<allSalesOrder.size(); x++){
            ItemOrder currentOrder = allSalesOrder.get(x);
            String[] orderDate = currentOrder.getDate().split("-");
            if(orderDate[0].equals(yearAndMonth[0])&&orderDate[1].equals(yearAndMonth[1])){
                ordersByMonthGiven.add(currentOrder);
            }
        }

        for(int x=0; x<=ordersByMonthGiven.size();x++){
            ItemOrder order = ordersByMonthGiven.get(x);
            ArrayList<OrderDetails> details = XMLProcessing.fetchOrderDetails(order.getOrderId());
            float revenue = (float) details.stream().mapToDouble(orderDetail -> orderDetail.getUnits()*orderDetail.getUnitPrice()).sum();
            float cost = (float) details.stream().mapToDouble(orderDetail -> {

                String[] batchNo = orderDetail.getBatchNo().split("_");
                float costOfUnit = Float.parseFloat(batchNo[batchNo.length]);

                return orderDetail.getUnits()*costOfUnit;
            }).sum();
            revenueAndCogs[0] += revenue;
            revenueAndCogs[1] += cost;
        }


        return revenueAndCogs;
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
