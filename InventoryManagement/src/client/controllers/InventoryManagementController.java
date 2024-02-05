package client.controllers;

import client.InventoryManagementInterface;

public class InventoryManagementController {
    InventoryManagementInterface inventoryManagementInterface;
    public InventoryManagementController() {
        inventoryManagementInterface = new InventoryManagementInterface();
    }
}
