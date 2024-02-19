package client.common.controllers;

import client.common.models.ProfileManagementModel;
import client.common.views.SignUpView;
import utility.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
@Deprecated
public class SignUpController {
    InventoryManagementController inventoryManagementController;
    ProfileManagementModel profileManagementModel;
    SignUpView signUpView;
    String userRole = "purchase";
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;


    public SignUpController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        this.profileManagementModel = new ProfileManagementModel();
        objectInputStream = oIs;
        objectOutputStream = oOs;

        signUpView = new SignUpView();

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
                    User user = ProfileManagementModel.handleSignup(username, password, userRole, objectOutputStream, objectInputStream);
                    String userType = user.getRole();
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
