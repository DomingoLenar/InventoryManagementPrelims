package client.purchaser.views;

import client.common.views.GradientPanel;

import javax.swing.*;

public class PurchaserAddItemView {
    private JPanel mainPanel;
    private JButton addItemButton;
    private JTextField productField;
    private JTextField priceField;
    private JTextField quantityField;
    private JComboBox warehouseComboBox;
    private JButton cancelButton;
    private JComboBox supplierComboBox;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JTextField getProductField() {
        return productField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JComboBox getWarehouseComboBox() {
        return warehouseComboBox;
    }

    public JComboBox getSupplierComboBox() {
        return supplierComboBox;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    private void createUIComponents() {
        mainPanel = new GradientPanel();
    }
}
