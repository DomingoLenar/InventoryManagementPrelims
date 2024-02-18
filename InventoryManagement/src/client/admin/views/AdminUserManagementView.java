package client.admin.views;

import client.common.views.GradientPanel;

import javax.swing.*;

public class AdminUserManagementView {

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JComboBox roleComboBox;
    private JLabel userManagement;
    private JPanel mainPanel;
    private JButton createUserButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getUsernameTextField(){
        return usernameTextField;
    }
    public JTextField getPasswordTextField(){
        return passwordTextField;
    }

    public JComboBox getRoleComboBox() {
        return roleComboBox;
    }

    public JLabel getUserManagement(){
        return userManagement;
    }

    public JButton getCreateUserButton() {
        return createUserButton;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        mainPanel = new GradientPanel();
        String[] role = {"Purchase", "Sales"};
        roleComboBox = new JComboBox(role);
    }
}
