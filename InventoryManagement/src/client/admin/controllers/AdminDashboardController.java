package client.admin.controllers;

import client.admin.views.AdminDashboardView;
import client.common.controllers.InventoryManagementController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminDashboardController {
    AdminDashboardView adminDashboardView;

    public AdminDashboardController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        adminDashboardView = new AdminDashboardView();
    }

    public AdminDashboardView getAdminDashboardView() {
        return adminDashboardView;
    }
}
