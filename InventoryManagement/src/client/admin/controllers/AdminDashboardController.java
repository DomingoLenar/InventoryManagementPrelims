package client.admin.controllers;

import client.admin.views.AdminDashboardView;
import client.common.controllers.InventoryManagementController;

public class AdminDashboardController {
    AdminDashboardView adminDashboardView;

    public AdminDashboardController(InventoryManagementController inventoryManagementController) {
        adminDashboardView = new AdminDashboardView();
    }

    public AdminDashboardView getAdminDashboardView() {
        return adminDashboardView;
    }
}
