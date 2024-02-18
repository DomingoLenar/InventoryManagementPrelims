package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.views.PurchaserDashboardView;

public class PurchaserDashboardController {
    InventoryManagementController inventoryManagementController;
    PurchaserDashboardView purchaserDashboardView;

    public PurchaserDashboardController(InventoryManagementController inventoryManagementController){
        purchaserDashboardView = new PurchaserDashboardView();
    }

    public PurchaserDashboardView getPurchaserDashboardView() {
        return purchaserDashboardView;
    }
}
