package client.purchaser.views;

import client.common.views.GradientPanel;

import javax.swing.*;

public class PurchaserAddItemView {
    private JPanel mainPanel;
    private JButton addItemButton;
    private JTextField productField;
    private JTextField pPriceField;
    private JTextField quantityField;
    private JComboBox warehouseComboBox; // todo: warehouse capability
    private JButton cancelButton;
    private JTextField typeField;
    private JTextField supplierField;
    private JTextField stockLevelField;
    private JTextField batchNoField;

    public JTextField getBatchNoField() {
        return batchNoField;
    }

    public JTextField getStockLevelField() {
        return stockLevelField;
    }

    public JTextField getSupplierField() {
        return supplierField;
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
