package client.purchaser.views;

import javax.swing.*;

public class PurchaserItemListingView {
    private JPanel mainPanel;
    private JTextField prodField;
    private JComboBox<String> comboBox1;
    private JButton addItemButton;

    public JTextField getProdField() {
        return prodField;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JComboBox<String> getComboBox1() {
        return comboBox1;
    }

    public JButton getAddItemButton() {
        return addItemButton;
    }
}
