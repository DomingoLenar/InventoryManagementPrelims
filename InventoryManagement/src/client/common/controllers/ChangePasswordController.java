package client.common.controllers;

import client.common.models.ChangePasswordModel;
import client.common.views.ChangePasswordView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChangePasswordController {
    ChangePasswordModel changePasswordModel;
    InventoryManagementController inventoryManagementController;
    ChangePasswordView changePasswordView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public ChangePasswordController(InventoryManagementController inventoryManagementController, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        this.inventoryManagementController = inventoryManagementController;
        changePasswordModel = new ChangePasswordModel();
        changePasswordView = new ChangePasswordView();
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;

        initComponents();
    }

    private void initComponents() {
        initButtons();
    }

    private void initButtons() {
        changePasswordView.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = changePasswordView.getOldPasswordField().getText();
                String newPassword = changePasswordView.getNewPasswordField().getText();

                if (oldPassword.isEmpty() || newPassword.isEmpty()) {
                    // do smth
                }
                if (newPassword.length() > 7){ // fetch the old password from the server
                    changePasswordModel.changePassword(inventoryManagementController.inventoryManagementInterface.getUsername(), newPassword, oldPassword, objectOutputStream, objectInputStream);
                }

            }
        });
    }

    public ChangePasswordView getChangePasswordView() {
        return changePasswordView;
    }
}
