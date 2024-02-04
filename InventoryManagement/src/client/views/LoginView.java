package client.views;

import javax.swing.*;

public class LoginView {
    private JPasswordField LVpasswordField;
    private JTextField LVusernameField;
    private JButton LVloginBtn;
    private JPanel LVpanel;

    public JTextField getLVusernameField() {
        return LVusernameField;
    }

    public JPasswordField getLVpasswordField() {
        return LVpasswordField;
    }

    public JButton getLVloginBtn() {
        return LVloginBtn;
    }

    public JPanel getLVpanel() {
        return LVpanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        LVpanel = new GradientPanel();
    }
}
