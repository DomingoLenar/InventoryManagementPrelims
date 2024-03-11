package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.models.PurchaserDashboardModel;
import client.purchaser.views.PurchaserDashboardView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
//        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        // Change to Raw Data
//        purchaserDashboardView.getChart().addSeries("Cost", Arrays.asList(months), Arrays.asList(1000, 1500, 1200, 800, 1200, 800, 800, 800, 800, 800, 800, 800));
//        purchaserDashboardView.getChart().addSeries("Budget", Arrays.asList(months), Arrays.asList(1500, 1700, 1500, 1000, 1500, 1000, 1000, 1000, 1000, 1000, 1000, 1000));
    }

    public PurchaserDashboardView getPurchaserDashboardView() {
        return purchaserDashboardView;
    }
}
