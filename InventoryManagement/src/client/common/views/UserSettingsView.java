package client.common.views;

import javax.swing.*;

public class UserSettingsView {
    private JPanel mainPanel;
    private JButton changePasswordButton;
    private JButton logOutButton;
    private JLabel usernameLabel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }
}
