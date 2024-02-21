package client.sales.views;

import javax.swing.*;

public class SalesNavigationBarView {
    private JButton financesButton;
    private JButton profileButton;
    private JButton stockControlButton;
    private JButton dashboardButton;
    private JPanel leftPanel;
    private JButton logobtn;
    private JButton salesInvoicesButton;

    public JButton getSalesInvoicesButton() {
        return salesInvoicesButton;
    }

    public JButton getDashboardButton() {
        return dashboardButton;
    }

    public JButton getFinancesButton() {
        return financesButton;
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    public JButton getStockControlButton() {
        return stockControlButton;
    }

    public JButton getLogobtn() {
        return logobtn;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

//    private void createUIComponents() {
//        logobtn = new JButton();
//        ImageIcon icon = new ImageIcon("InventoryManagement/src/client/resource/inventory_management_logo.png");
//        Image image = icon.getImage();
//        Image modifiedImage = image.getScaledInstance(115, 200, Image.SCALE_SMOOTH);
//
//        icon = new ImageIcon(modifiedImage);
//
//        logobtn.setIcon(icon);
//    }

}
