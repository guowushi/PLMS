import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-17
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */
public class d {
    private JTextField dssdsdTextField;
    private JLabel label1;
    private JPanel panel1;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("d");
        frame.setContentPane(new d().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
        panel1 = new javax.swing.JPanel();
        panel1.setLayout(new java.awt.GridBagLayout());
        label1 = new javax.swing.JLabel();
        java.awt.GridBagConstraints gbc;
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        panel1.add(label1, gbc);
        dssdsdTextField = new javax.swing.JTextField();
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panel1.add(dssdsdTextField, gbc);
    }

    /**
     * @noinspection ALL
     */
    public javax.swing.JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}