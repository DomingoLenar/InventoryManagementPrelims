package client.views;

import javax.swing.*;

public class SignUpView {
    private JTextField SUusernameField;
    private JPasswordField SUpasswordField;
    private JButton SUsignUpBtn;
    private JPanel SUpanel;

    public JPanel getSUpanel() {
        return SUpanel;
    }

    public JButton getSUsignUpBtn() {
        return SUsignUpBtn;
    }

    public JTextField getSUusernameField() {
        return SUusernameField;
    }

    public JPasswordField getSUpasswordField() {
        return SUpasswordField;
    }
}
