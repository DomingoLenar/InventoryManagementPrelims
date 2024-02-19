package client.common.controllers;

import client.common.models.ProfileManagementModel;
import client.common.views.UserSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserSettingsController {
    UserSettingsView userSettingsView;
    InventoryManagementController inventoryManagementController;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public UserSettingsController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        userSettingsView = new UserSettingsView();
        objectInputStream = oIs;
        objectOutputStream = oOs;

        initComponents();
    }

    private void initComponents() {
        userSettingsView.getUsernameLabel().setText(inventoryManagementController.getUsername());

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
                ProfileManagementModel.sessionTimeout(inventoryManagementController.getUsername(), objectOutputStream, objectInputStream);
                inventoryManagementController.displayIndexPanel();
            }
        });

    }

    public UserSettingsView getUserSettingsView() {
        return userSettingsView;
    }
}
