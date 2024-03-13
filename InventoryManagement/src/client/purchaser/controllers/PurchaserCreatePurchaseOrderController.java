package client.purchaser.controllers;

import client.common.controllers.InventoryManagementController;
import client.purchaser.models.PurchaserCreatePurchaseOrderModel;
import client.purchaser.views.PurchaserCreatePurchaseOrderView;

import javax.swing.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class PurchaserCreatePurchaseOrderController {
    PurchaserCreatePurchaseOrderModel purchaserCreatePurchaseOrderModel;
    InventoryManagementController inventoryManagementController;
    PurchaserCreatePurchaseOrderView purchaserCreatePurchaseOrderView;
    ObjectOutputStream oOs;
    ObjectInputStream oIs;
    String[] productDetails;
    String supplier = null;
    public void setProductDetails(String[] productDetails) {
        this.productDetails = productDetails;
    }

    public String[] getProductDetails() {
        return productDetails;
    }

    public PurchaserCreatePurchaseOrderController(InventoryManagementController inventoryManagementController, ObjectInputStream oIs, ObjectOutputStream oOs){
        this.inventoryManagementController = inventoryManagementController;
        purchaserCreatePurchaseOrderModel = new PurchaserCreatePurchaseOrderModel();
        purchaserCreatePurchaseOrderView = new PurchaserCreatePurchaseOrderView();
        this.oIs = oIs;
        this.oOs = oOs;

    }

    public void initComponents() {
        initComboBox();
        initButtons();
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    private void initComboBox() {
        ArrayList<String> suppliers = purchaserCreatePurchaseOrderModel.fetchListOfSuppliers(oOs, oIs);

        if (!Objects.equals(supplier, "none")) {
            purchaserCreatePurchaseOrderView.getSupplierComboBox().setSelectedItem(supplier);
            purchaserCreatePurchaseOrderView.getSupplierComboBox().setEnabled(false);
        } else {
            purchaserCreatePurchaseOrderView.getSupplierComboBox().setEnabled(true);
            supplier = suppliers.get(0); // first item of drop down
            purchaserCreatePurchaseOrderView.getSupplierComboBox().setSelectedItem(supplier);
        }

        for (String supplier:suppliers) {
            purchaserCreatePurchaseOrderView.getSupplierComboBox().addItem(supplier);
        }
        purchaserCreatePurchaseOrderView.getSupplierComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    supplier = e.getItem().toString();
                }
            }
        });
    }

    private void initButtons() {
        purchaserCreatePurchaseOrderView.getAddItemButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int itemId = Integer.parseInt(productDetails[0]);
                String productName = purchaserCreatePurchaseOrderView.getProductField().getText();
                float purchasePrice = Float.parseFloat(purchaserCreatePurchaseOrderView.getPurchasePriceField().getText());
                int qtyItem = Integer.parseInt(purchaserCreatePurchaseOrderView.getQuantityField().getText());
//                String supplier = purchaserCreatePurchaseOrderView.getSupplierField().getText();
                String type = purchaserCreatePurchaseOrderView.getTypeField().getText();
                String createdBy = inventoryManagementController.getInventoryManagementInterface().getUsername();
                String dateCreated = inventoryManagementController.getInventoryManagementInterface().getFormattedDate();
                String orderType = inventoryManagementController.getInventoryManagementInterface().getUserType();
//                String batchNo = purchaserCreatePurchaseOrderView.getBatchNoField().getText(); // todo: supplier field dropdown, purchase price & qty user input
                boolean creationSuccess = purchaserCreatePurchaseOrderModel.OnCreate(productName, purchasePrice, qtyItem, supplier, type, createdBy, dateCreated, itemId,
                        orderType, oOs, oIs);
                if (creationSuccess) {
                    JOptionPane.showMessageDialog(null, "Purchase order creation success ", null, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failure to create purchase order", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        purchaserCreatePurchaseOrderView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryManagementController.changeScreen(inventoryManagementController.getPurchaserStockControlController().purchaserStockMonitorView.getMainPanel());
            }
        });
    }

    public PurchaserCreatePurchaseOrderView getPurchaserCreatePurchaseOrderView() {
        return purchaserCreatePurchaseOrderView;
    }
}
