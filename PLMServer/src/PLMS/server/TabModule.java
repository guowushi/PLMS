package PLMS.server;

import PLMS.AreaJTree.AreaJTreeTalkId;
import PLMS.AreaJTree.TreeNode.MyTreeNode;
import PLMS.AreaJTree.UserDataForTreeNode.UserData;
import PLMS.Base.Module;
import PLMS.Base.WidgetBase;
import PLMS.DataUnits.AbstractFn;
import PLMS.GUI.GUIUtil;
import PLMS.server.panes.GridPane;
import PLMS.server.panes.OtherTab;
import PLMS.server.panes.SettingTab;
import PLMS.talk.EventEx.ActionEventEx;

import com.jidesoft.swing.JideTabbedPane;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Tab模块
 */
public class TabModule extends Module {
    private JideTabbedPane mainTab;    // Tab组件
    private SettingTab tab_setting;
    private WidgetBase settingBase;
    private JPanel tab_others;
    private GridPane tab_monitor;
    private JPanel tab_addNode;

    // -------------------------------------------------------------------
    HashMap<Integer, String[]> map = new HashMap<Integer, String[]>();    //参数设置影射
    {
        map.put(new Integer(AreaJTreeTalkId.MENU_TERMINAL_COMMUNICATION), new String[]{"PLMS.server.panes.setting_F1", "PLMS.DataUnits.AFN04.F1"});
        map.put(new Integer(AreaJTreeTalkId.MENU_IP_PORT), new String[]{"PLMS.server.panes.setting_F3", "PLMS.DataUnits.AFN04.F3"});
        map.put(new Integer(AreaJTreeTalkId.MENU_PHONE_SMS), new String[]{"PLMS.server.panes.setting_F4", "PLMS.DataUnits.AFN04.F4"});
        map.put(new Integer(AreaJTreeTalkId.MENU_MSG_AUTH_PARAM), new String[]{"PLMS.server.panes.setting_F10", "PLMS.DataUnits.AFN04.F10"});
    }
    // ---------------------------------------------------------------------------

    // ---------------------------------------------------------------------------
    public TabModule() {
        init();
        setLayout(new BorderLayout());
        add(mainTab);
    }

    public ActionListener getAlForAddNode() {
        return alForAddNode;
    }

    public JPanel getActiveTab() {
        JPanel panel = (JPanel) mainTab.getSelectedComponent();
        return panel;
    }

    public ActionListener getAlforSettingMenuItem() {
        return alforSettingMenuItem;
    }

    /**
     *  Tab界面初始化
     */
    protected void init() {
        mainTab = new JideTabbedPane();
        mainTab.setHideOneTab(false);
        mainTab.setSelectedTabFont(new Font(mainTab.getSelectedTabFont().getName(), Font.BOLD, mainTab.getSelectedTabFont().getSize()));
        mainTab.setShowCloseButton(false);
        mainTab.setTabPlacement(4);
        // --------------添加第1个Tab-----------------
        tab_monitor = new GridPane();
        // --------------添加第2个Tab-----------------
        tab_setting=new SettingTab();
        // --------------添加第3个Tab-----------------
        tab_others = new OtherTab();

        // --------------------------------------------
        mainTab.addTab("系统状态", tab_monitor);
        mainTab.addTab("参数设置", tab_setting);
        mainTab.addTab("其他", tab_others);
    }

    ActionListener alForAddNode = new ActionListener() {
        boolean lock = false;
        @Override
        public void actionPerformed(ActionEvent e) {
            ActionEventEx ex = (ActionEventEx) e;
            try {
                MyTreeNode node = (MyTreeNode) e.getSource();
                UserData ud = (UserData) node.getUserObject();
                if (lock) {
                    JOptionPane.showConfirmDialog(mainTab, "你确定要放弃当前正在进行的编辑吗？", "添加", JOptionPane.YES_NO_OPTION);
                }

                if (tab_addNode == null) {
                    tab_addNode = new JPanel(new BorderLayout());
                    JButton btnOk = new JButton("确定");
                    JButton btnCancel = new JButton("取消");
                    JPanel group = new JPanel();
                    DefaultTableModel tm = new DefaultTableModel(new String[]{"名称"}, 1);
                    JTable table = new JTable(tm);
                    group.add(btnOk);
                    group.add(btnCancel);
                    tab_addNode.add(new JScrollPane(table), BorderLayout.CENTER);
                    tab_addNode.add(group, BorderLayout.SOUTH);

                    mainTab.addTab("添加", tab_addNode);
                    mainTab.setSelectedComponent(tab_addNode);
                    mainTab.updateUI();
                }
                switch (ex.getTalkId()) {
                    case AreaJTreeTalkId.ADD_AREA:
                        break;
                    case AreaJTreeTalkId.ADD_POWERSUBSTATION:
                        break;
                    case AreaJTreeTalkId.ADD_COLLECTION_POINT:
                        break;
                    case AreaJTreeTalkId.ADD_LINE:
                        break;
                    case AreaJTreeTalkId.ADD_TERMINAL:
                        break;
                }

            } catch (ClassCastException ex1) {

            }

            //To change body of implemented methods use File | Settings | File Templates.
        }
    };
    ActionListener alforSettingMenuItem = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ActionEventEx ex = (ActionEventEx) e;
            String[] settingPanel = map.get(new Integer(ex.getTalkId()));
            if (settingPanel != null) {
                Class panel = null;
                Class fn = null;
                try {
                    panel = Class.forName(settingPanel[0]);
                    fn = Class.forName(settingPanel[1]);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                AbstractFn fnData;
                try {
                    fnData = (AbstractFn) fn.newInstance();
                    Constructor constructor = panel.getConstructor(fnData.getClass());
                    settingBase = (WidgetBase) constructor.newInstance(fnData);
                    mainTab.setSelectedComponent(tab_setting);
                    tab_setting.getMainArea().removeAll();
                    tab_setting.getMainArea().setLayout(new BorderLayout());
                    tab_setting.getMainArea().add(settingBase.getContentPane(), BorderLayout.CENTER);
                    tab_setting.getMainArea().updateUI();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }
        }
    };

    private void load_setting_ui()
    {

    }


}

