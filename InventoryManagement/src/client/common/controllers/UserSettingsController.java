package client.common.controllers;

import client.common.models.UserSettingsModel;
import client.common.views.UserSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserSettingsController {
    UserSettingsModel userSettingsModel;
    UserSettingsView userSettingsView;
    InventoryManagementController inventoryManagementController;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public UserSettingsController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        userSettingsModel = new UserSettingsModel();
        userSettingsView = new UserSettingsView();
        objectInputStream = oIs;
        objectOutputStream = oOs;

        initComponents();
    }

    private void initComponents() {
        userSettingsView.getUsernameLabel().setText(inventoryManagementController.getInventoryManagementInterface().getUsername());

        initButtons();
    }

    private void initButtons() {
        userSettingsView.getChangePasswordButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getChangePasswordController().getChangePasswordView().getMainPanel());
            }
        });

        userSettingsView.getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userSettingsModel.sessionTimeout(inventoryManagementController.inventoryManagementInterface.getUsername(), objectOutputStream, objectInputStream);

                // TODO: solution: better implementation -- note: this is just a band aid
                inventoryManagementController.getSalesDashboardController().getSalesDashboardView().getChart().removeSeries("Revenue");
                inventoryManagementController.getSalesDashboardController().getSalesDashboardView().getChart().removeSeries("Cost");
                inventoryManagementController.getSalesDashboardController().getSalesDashboardView().getPieChart().removeSeries("Today");
                inventoryManagementController.getSalesDashboardController().getSalesDashboardView().getPieChart().removeSeries("Annual");

//                inventoryManagementController.getPurchaserDashboardController().getPurchaserDashboardView().getPieChart().removeSeries()

                inventoryManagementController.getAdminDashboardController().getAdminDashboardView().getChart().removeSeries("Revenue");
                inventoryManagementController.getAdminDashboardController().getAdminDashboardView().getChart().removeSeries("Cost");
                inventoryManagementController.getAdminDashboardController().getAdminDashboardView().getPieChart().removeSeries("Today");
                inventoryManagementController.getAdminDashboardController().getAdminDashboardView().getPieChart().removeSeries("Annual");

                try {
                    inventoryManagementController.displayIndexPanel();
                } catch (InstantiationException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public UserSettingsView getUserSettingsView() {
        return userSettingsView;
    }
}
