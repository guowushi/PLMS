package plms;


import PLMS.GUI.PLMSJFrame;
import net.infonode.docking.*;
import net.infonode.docking.util.DockingUtil;
import net.infonode.docking.util.ViewMap;
import net.infonode.gui.laf.InfoNodeLookAndFeel;
import net.infonode.util.Direction;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class main {


    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new InfoNodeLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("载入UI错误！");
        }
        // 是线程不安全的，需要放到Swing线程中运行
        SwingUtilities.invokeLater(new Runnable() {
            dockFrame frame;
            public void run() {
                frame= new dockFrame("电力管理系统",1,2);
                frame.setVisible(true);
            }


        });
    }
}
