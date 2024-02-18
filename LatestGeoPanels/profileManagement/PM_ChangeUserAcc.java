package profileManagement;

import javax.swing.*;

public class PM_ChangeUserAcc {
    private JTextField userNameTextField;
    private JTextField userEmailTextField;
    private JComboBox comboBox1;
    private JLabel icon;

    public JTextField getUserEmailTextField(){
        return userEmailTextField;
    }
    public JTextField getUserNameTextField(){
        return userNameTextField;
    }
    public JComboBox getComboBox1(){
        return comboBox1;
    }
    public JLabel getIcon(){
        return icon;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
