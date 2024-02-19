package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesDashboardView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalesDashboardController {
    SalesDashboardView salesDashboardView;

    public SalesDashboardController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        salesDashboardView = new SalesDashboardView();
    }

    public SalesDashboardView getSalesDashboardView() {
        return salesDashboardView;
    }
}
