package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesFinancesView;

public class SalesFinancesController {
    InventoryManagementController inventoryManagementController;
    SalesFinancesView salesFinancesView;

    public SalesFinancesController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;

        salesFinancesView = new SalesFinancesView();
    }

    public SalesFinancesView getSalesFinancesView() {
        return salesFinancesView;
    }
}
