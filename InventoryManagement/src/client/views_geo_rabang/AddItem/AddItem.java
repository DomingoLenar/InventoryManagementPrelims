package client.views_geo_rabang.AddItem;

import javax.swing.*;

public class AddItem {
    private JTextField prdct;
    private JTextField prc;
    private JTextField qty;
    private JComboBox warehouse;
    private JButton addItemButton;
    private JLabel addItem;

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
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
