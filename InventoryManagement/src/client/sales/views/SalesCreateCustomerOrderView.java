package client.sales.views;

import javax.swing.*;

public class SalesCreateCustomerOrderView {
    private JPanel mainPanel;
    private JTextField prodName;
    private JTextField qnty;
    private JTextField cxName;
    private JButton createOrderButton;

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
}
