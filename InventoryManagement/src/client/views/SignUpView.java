package client.views;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        SUpanel = new GradientPanel();
    }

}
