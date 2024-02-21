package client.admin.views;

import client.common.views.GradientPanel;

import javax.swing.*;

public class AdminAddItemView {
    private JTextField productName;
    private JTextField productPrice;
    private JTextField quantity;
    private JComboBox warehouse;
    private JButton addItemButton;
    private JPanel mainPanel;
    private JButton cancelbtn;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getProductName() {
        return productName;
    }

    public JTextField getProductPrice() {
        return productPrice;
    }

    public JTextField getQuantity() {
        return quantity;
    }

    public JComboBox getWarehouse() {
        return warehouse;
    }

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JButton getCancelbtn() {
        return cancelbtn;
    }

    private void createUIComponents() {
        mainPanel = new GradientPanel();
    }
}
