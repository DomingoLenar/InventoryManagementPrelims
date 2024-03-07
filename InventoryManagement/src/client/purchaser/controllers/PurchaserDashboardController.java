package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.views.PurchaserDashboardView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PurchaserDashboardController {
    InventoryManagementController inventoryManagementController;
    PurchaserDashboardView purchaserDashboardView;

    public PurchaserDashboardController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs){
        purchaserDashboardView = new PurchaserDashboardView();

        initComponents();
    }

    public void initComponents() {
//        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        // Change to Raw Data
//        purchaserDashboardView.getChart().addSeries("Cost", Arrays.asList(months), Arrays.asList(1000, 1500, 1200, 800, 1200, 800, 800, 800, 800, 800, 800, 800));
//        purchaserDashboardView.getChart().addSeries("Budget", Arrays.asList(months), Arrays.asList(1500, 1700, 1500, 1000, 1500, 1000, 1000, 1000, 1000, 1000, 1000, 1000));
    }

    public PurchaserDashboardView getPurchaserDashboardView() {
        return purchaserDashboardView;
    }
}
