package PLMS.server;

import PLMS.GUI.LoginDialog;

import javax.swing.*;

import java.awt.*;

/**
 * 主站程序
 */
public class MainPrograme {



    //-----------------------------------------
    public MainPrograme() {
    
    }


    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            //   jb.updateUI();
            //  jc.updateUI();

        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
         MainStationFrame frame;     //主窗体
        StartProgess.startProgess.setMaxValue(16);
        StartProgess.startProgess.setText("init...");
        frame = new MainStationFrame("电力管理系统", 1, 1);
        long t1 = System.currentTimeMillis();
        // ------------ 主窗体对象 --------------------
        LoginDialog login = new LoginDialog(null, true, "");
        if (login.getAnswer() == true) {
            frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
            frame.init();
            frame.setVisible(true);
            StartProgess.startProgess.setVisible(false);
        }
        System.out.println("实例化:" + (System.currentTimeMillis() - t1));



    }


 


    






}

// -------------------------------------------------------------------------------------------------------------


