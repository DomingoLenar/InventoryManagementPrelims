package client.common.views;

import javax.swing.*;

public class ChangePasswordView {
    private JPanel mainPanel;
    private JTextField oldPasswordField;
    private JTextField newPasswordField;
    private JButton saveButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getOldPasswordField() {
        return oldPasswordField;
    }

    public JTextField getNewPasswordField() {
        return newPasswordField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
