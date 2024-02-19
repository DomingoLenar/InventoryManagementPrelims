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
    }

    public PurchaserDashboardView getPurchaserDashboardView() {
        return purchaserDashboardView;
    }
}
