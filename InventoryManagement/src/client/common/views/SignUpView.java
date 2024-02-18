package client.common.views;

import javax.swing.*;

public class SignUpView {
    private JTextField SUusernameField;
    private JTextField SUpasswordField;
    private JButton SUsignUpBtn;
    private JPanel SUpanel;
    private JComboBox roleComboBox;

    public JPanel getSUpanel() {
        return SUpanel;
    }

    public JButton getSUsignUpBtn() {
        return SUsignUpBtn;
    }

    public JTextField getSUusernameField() {
        return SUusernameField;
    }

    public JTextField getSUpasswordField() {
        return SUpasswordField;
    }

    public JComboBox getRoleComboBox() {
        return roleComboBox;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        SUpanel = new GradientPanel();
        String[] roles = {"Purchase", "Sales"};
        roleComboBox = new JComboBox(roles);
    }

}
