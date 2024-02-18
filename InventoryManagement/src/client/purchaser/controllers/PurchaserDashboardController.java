package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.views.PurchaserDashboardView;

import java.net.Socket;

public class PurchaserDashboardController {
    InventoryManagementController inventoryManagementController;
    PurchaserDashboardView purchaserDashboardView;

    public PurchaserDashboardController(InventoryManagementController inventoryManagementController, Socket clientSocket){
        purchaserDashboardView = new PurchaserDashboardView();
    }

    public PurchaserDashboardView getPurchaserDashboardView() {
        return purchaserDashboardView;
    }
}
