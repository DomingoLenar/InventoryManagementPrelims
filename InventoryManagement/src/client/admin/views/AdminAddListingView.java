package client.admin.views;

import client.common.views.GradientPanel;

import javax.swing.*;

public class AdminAddListingView {
    private JTextField productTextField;
    private JButton addToListButton;
    private JLabel AddListing;
    private JLabel Product;
    private JPanel mainPanel; // just copied this from previous views

    public JPanel getMainPanel() { // just copied this from previous views
        return mainPanel;
    }
    public JLabel getProduct(){
        return Product;
    }
    public JTextField getProductTextField(){
        return productTextField;
    }
    public JButton getAddToListButton(){
        return addToListButton;
    }
    public JLabel getAddListing(){
        return AddListing;
    }
    private void createUIComponents() {
        mainPanel = new GradientPanel(); // just copied this from previous views
    }
}
