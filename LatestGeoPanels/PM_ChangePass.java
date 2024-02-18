package profileManagement;

import javax.swing.*;

public class PM_ChangePass {
    private JTextField userNameTextField;
    private JTextField userEmailTextField;
    private JPasswordField oldPasswordPasswordField;
    private JPasswordField newPasswordPasswordField;
    private JButton saveButton;
    private JLabel icon;

    public JTextField getUserEmailTextField(){
        return userEmailTextField;
    }
    public  JTextField getUserNameTextField(){
        return userNameTextField;
    }
    public JPasswordField getOldPasswordPasswordField(){
        return oldPasswordPasswordField;
    }
    public JPasswordField getNewPasswordPasswordField(){
        return newPasswordPasswordField;
    }
    public JButton getSaveButton(){
        return saveButton;
    }
    public JLabel getIcon(){
        return icon;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
