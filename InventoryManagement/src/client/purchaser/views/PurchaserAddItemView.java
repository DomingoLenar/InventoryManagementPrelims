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

    public JButton getCancelButton() {
        return cancelButton;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        mainPanel = new GradientPanel();
    }
}
