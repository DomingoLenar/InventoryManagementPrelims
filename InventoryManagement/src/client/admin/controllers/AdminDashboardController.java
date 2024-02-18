package client.admin.controllers;

import client.admin.views.AdminDashboardView;
import client.common.controllers.InventoryManagementController;

import java.net.Socket;

public class AdminDashboardController {
    AdminDashboardView adminDashboardView;

    public AdminDashboardController(InventoryManagementController inventoryManagementController, Socket clientSocket) {
        adminDashboardView = new AdminDashboardView();
    }

    public AdminDashboardView getAdminDashboardView() {
        return adminDashboardView;
    }
}
