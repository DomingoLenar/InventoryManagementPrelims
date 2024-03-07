package client.common.views;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InventoryManagementInterface extends JFrame {
    JPanel mainContainer;
    String userType;
    String username;
    String formattedDate;

    public String getFormattedDate() {
        return formattedDate;
    }

    public JPanel getMainContainer() {
        return mainContainer;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public InventoryManagementInterface(){
        LocalDate currentDate = LocalDate.now();

        // Format the date using a specific pattern (optional)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formattedDate = currentDate.format(formatter);

        // TODO: find a solution that declared this as an attribute of InventoryManagementInterface (JFrame)
        mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());

        add(mainContainer);
        setVisible(true);
//        setLocationRelativeTo(null); TODO: if client click exit button then set active status to false (i.e,. handle the xml)
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);
    }
}
