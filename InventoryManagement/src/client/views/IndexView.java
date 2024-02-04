package client.views;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class IndexView{
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
