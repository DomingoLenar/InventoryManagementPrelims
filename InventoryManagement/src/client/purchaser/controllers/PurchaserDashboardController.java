package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.models.PurchaserDashboardModel;
import client.purchaser.views.PurchaserDashboardView;
import utility.revision.Item;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PurchaserDashboardController {
    PurchaserDashboardModel purchaserDashboardModel;
    InventoryManagementController inventoryManagementController;
    PurchaserDashboardView purchaserDashboardView;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public PurchaserDashboardController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs){
        this.inventoryManagementController = inventoryManagementController;
        purchaserDashboardModel = new PurchaserDashboardModel();
        purchaserDashboardView = new PurchaserDashboardView();
        objectInputStream = oIs;
        objectOutputStream = oOs;
    }

    public void initComponents() {
        purchaserDashboardModel.fetchDashboard(objectOutputStream, objectInputStream);
        ArrayList<Item> lowStockItems = purchaserDashboardModel.getLowStockItems();
        int[] unitSold = purchaserDashboardModel.getUnitsSold();

        purchaserDashboardView.getPieChart().addSeries("Units Sold", unitSold[0]);
        purchaserDashboardView.getPieChart().addSeries("Stock Quantity", unitSold[1]);

        if (!purchaserDashboardView.getQuantityOfProductsModel().isEmpty() && !purchaserDashboardView.getLowStockProductsModel().isEmpty()){
            purchaserDashboardView.getLowStockProductsModel().removeAllElements();
            purchaserDashboardView.getQuantityOfProductsModel().removeAllElements();
        } else {
            for (Item item : lowStockItems) {
                purchaserDashboardView.getLowStockProductsModel().addElement(item.getName());
                purchaserDashboardView.getQuantityOfProductsModel().addElement(String.valueOf(item.getTotalQty()));
            }
        }
    }

    public PurchaserDashboardView getPurchaserDashboardView() {
        return purchaserDashboardView;
    }
}
