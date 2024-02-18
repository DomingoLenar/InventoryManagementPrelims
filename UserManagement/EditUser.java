package UserManagement;

import javax.swing.*;

public class EditUser {
    private JLabel user;
    private JButton changePasswordButton;
    private JComboBox role;
    private JButton deleteUserButton;
    private JButton saveChangesButton;
    public JLabel getUser(){
        return user;
    }
    public JButton getChangePasswordButton(){
        return changePasswordButton;
    }
    public JComboBox getRole(){
        return role;
    }
    public JButton getDeleteUserButton(){
        return deleteUserButton;
    }
    public JButton getSaveChangesButton(){
        return saveChangesButton;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
