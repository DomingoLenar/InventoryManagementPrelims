package client.sales.views;

import javax.swing.*;

public class SalesCreateCustomerOrderView {
    private JPanel mainPanel;
    private JTextField prodName;
    private JTextField qnty;
    private JTextField cxName;
    private JButton createOrderButton;
    private JTextField prc;
    private JTextField prodType;
    private JTextField batchNo;
    private JTextField supplier;

    public JTextField getProdName() {
        return prodName;
    }

    public JTextField getQnty() {
        return qnty;
    }

    public JTextField getCxName() {
        return cxName;
    }

    public JButton getCreateOrderButton() {
        return createOrderButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getPrc() {
        return prc;
    }

    public JTextField getProdType() {
        return prodType;
    }

    public JTextField getBatchNo() {
        return batchNo;
    }

    public JTextField getSupplier() {
        return supplier;
    }
}
