package client.common.views;

import javax.swing.*;

public class LoginView {
    private JTextField LVpasswordField;
    private JTextField LVusernameField;
    private JButton LVloginBtn;
    private JPanel LVpanel;

    public JTextField getLVusernameField() {
        return LVusernameField;
    }

    public JTextField getLVpasswordField() {
        return LVpasswordField;
    }

    public JButton getLVloginBtn() {
        return LVloginBtn;
    }

    public JPanel getLVpanel() {
        return LVpanel;
    }

    private void createUIComponents() {
        LVpanel = new GradientPanel();
    }

}
