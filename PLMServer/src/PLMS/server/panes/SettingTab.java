package PLMS.server.panes;

import PLMS.DataUnits.AbstractFn;
import PLMS.GUI.GUIUtil;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 参数设置界面
 */
public class SettingTab extends JPanel {
    protected  JScrollPane mainArea;      //主操作区域
    protected JPanel btnPane;       // 按钮区域
    protected AbstractFn  fn;
    protected setting_F1  f1;
    protected String title_of_border;
    // ---------------------------------------------
    public JScrollPane getMainArea()
    {
        return mainArea;
    }

    public void setTitle_of_border(String title_of_border) {
        this.title_of_border = title_of_border;
        mainArea.setBorder(GUIUtil.createBorder(this.title_of_border));
    }

    public SettingTab() {
        initLayout();
        initComponent();
    }
    public void initLayout()
    {

        mainArea = new JScrollPane();
        btnPane=new JPanel();
        // ---------整体布局----------------------
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        setLayout(new BorderLayout());


        add(mainArea, BorderLayout.CENTER);
        add(btnPane, BorderLayout.SOUTH);
        // ----------主区域界面-----------------
        mainArea.setBorder(GUIUtil.createBorder(this.title_of_border));
        // ------------按钮区域界面--------------------
        btnPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton okbtn = new JButton("保存参数");
        btnPane.add(okbtn);
        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 saveSetting();

            }
        });
        JButton btn_test = new JButton("获取参数");
        btnPane.add(btn_test);
        btn_test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSetting();
            }
        });
    }
    protected void initComponent()
    {

    }
    protected void saveSetting()
    {
       // mainArea
        JOptionPane.showMessageDialog(null,"ddd");
    }
    protected void getSetting()
    {

    }
}
