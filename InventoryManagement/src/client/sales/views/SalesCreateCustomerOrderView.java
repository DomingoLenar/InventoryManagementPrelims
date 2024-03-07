package client.sales.views;

import javax.swing.*;

public class SalesCreateCustomerOrderView {
    private JPanel mainPanel;
    private JTextField prodName;
    private JTextField qnty;
    private JTextField cxName;
    private JButton createOrderButton;
    private JTextField prc;

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

    public void setProdName(JTextField prodName) {
        this.prodName = prodName;
    }

    public void setQnty(JTextField qnty) {
        this.qnty = qnty;
    }

    public void setPrc(JTextField prc) {
        this.prc = prc;
    }
}
