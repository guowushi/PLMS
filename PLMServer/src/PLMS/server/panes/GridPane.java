package PLMS.server.panes;

import PLMS.DataUnits.AFN00.F1;
import PLMS.GUI.BeanTableModel;
import PLMS.GUI.myRowModel;
import PLMS.JavaBean.TerminalBean;
import PLMS.JavaBean.TerminalSessionBean;
import com.adamtaft.eb.EventBus;
import com.adamtaft.eb.EventBusService;
import com.adamtaft.eb.EventHandler;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 终端显示Grid
 */
public class GridPane extends JPanel {

    private JPanel panel1;
    private JButton button1;
    private JTable table1;
    private JPanel toolPanel;
    private JScrollPane s_pane;

    private myRowModel<TerminalSessionBean> rowModel;
    private BeanTableModel<TerminalBean> beanTableModel;


    public GridPane() {
        this.setLayout(new BorderLayout());
        this.add(panel1, "Center");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.updateUI();
            }
        });


        rowModel = new myRowModel(TerminalSessionBean.class);
        beanTableModel = new BeanTableModel<TerminalBean>(TerminalBean.class);

        beanTableModel.addColumn("连接编号", "sessionId");
        beanTableModel.addColumn("终端IP", "termailIP");
        beanTableModel.addColumn("地区", "terminalAddress");
        beanTableModel.addColumn("终端状态", "terminalStatus");
        beanTableModel.addColumn("收到/发送消息", "rwMessage");
        // beanTableModel.addColumn("收到/发送字节", "terminalID");
        table1.setModel(beanTableModel);

        EventBusService.subscribe(this);
    }

    @EventHandler
    public synchronized void refreshStatus(TerminalBean bean) {
        // IoSession session = bean.getCurrentSession();
        // dtm.addRow(data);
        //dtm.fireTableDataChanged();
        // rowModel.addRow(bean);
        if (!beanTableModel.findRow(bean)) {
            beanTableModel.addRow(bean);
        } else {
            beanTableModel.updateRow(bean);
        }
        JOptionPane.showMessageDialog(null, "消息事件");

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GridPane");
        frame.setContentPane(new GridPane().panel1);
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
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        s_pane = new JScrollPane();
        panel1.add(s_pane, BorderLayout.NORTH);
        table1 = new JTable();
        s_pane.setViewportView(table1);
        toolPanel = new JPanel();
        toolPanel.setLayout(new FormLayout("fill:d:noGrow", "center:d:noGrow"));
        panel1.add(toolPanel, BorderLayout.SOUTH);
        button1 = new JButton();
        button1.setText("Button");
        CellConstraints cc = new CellConstraints();
        toolPanel.add(button1, cc.xy(1, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}