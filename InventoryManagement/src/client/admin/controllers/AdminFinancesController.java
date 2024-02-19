package client.admin.controllers;

import client.admin.views.AdminFinancesView;
import client.common.controllers.InventoryManagementController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminFinancesController {
    InventoryManagementController inventoryManagementController;
    AdminFinancesView adminFinancesView;

    public AdminFinancesController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;

        adminFinancesView = new AdminFinancesView();
    }

    public AdminFinancesView getAdminFinancesView() {
        return adminFinancesView;
    }
}
