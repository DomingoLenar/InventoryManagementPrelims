package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.views.PurchaserNavigationBarView;

public class PurchaserNavigationBarController {
    InventoryManagementController inventoryManagementController;
    PurchaserNavigationBarView purchaserNavigationBarView;
    public PurchaserNavigationBarController (InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;

        purchaserNavigationBarView = new PurchaserNavigationBarView();

        initButtons();
    }

    private void initButtons() {
    }

    public PurchaserNavigationBarView getPurchaserNavigationBarView() {
        return purchaserNavigationBarView;
    }
}
