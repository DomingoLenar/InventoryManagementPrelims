package client.admin.controllers;

import client.admin.views.AdminDashboardView;
import client.common.controllers.InventoryManagementController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminDashboardController {
    InventoryManagementController inventoryManagementController;
    AdminDashboardView adminDashboardView;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public AdminDashboardController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        objectOutputStream = oOs;
        objectInputStream = oIs;

        adminDashboardView = new AdminDashboardView();
    }

    public void initComponents(){

    }

    public AdminDashboardView getAdminDashboardView() {
        return adminDashboardView;
    }
}
