package client.purchaser.views;

import client.common.views.GradientPanel;

import javax.swing.*;

public class PurchaserCreatePurchaseOrderView {
    private JPanel mainPanel;
    private JButton addItemButton;
    private JTextField productField;
    private JTextField pPriceField;
    private JTextField quantityField;
    private JComboBox warehouseComboBox; // todo: warehouse capability
    private JButton cancelButton;
    private JTextField typeField;
    private JComboBox supplierComboBox;
    private JTextField stockLevelField;
    private JTextField batchNoField;

    public JTextField getBatchNoField() {
        return batchNoField;
    }

    public JTextField getStockLevelField() {
        return stockLevelField;
    }

    public JComboBox getSupplierComboBox() {
        return supplierComboBox;
    }

    public JTextField getpPriceField() {
        return pPriceField;
    }

    public JTextField getTypeField() {
        return typeField;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JTextField getProductField() {
        return productField;
    }

    public JTextField getPurchasePriceField() {
        return pPriceField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JComboBox getWarehouseComboBox() {
        return warehouseComboBox;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    private void createUIComponents() {
        mainPanel = new GradientPanel();
    }
}
