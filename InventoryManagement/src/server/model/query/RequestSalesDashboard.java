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
import java.util.Stack;

public class RequestSalesDashboard {
    public synchronized static void process(ObjectOutputStream objectOutputStream) throws IOException{

        ArrayList<String> yearlyRevenueCogs = getYearlyRevenueNCosts();
        objectOutputStream.writeObject(yearlyRevenueCogs);
        objectOutputStream.flush();

        float[] revenueAndCost = getRevenueNCogsToday();
        objectOutputStream.writeObject(revenueAndCost);
        objectOutputStream.flush();

        Stack<Item> recentlyAddedItems = getRecentlyAddedItems();
        objectOutputStream.writeObject(recentlyAddedItems);
        objectOutputStream.flush();

    }

    static ArrayList<String> getYearlyRevenueNCosts(){
        ArrayList<String> revenueNCosts = new ArrayList<>();
        ArrayList<ArrayList<ItemOrder>> byMonth = new ArrayList<>();

        String currentDate = getCurrentDate();
        String[] yMD = currentDate.split("-");

        ArrayList<ItemOrder> allSalesOrder = XMLProcessing.fetchItemOrders("sales");

        for(int x=1; x<=12; x++){
            String month = null;
            if(x < 10){
                month = "0"+x;
            }else{
                month = String.valueOf(x);
            }
            String[] yNM = {yMD[0],month};
            revenueNCosts.add(getRevenueCogsByMonth(yNM, allSalesOrder));
        }

        return revenueNCosts;
    }

    public static String getRevenueCogsByMonth(String[] yearAndMonth, ArrayList<ItemOrder> allSalesOrder){
        float[] initialRevenueAndCogs = {0,0};

        ArrayList<ItemOrder> ordersByMonthGiven = new ArrayList<>();

        for(int x =0; x<allSalesOrder.size(); x++){
            ItemOrder currentOrder = allSalesOrder.get(x);
            String[] orderDate = currentOrder.getDate().split("-");
            System.out.print(orderDate[0]+","+orderDate[1]+" "+yearAndMonth[0]+","+yearAndMonth[1]+" ");
            System.out.println(orderDate[0].equals(yearAndMonth[0])&&orderDate[1].equals(yearAndMonth[1]));
            if(orderDate[0].equals(yearAndMonth[0])&&orderDate[1].equals(yearAndMonth[1])){
                ordersByMonthGiven.add(currentOrder);
            }
        }

        System.out.println(ordersByMonthGiven.size());
        if(ordersByMonthGiven.size() != 0) {
            for (int x = 0; x < ordersByMonthGiven.size(); x++) {
                ItemOrder order = ordersByMonthGiven.get(x);
                ArrayList<OrderDetails> details = XMLProcessing.fetchOrderDetails(order.getOrderId());
                float revenue = (float) details.stream().mapToDouble(orderDetail -> orderDetail.getUnits() * orderDetail.getUnitPrice()).sum();
                float cost = (float) details.stream().mapToDouble(orderDetail -> {

                    String[] batchNo = orderDetail.getBatchNo().split("_");
                    float costOfUnit = Float.parseFloat(batchNo[batchNo.length-1]);

                    return orderDetail.getUnits() * costOfUnit;
                }).sum();
                initialRevenueAndCogs[0] += revenue;
                initialRevenueAndCogs[1] += cost;
            }
        }


        return initialRevenueAndCogs[0]+","+initialRevenueAndCogs[1];
    }

    private static Stack<Item> getRecentlyAddedItems(){
        Stack<Item> recentlyAddedItems = new Stack<>();
        ArrayList<ItemOrder> allPurchaseOrders = XMLProcessing.fetchItemOrders("purchase");
        int length = allPurchaseOrders.size();

        int maxIndex = Math.min(5, length);

        for(int x = length-1; x > (length-maxIndex); x--){
            int orderID = allPurchaseOrders.get(x).getOrderId();
            ArrayList<OrderDetails> orderDetails = XMLProcessing.fetchOrderDetails(orderID);
            recentlyAddedItems.add(XMLProcessing.fetchItem(orderDetails.get(0).getItemID(), false));
        }

        return recentlyAddedItems;
    }

    private static String getCurrentDate(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = localDate.format(formatter);
        return currentDate;
    }

    private static ArrayList<ItemOrder> getSalesToday(){
        String currentDate = getCurrentDate();

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

    private static float[] getRevenueNCogsToday(){
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

        ArrayList<ItemOrder> salesToday = getSalesToday();

        
        for(int x = 0; x < salesToday.size(); x++){
            int itemOrderID = salesToday.get(x).getOrderId();
            ArrayList<OrderDetails> orderDetails = XMLProcessing.fetchOrderDetails(itemOrderID);
            revenue += (float) orderDetails.stream().mapToDouble(orderDetail -> orderDetail.getUnits() * orderDetail.getUnitPrice()).sum();
        }


        return revenue;
    }
}
