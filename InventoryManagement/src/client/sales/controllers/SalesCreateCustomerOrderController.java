package client.sales.controllers;

import client.common.controllers.InventoryManagementController;
import client.sales.models.SalesCreateCustomerOrderModel;
import client.sales.views.SalesCreateCustomerOrderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalesCreateCustomerOrderController {
    SalesCreateCustomerOrderModel salesCreateCustomerOrderModel;
    InventoryManagementController inventoryManagementController;
    SalesCreateCustomerOrderView salesCreateCustomerOrderView;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    String[] productDetails;

    public String[] getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String[] productDetails) {
        this.productDetails = productDetails;
    }

    public SalesCreateCustomerOrderController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs) {
        this.inventoryManagementController = inventoryManagementController;
        salesCreateCustomerOrderModel = new SalesCreateCustomerOrderModel();
        salesCreateCustomerOrderView = new SalesCreateCustomerOrderView();
        objectOutputStream = oOs;
        objectInputStream = oIs;
        
        initComponents();
    }

    private void initComponents() {
        salesCreateCustomerOrderView.getCreateOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] prodDetails = getProductDetails();
                int itemId = Integer.parseInt(prodDetails[0]);
                int qnty_stock = Integer.parseInt(prodDetails[prodDetails.length-1]);
                int qnty_bought = Integer.parseInt(salesCreateCustomerOrderView.getQnty().getText()); // TODO: subtract: current stock of prod - qnty of prod in creation of cx order
                String prod = salesCreateCustomerOrderView.getProdName().getText();
                float prc = Float.parseFloat(salesCreateCustomerOrderView.getPrc().getText());
                String createdBy = inventoryManagementController.getInventoryManagementInterface().getUsername();
                String dateCreated = inventoryManagementController.getInventoryManagementInterface().getFormattedDate();
                String orderType = inventoryManagementController.getInventoryManagementInterface().getUserType();
                String batchNo = inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getBatchNo().getText();
                String type = inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getProdType().getText();
                String supplier = inventoryManagementController.getSalesCreateCustomerOrderController().getSalesCreateCustomerOrderView().getSupplier().getText();

                if (qnty_bought <= qnty_stock) {
                    boolean successOrder = salesCreateCustomerOrderModel.OnGenerate(prod, prc, qnty_bought, createdBy, dateCreated, orderType, itemId, type, batchNo, qnty_stock, supplier,
                            objectInputStream, objectOutputStream);
                    if (!successOrder) {
                        JOptionPane.showMessageDialog(null, "Generation of sales invoice failed", "Error creating sales invoice", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Generation of sales invoice success", "Success creating sales invoice", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Quantity should not be greater than stock quantity", "Error creation of sales invoice", JOptionPane.ERROR_MESSAGE);

                }

            }
        });
    }

    public SalesCreateCustomerOrderView getSalesCreateCustomerOrderView() {
        return salesCreateCustomerOrderView;
    }
}
