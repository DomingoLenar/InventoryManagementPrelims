package UserManagement;

import javax.swing.*;

public class UserManagement {

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JComboBox comboBox1;
    private JLabel userManagement;
    private JButton createButton;

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
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
