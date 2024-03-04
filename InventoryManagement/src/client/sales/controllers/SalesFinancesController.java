package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.views.SalesFinancesView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Deprecated
public class SalesFinancesController {
    InventoryManagementController inventoryManagementController;
    SalesFinancesView salesFinancesView;

    public SalesFinancesController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;

        salesFinancesView = new SalesFinancesView();
    }

    public SalesFinancesView getSalesFinancesView() {
        return salesFinancesView;
    }
}
