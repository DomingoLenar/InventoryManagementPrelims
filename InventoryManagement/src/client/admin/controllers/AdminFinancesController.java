package client.admin.controllers;

import client.admin.views.AdminFinancesView;
import client.common.controllers.InventoryManagementController;

public class AdminFinancesController {
    InventoryManagementController inventoryManagementController;
    AdminFinancesView adminFinancesView;

    public AdminFinancesController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;

        adminFinancesView = new AdminFinancesView();
    }

    public AdminFinancesView getAdminFinancesView() {
        return adminFinancesView;
    }
}
