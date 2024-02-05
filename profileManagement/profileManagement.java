package profileManagement;

import javax.swing.*;

public class profileManagement {
    private JTextField userNameTextField;
    private JTextField userEmailTextField;
    private JButton changeUserAccountButton;
    private JButton changePasswordButton;
    private JButton logOutButton;

    public JTextField getUserNameTextField(){
        return userNameTextField;
    }
    public JTextField getUserEmailTextField(){
        return userEmailTextField;
    }

    public JButton getChangeUserAccountButton(){
        return changeUserAccountButton;
    }

    public JButton getChangePasswordButton(){
        return changePasswordButton;
    }
    public JButton getLogOutButton(){
        return logOutButton;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


