package client.common.views;

import javax.swing.*;

public class InventoryManagementInterface extends JFrame {

    public InventoryManagementInterface(){
        setVisible(true);
//        setLocationRelativeTo(null); TODO: if client click exit button then set active status to false (i.e,. handle the xml)
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);
    }
}
