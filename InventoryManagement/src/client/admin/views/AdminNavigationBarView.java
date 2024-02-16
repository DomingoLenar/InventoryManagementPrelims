package client.admin.views;

import javax.swing.*;

public class AdminNavigationBarView {
    private JPanel leftPanel;
    private JButton dashboardButton;
    private JButton stockControlButton;
    private JButton financesButton;
    private JButton salesInvoicesButton;
    private JButton userManagementButton;
    private JButton profileButton;
    private JButton button1;

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public JButton getStockControlButton() {
        return stockControlButton;
    }

    public JButton getDashboardButton() {
        return dashboardButton;
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    public JButton getFinancesButton() {
        return financesButton;
    }

    public JButton getSalesInvoicesButton() {
        return salesInvoicesButton;
    }

    public JButton getUserManagementButton() {
        return userManagementButton;
    }
}
