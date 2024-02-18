package client.admin.views;

import javax.swing.*;

public class AdminUserManagementView {

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JComboBox comboBox1;
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
    public JComboBox getComboBox1(){
        return comboBox1;
    }
    public JLabel getUserManagement(){
        return userManagement;
    }

    public JButton getCreateUserButton() {
        return createUserButton;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
