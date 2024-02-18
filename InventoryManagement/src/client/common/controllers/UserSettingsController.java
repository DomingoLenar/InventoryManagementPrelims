package client.common.controllers;

import client.common.views.UserSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSettingsController {
    UserSettingsView userSettingsView;
    InventoryManagementController inventoryManagementController;

    public UserSettingsController(InventoryManagementController inventoryManagementController) {
        this.inventoryManagementController = inventoryManagementController;
        userSettingsView = new UserSettingsView();

        // TODO: display the username fetch from server
//        userSettingsView.getUsernameLabel().setText();
        initButtons();
    }

    private void initButtons() {
        userSettingsView.getChangePasswordButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: userchangepasswordview
            }
        });

        userSettingsView.getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: perform logout operation
            }
        });

    }

    public UserSettingsView getUserSettingsView() {
        return userSettingsView;
    }
}
