package PLMS.server.panes;

import PLMS.Base.WidgetBase;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.apache.commons.configuration.*;

import javax.swing.*;
import java.awt.*;

/**
 * 测量点参数限制
 */
public class setting_F26 extends WidgetBase {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private XMLConfiguration config;

    public setting_F26() {
        try {
            config = new XMLConfiguration("setting_F26.xml");

            System.out.print(config.getEncoding());
        } catch (ConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        final JLabel label1 = new JLabel();
        label1.setText(config.getString("setting_F26.label1.name", "电压合格上限"));
        CellConstraints cc = new CellConstraints();
        panel1.add(label1, cc.xy(1, 1));
        textField1 = new JTextField();
        panel1.add(textField1, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label2 = new JLabel();
        label2.setText("电压合格下限");
        panel1.add(label2, cc.xy(1, 3));
        textField2 = new JTextField();
        panel1.add(textField2, cc.xy(3, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label3 = new JLabel();
        label3.setText("电压断相门限");
        panel1.add(label3, cc.xy(1, 5));
        textField3 = new JTextField();
        panel1.add(textField3, cc.xy(3, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label4 = new JLabel();
        label4.setText("过压门限");
        panel1.add(label4, cc.xy(1, 7));
        textField4 = new JTextField();
        panel1.add(textField4, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label5 = new JLabel();
        label5.setText("欠压门限");
        panel1.add(label5, cc.xy(1, 9));
        textField5 = new JTextField();
        panel1.add(textField5, cc.xy(3, 9, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label6 = new JLabel();
        label6.setText("过流门限");
        panel1.add(label6, cc.xy(1, 11));
        textField6 = new JTextField();
        panel1.add(textField6, cc.xy(3, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label7 = new JLabel();
        label7.setText("额定电流门限");
        panel1.add(label7, cc.xy(1, 13));
        textField7 = new JTextField();
        panel1.add(textField7, cc.xy(3, 13, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label8 = new JLabel();
        label8.setText("零序电流上限");
        panel1.add(label8, cc.xy(1, 15));
        final JLabel label9 = new JLabel();
        label9.setText("视在功率上上限");
        panel1.add(label9, cc.xy(1, 17));
        final JLabel label10 = new JLabel();
        label10.setText("视图功率上限");
        panel1.add(label10, cc.xy(1, 19));
        final JLabel label11 = new JLabel();
        label11.setText("三相电压不平衡限值");
        panel1.add(label11, cc.xy(1, 21));
        final JLabel label12 = new JLabel();
        label12.setText("三相电流不平衡限值");
        panel1.add(label12, cc.xy(1, 23));
        final JLabel label13 = new JLabel();
        label13.setText("连续失压时间限制");
        panel1.add(label13, cc.xy(1, 25));
        textField8 = new JTextField();
        panel1.add(textField8, cc.xy(3, 15, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField9 = new JTextField();
        panel1.add(textField9, cc.xy(3, 17, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField10 = new JTextField();
        panel1.add(textField10, cc.xy(3, 19, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField11 = new JTextField();
        panel1.add(textField11, cc.xy(3, 21, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField12 = new JTextField();
        panel1.add(textField12, cc.xy(3, 23, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField13 = new JTextField();
        panel1.add(textField13, cc.xy(3, 25, CellConstraints.FILL, CellConstraints.DEFAULT));
    }

    @Override
    protected Object collectValues() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Container getContentPane() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("setting_F26");
        frame.setContentPane(new setting_F26().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        System.out.println(System.getProperty("user.dir"));
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        // $$$setupUI$$$();
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
        panel1.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        final JLabel label1 = new JLabel();
        label1.setText("电压合格上限");
        CellConstraints cc = new CellConstraints();
        panel1.add(label1, cc.xy(1, 1));
        textField1 = new JTextField();
        panel1.add(textField1, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label2 = new JLabel();
        label2.setText("电压合格下限");
        panel1.add(label2, cc.xy(1, 3));
        textField2 = new JTextField();
        panel1.add(textField2, cc.xy(3, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label3 = new JLabel();
        label3.setText("电压断相门限");
        panel1.add(label3, cc.xy(1, 5));
        textField3 = new JTextField();
        panel1.add(textField3, cc.xy(3, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label4 = new JLabel();
        label4.setText("过压门限");
        panel1.add(label4, cc.xy(1, 7));
        textField4 = new JTextField();
        panel1.add(textField4, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label5 = new JLabel();
        label5.setText("欠压门限");
        panel1.add(label5, cc.xy(1, 9));
        textField5 = new JTextField();
        panel1.add(textField5, cc.xy(3, 9, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label6 = new JLabel();
        label6.setText("过流门限");
        panel1.add(label6, cc.xy(1, 11));
        textField6 = new JTextField();
        panel1.add(textField6, cc.xy(3, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label7 = new JLabel();
        label7.setText("额定电流门限");
        panel1.add(label7, cc.xy(1, 13));
        textField7 = new JTextField();
        panel1.add(textField7, cc.xy(3, 13, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label8 = new JLabel();
        label8.setText("零序电流上限");
        panel1.add(label8, cc.xy(1, 15));
        final JLabel label9 = new JLabel();
        label9.setText("视在功率上上限");
        panel1.add(label9, cc.xy(1, 17));
        final JLabel label10 = new JLabel();
        label10.setText("视图功率上限");
        panel1.add(label10, cc.xy(1, 19));
        final JLabel label11 = new JLabel();
        label11.setText("三相电压不平衡限值");
        panel1.add(label11, cc.xy(1, 21));
        final JLabel label12 = new JLabel();
        label12.setText("三相电流不平衡限值");
        panel1.add(label12, cc.xy(1, 23));
        final JLabel label13 = new JLabel();
        label13.setText("连续失压时间限制");
        panel1.add(label13, cc.xy(1, 25));
        textField8 = new JTextField();
        panel1.add(textField8, cc.xy(3, 15, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField9 = new JTextField();
        panel1.add(textField9, cc.xy(3, 17, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField10 = new JTextField();
        panel1.add(textField10, cc.xy(3, 19, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField11 = new JTextField();
        panel1.add(textField11, cc.xy(3, 21, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField12 = new JTextField();
        panel1.add(textField12, cc.xy(3, 23, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField13 = new JTextField();
        panel1.add(textField13, cc.xy(3, 25, CellConstraints.FILL, CellConstraints.DEFAULT));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
