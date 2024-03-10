package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.models.SalesDashboardModel;
import client.sales.views.SalesDashboardView;
import utility.revision.Item;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class SalesDashboardController {
    SalesDashboardModel salesDashboardModel;
    InventoryManagementController inventoryManagementController;
    SalesDashboardView salesDashboardView;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public SalesDashboardController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        salesDashboardModel = new SalesDashboardModel();
        salesDashboardView = new SalesDashboardView();

        objectInputStream = oIs;
        objectOutputStream = oOs;

    }

    public void initComponents() {
        salesDashboardModel.fetchDashboard(objectOutputStream, objectInputStream);

        ArrayList<String> yearlyRevenueCogs = salesDashboardModel.getYearlyRevenueCogs();
        ArrayList<Float> totalRevenuePerMonth = new ArrayList<>();
        ArrayList<Float> totalCostPerMonth = new ArrayList<>();
        for (int i=0; i< yearlyRevenueCogs.size(); i++) {
            String[] arr_yearlyRevenueCogs = yearlyRevenueCogs.get(i).split(",");
            totalRevenuePerMonth.add(Float.parseFloat(arr_yearlyRevenueCogs[0]));
            totalCostPerMonth.add(Float.parseFloat(arr_yearlyRevenueCogs[1]));
        }

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
       salesDashboardView.getChart().addSeries("Revenue", Arrays.asList(months), totalRevenuePerMonth);
       salesDashboardView.getChart().addSeries("Cogs", Arrays.asList(months), totalCostPerMonth);

       Stack<Item> recentAddedItems = salesDashboardModel.getRecentlyAddedItems();

       // extract the product name and product id
       if (!recentAddedItems.isEmpty()) {
           byte limit = 5;

           if (!salesDashboardView.getRecentlyAddedItemsNameListModel().isEmpty() && !salesDashboardView.getRecentlyAddedItemsIDListModel().isEmpty()) {
               salesDashboardView.getRecentlyAddedItemsIDListModel().removeAllElements();
               salesDashboardView.getRecentlyAddedItemsNameListModel().removeAllElements();
           }

           while (!recentAddedItems.isEmpty() && limit != 0) {
                   Item item = recentAddedItems.pop();
                   salesDashboardView.getRecentlyAddedItemsIDListModel().addElement(String.valueOf(item.getId()));
                   salesDashboardView.getRecentlyAddedItemsNameListModel().addElement(item.getName());
                   limit--;
           }
       }

       float[] revenueNCogsToday = salesDashboardModel.getRevenueAndCogs();

//       // TODO: dynamic pie chart | computations: unit sold -> total quantity sold + total quantity sold today
//
//        int soldQtyToday = 0;
//        int soldQtyAnnual = 0; // TODO: this could be improve depending on the company requirement
//        int  totalQtySold = 0;
//
//        for (int i=0; i<listOfSalesItemOrders.size(); i++) {
//            ItemOrder itemOrder = listOfSalesItemOrders.get(i);
//            if (itemOrder.getDate().contains(inventoryManagementController.getInventoryManagementInterface().getFormattedDate())) {
//                soldQtyToday = itemOrder.getQuantity();
//            }
//            soldQtyAnnual += itemOrder.getQuantity();
//        }
//
//        totalQtySold = soldQtyToday + soldQtyAnnual;
//
//        // TODO: understand this percentage/ratio
//        double soldQtyTodayPercent = ((double) soldQtyToday / totalQtySold) * 100;
//        double soldQtyAnnualPercent = ((double) soldQtyAnnual / totalQtySold) * 100;
//
//        String soldQtyTodayPercentStr = String.format("Today: %.2f%%", soldQtyTodayPercent);
//        String soldQtyAnnualPercentStr = String.format("Max: %.2f%%", soldQtyAnnualPercent);
//        String totalQtySoldPercentStr = String.format("Total: %d", totalQtySold);
//
//        salesDashboardView.getSoldQtyTodayLabel().setText(soldQtyTodayPercentStr);
//        salesDashboardView.getSoldQtyAnnualLabel().setText(soldQtyAnnualPercentStr);
//        salesDashboardView.getTotalQtySoldLabel().setText(totalQtySoldPercentStr);

        salesDashboardView.getPieChart().addSeries("Revenue", revenueNCogsToday[0]);
        salesDashboardView.getPieChart().addSeries("Cogs", revenueNCogsToday[1]);

    }

    public SalesDashboardView getSalesDashboardView() {
        return salesDashboardView;
    }
}
