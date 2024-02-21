package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.common.models.ItemManagementModel;
import client.sales.views.SalesDashboardView;
import utility.Item;
import utility.ItemOrder;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class SalesDashboardController {
    InventoryManagementController inventoryManagementController;
    SalesDashboardView salesDashboardView;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public SalesDashboardController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        salesDashboardView = new SalesDashboardView();

        objectInputStream = oIs;
        objectOutputStream = oOs;

    }

    public void initComponents() {

        // populate the table
        ArrayList<ItemOrder> listOfSalesItemOrders = ItemManagementModel.fetchItemOrdersByUserType("sales", objectOutputStream, objectInputStream);

        ArrayList<Float> revenueForJan = new ArrayList<>();
        ArrayList<Float> revenueForFeb = new ArrayList<>();
        ArrayList<Float> revenueForMar = new ArrayList<>();
        ArrayList<Float> revenueForApr = new ArrayList<>();
        ArrayList<Float> revenueForMay = new ArrayList<>();
        ArrayList<Float> revenueForJun = new ArrayList<>();
        ArrayList<Float> revenueForJuly = new ArrayList<>();
        ArrayList<Float> revenueForAug = new ArrayList<>();
        ArrayList<Float> revenueForSept = new ArrayList<>();
        ArrayList<Float> revenueForOct = new ArrayList<>();
        ArrayList<Float> revenueForNov = new ArrayList<>();
        ArrayList<Float> revenueForDec = new ArrayList<>();

        for (int i=0; i< listOfSalesItemOrders.size(); i++) {
            ItemOrder itemOrder = listOfSalesItemOrders.get(i);
            String date = itemOrder.getDate();
            if (date.contains("2024-01")){
                revenueForJan.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-02")){
                revenueForFeb.add(itemOrder.getPurchasePrice());

            }
            else if (date.contains("2024-03")){
                revenueForMar.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-04")){
                revenueForApr.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-05")){
                revenueForMay.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-06")){
                revenueForJun.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-07")){
                revenueForJuly.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-08")){
                revenueForAug.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-09")){
                revenueForSept.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-10")){
                revenueForOct.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-11")){
                revenueForNov.add(itemOrder.getPurchasePrice());
            }
            else if (date.contains("2024-12")){
                revenueForDec.add(itemOrder.getPurchasePrice());
            }
        }

        ArrayList<ArrayList<Float>> revenuePerMonth = new ArrayList<>();
        revenuePerMonth.add(revenueForJan);
        revenuePerMonth.add(revenueForFeb);
        revenuePerMonth.add(revenueForMar);
        revenuePerMonth.add(revenueForApr);
        revenuePerMonth.add(revenueForMay);
        revenuePerMonth.add(revenueForJun);
        revenuePerMonth.add(revenueForJuly);
        revenuePerMonth.add(revenueForAug);
        revenuePerMonth.add(revenueForSept);
        revenuePerMonth.add(revenueForOct);
        revenuePerMonth.add(revenueForNov);
        revenuePerMonth.add(revenueForDec);

        ArrayList<Float> totalRevenuePerMonth = new ArrayList<>();

        for (int i=0; i<revenuePerMonth.size(); i++) {
            ArrayList<Float> month = revenuePerMonth.get(i);
            float totalRevenue = 0;

            for (int j=0; j< month.size(); j++) {
                totalRevenue += month.get(j);
            }

            totalRevenuePerMonth.add(totalRevenue);
        }

        ArrayList<ItemOrder> listOfPurchaseItemOrders = ItemManagementModel.fetchItemOrdersByUserType("purchase", objectOutputStream, objectInputStream);

        ArrayList<Float> costForJan = new ArrayList<>();
        ArrayList<Float> costForFeb = new ArrayList<>();
        ArrayList<Float> costForMar = new ArrayList<>();
        ArrayList<Float> costForApr = new ArrayList<>();
        ArrayList<Float> costForMay = new ArrayList<>();
        ArrayList<Float> costForJun = new ArrayList<>();
        ArrayList<Float> costForJuly = new ArrayList<>();
        ArrayList<Float> costForAug = new ArrayList<>();
        ArrayList<Float> costForSept = new ArrayList<>();
        ArrayList<Float> costForOct = new ArrayList<>();
        ArrayList<Float> costForNov = new ArrayList<>();
        ArrayList<Float> costForDec = new ArrayList<>();

        for (int i=0; i< listOfPurchaseItemOrders.size(); i++) {
            ItemOrder itemOrder = listOfPurchaseItemOrders.get(i);
            String date = itemOrder.getDate();
            if (date.contains("2024-01"))costForJan.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-02"))costForFeb.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-03"))costForMar.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-04"))costForApr.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-05"))costForMay.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-06"))costForJun.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-07"))costForJuly.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-08"))costForAug.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-09"))costForSept.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-10"))costForOct.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-11"))costForNov.add(itemOrder.getPurchasePrice());
            else if (date.contains("2024-12"))costForDec.add(itemOrder.getPurchasePrice());
        }

        ArrayList<ArrayList<Float>> costPerMonth = new ArrayList<>();
        costPerMonth.add(costForJan);
        costPerMonth.add(costForFeb);
        costPerMonth.add(costForMar);
        costPerMonth.add(costForApr);
        costPerMonth.add(costForMay);
        costPerMonth.add(costForJun);
        costPerMonth.add(costForJuly);
        costPerMonth.add(costForAug);
        costPerMonth.add(costForSept);
        costPerMonth.add(costForOct);
        costPerMonth.add(costForNov);
        costPerMonth.add(costForDec);

        ArrayList<Float> totalCostPerMonth = new ArrayList<>();

        for (int i=0; i<costPerMonth.size(); i++) {
            ArrayList<Float> month = costPerMonth.get(i);
            float totalCost = 0;

            for (int j=0; j< month.size(); j++) {
                totalCost += month.get(j);
            }

            totalCostPerMonth.add(totalCost);
        }

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

       salesDashboardView.getChart().addSeries("Revenue", Arrays.asList(months), totalRevenuePerMonth);
       salesDashboardView.getChart().addSeries("Cost", Arrays.asList(months), totalCostPerMonth);

       Stack<Item> recentAddedItems = ItemManagementModel.fetchItemsByUserType(inventoryManagementController.getUserType(), objectOutputStream, objectInputStream);

       // extract the product name and product id

       if (!recentAddedItems.isEmpty()) {
           byte limit = 5;

           if (!salesDashboardView.getRecentlyAddedItemsNameListModel().isEmpty() && !salesDashboardView.getRecentlyAddedItemsIDListModel().isEmpty()) {
               salesDashboardView.getRecentlyAddedItemsIDListModel().removeAllElements();
               salesDashboardView.getRecentlyAddedItemsNameListModel().removeAllElements();
           }

           while (!recentAddedItems.isEmpty() && limit != 0) {
                   Item item = recentAddedItems.pop();
                   salesDashboardView.getRecentlyAddedItemsIDListModel().addElement(String.valueOf(item.getItemId()));
                   salesDashboardView.getRecentlyAddedItemsNameListModel().addElement(item.getName());
                   limit--;
           }
       }

       // TODO: dynamic pie chart | computations: unit sold -> total quantity sold + total quantity sold today

        int soldQtyToday = 0;
        int soldQtyAnnual = 0; // TODO: this could be improve depending on the company requirement
        int  totalQtySold = 0;

        for (int i=0; i<listOfSalesItemOrders.size(); i++) {
            ItemOrder itemOrder = listOfSalesItemOrders.get(i);
            if (itemOrder.getDate().contains(inventoryManagementController.getFormattedDate())) {
                soldQtyToday = itemOrder.getQuantity();
            }
            soldQtyAnnual += itemOrder.getQuantity();
        }

        totalQtySold = soldQtyToday + soldQtyAnnual;

        // TODO: understand this percentage/ratio
        double soldQtyTodayPercent = ((double) soldQtyToday / totalQtySold) * 100;
        double soldQtyAnnualPercent = ((double) soldQtyAnnual / totalQtySold) * 100;

        String soldQtyTodayPercentStr = String.format("Today: %.2f%%", soldQtyTodayPercent);
        String soldQtyAnnualPercentStr = String.format("Max: %.2f%%", soldQtyAnnualPercent);
        String totalQtySoldPercentStr = String.format("Total: %d", totalQtySold);

        salesDashboardView.getSoldQtyTodayLabel().setText(soldQtyTodayPercentStr);
        salesDashboardView.getSoldQtyAnnualLabel().setText(soldQtyAnnualPercentStr);
        salesDashboardView.getTotalQtySoldLabel().setText(totalQtySoldPercentStr);

        salesDashboardView.getPieChart().addSeries("Today", soldQtyToday);
        salesDashboardView.getPieChart().addSeries("Annual", soldQtyAnnual);


    }

    public SalesDashboardView getSalesDashboardView() {
        return salesDashboardView;
    }
}
