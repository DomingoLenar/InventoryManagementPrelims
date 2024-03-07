package server.model.query;

import server.model.XMLProcessing;
import utility.ItemOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RequestSalesDashboard {
    public static void process(){

    }
    private static ArrayList<ItemOrder> getRecentlyAddedItems(){
        ArrayList<ItemOrder> recentlyAddedItems = new ArrayList<>();
        ArrayList<ItemOrder> allPurchaseOrders = XMLProcessing.fetchItemOrders("purchase");
        int length = allPurchaseOrders.size();

        for(int x = length; x > (length-5); x--){
            recentlyAddedItems.add(allPurchaseOrders.get(x));
        }

        return recentlyAddedItems;
    }

    private static float getRevenueToday(){
        float revenue;
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = localDate.format(formatter);

        ArrayList<ItemOrder> purchaseOrders = XMLProcessing.fetchItemOrders("sale");
        ArrayList<ItemOrder> salesToday = new ArrayList<>();

        for(int x=0; x<purchaseOrders.size();x++){
            ItemOrder currentOrder = purchaseOrders.get(x);
            if(currentOrder.getDate().equals(currentDate)){
                salesToday.add(currentOrder);
            }
        }

        revenue = (float) salesToday.stream().mapToDouble(ItemOrder::getPurchasePrice).sum();

        return revenue;
    }
}
