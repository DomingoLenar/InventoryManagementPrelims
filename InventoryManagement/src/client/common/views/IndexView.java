package client.common.views;

import javax.swing.*;

public class IndexView {
    private JButton IVloginBtn;
    private JButton SUsignUpBtn;
    private JPanel IVpanel;

    public JPanel getIVpanel() {
        return IVpanel;
    }

    public JButton getIVloginBtn() {
        return IVloginBtn;
    }

    public JButton getSUsignUpBtn() {
        return SUsignUpBtn;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        IVpanel = new GradientPanel();
    }

}
