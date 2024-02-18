package client.common.controllers;

import client.common.views.SignUpView;
import client.common.models.ProfileManagementModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.Socket;

public class SignUpController {
    InventoryManagementController inventoryManagementController;
    ProfileManagementModel profileManagementModel;
    SignUpView signUpView;
    Socket socket;
    String userRole = "purchase";

    public SignUpController(InventoryManagementController inventoryManagementController, Socket clientSocket) {
        this.inventoryManagementController = inventoryManagementController;
        this.profileManagementModel = new ProfileManagementModel();

        signUpView = new SignUpView();
        socket = clientSocket;

        initComponents();
    }

    private void initComponents() {
        initButtons();
    }

    private void initButtons() {
        signUpView.getSUsignUpBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=signUpView.getSUusernameField().getText();
                String password=signUpView.getSUpasswordField().getText();

                if (username.isEmpty()) {
                    // show rLabel below username textfield
                }
                if (password.length() == 0) {
                    // show rLabel below password textfield
                }
                if (username.isEmpty() && password.length() == 0);
                // show rLabel below password textfield
                // show rLabel below username textfield
                if (password.length() < 7); // show rLabel password must be => 8 characters

                if (!username.isEmpty() && password.length() > 7) {
                    String userType = ProfileManagementModel.handleSignup(username, password, userRole, socket);
                    if (userType != null) {
                        switch (userType) {
                            case "sales":
                                inventoryManagementController.displaySalesMainMenu();
                                break;
                            case "purchase":
                                inventoryManagementController.displayPurchaserMainMenu();
                                break;
                        }
                    }
                }
            }
        });

        signUpView.getRoleComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    userRole = e.getItem().toString();
                    }
                }
        });

    }

    public SignUpView getSignUpView() {
        return signUpView;
    }
}
