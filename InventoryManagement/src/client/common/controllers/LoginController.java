package client.common.controllers;

import client.StockControlCallbackClass;
import client.common.models.LoginModel;
import client.common.views.LoginView;
import shared.AlreadyLoggedInException;
import shared.StockControlServer;
import shared.UserExistsException;
import utility.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;

public class LoginController {
    LoginModel loginModel;
    InventoryManagementController inventoryManagementController;
    LoginView loginView;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    StockControlCallbackClass stockControlCallbackClass;
    StockControlServer stub;
    public LoginController(InventoryManagementController inventoryManagementController,StockControlCallbackClass stockControlCallbackClass, StockControlServer stub) throws IOException {
        this.inventoryManagementController = inventoryManagementController;
        this.stockControlCallbackClass = stockControlCallbackClass;
        this.stub = stub;
        loginModel = new LoginModel();
        loginView = new LoginView();
//        objectInputStream = oIs;
//        objectOutputStream = oOs;

        initComponents();
    }

    private void initComponents() {
        initButtons();
        initTextFields();
    }

    private void initTextFields() {

    }

    private void initButtons() {
        loginView.getLVloginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginView.getLVusernameField().getText();
                String password = loginView.getLVpasswordField().getText();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please input on required field");
                } else {
                    User user = loginModel.handleLogin(username, password, objectOutputStream, objectInputStream);
                    stockControlCallbackClass.authenticate(user);
                    try {
                        stub.login(stockControlCallbackClass);
                    } catch (RemoteException | AlreadyLoggedInException | UserExistsException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("true");
//                    if (user == null) {
//                        JOptionPane.showMessageDialog(null, "Username is already logged in!", null, JOptionPane.ERROR_MESSAGE);
//                    } else {
//                        String userType = user.getRole();
//                        inventoryManagementController.getInventoryManagementInterface().setUsername(username);
//                        inventoryManagementController.getInventoryManagementInterface().setUserType(userType);
//                        inventoryManagementController.getUserSettingsController().getUserSettingsView().getUsernameLabel().setText(username);
//                        switch (userType) {
//                            case "admin":
//                                inventoryManagementController.displayAdminMainMenu();
//                                break;
//                            case "sales":
//                                inventoryManagementController.displaySalesMainMenu();
//                                break;
//                            case "purchase":
//                                inventoryManagementController.displayPurchaserMainMenu();
//                                break;
//                        }
//                    }
                }
            }
        });
    }

    public LoginView getLoginView() {
        return loginView;
    }
}
