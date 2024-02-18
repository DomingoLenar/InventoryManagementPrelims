package client.admin.views;

import javax.swing.*;

public class AdminAddItemView {
    private JTextField prdct;
    private JTextField prc;
    private JTextField qty;
    private JComboBox warehouse;
    private JButton addItemButton;
    private JLabel addItem;
    private JPanel mainPanel;
    private JButton cancelbtn;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getPrdct(){
        return prdct;
    }
    public JTextField getPrc(){
        return prc;
    }
    public JTextField getQty(){
        return qty;
    }
    public JComboBox getWarehouse(){
        return warehouse;
    }
    public JLabel getAddItem(){
        return addItem;
    }

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JButton getCancelbtn() {
        return cancelbtn;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
