package PLMS.server.panes;

import PLMS.Base.WidgetBase;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * 终端抄表间隔时间
 */
public class setting_F24 extends WidgetBase {
    private JPanel panel1;
    private JTextField textField1;

    public setting_F24() {
    }

    @Override
    protected Object collectValues() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Container getContentPane() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:d:grow", "center:d:noGrow"));
        final JLabel label1 = new JLabel();
        label1.setText("终端抄表间隔时间");
        CellConstraints cc = new CellConstraints();
        panel1.add(label1, cc.xy(1, 1));
        textField1 = new JTextField();
        panel1.add(textField1, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
