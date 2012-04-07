package PLMS.AreaJTree.TreeNode;

import PLMS.AreaJTree.UserDataForTreeNode.ForTerminal;
import PLMS.AreaJTree.UserDataForTreeNode.UserData;
import PLMS.talk.EventEx.ActionEventEx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 */
public class TerminalNode extends MyTreeNode{
    @Override
    public Icon getIcon() {
        ForTerminal ud=(ForTerminal)this.getUserObject();
        boolean  onLine=ud.isOnLine();
        if(onLine)
            return  new ImageIcon(this.getClass().getResource("images/online.png"));
        else
            return  new ImageIcon(this.getClass().getResource("images/offline.png"));
        //To change body of implemented methods use File | Settings | File Templates.
    }
    ActionListener al=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            e.setSource(TerminalNode.this);
            menu.setVisible(false);
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }  ;
    class AL1 implements ActionListener {
        int talkId=0;
        public AL1(int tid){
            talkId=tid;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            for(int i=0;i< MyTreeNode.actionListeners.size();i++){
                MyTreeNode.actionListeners.get(i).actionPerformed(new ActionEventEx(e,talkId));
            }
            menu.setVisible(false);
        }
    }
    @Override
    public void setMenuItem() {

        JMenuItem item=menu.add("添加采集点");
        JMenu item1=new JMenu("设置参数");
        JMenuItem []setting={
                new JMenuItem("终端通信参数设置"),
                new JMenuItem("终端中继转发设置"),
                new JMenuItem("主站IP地址和端口"),
                new JMenuItem("主站电话号码和短信中心号码"),
                new JMenuItem("终端消息认证参数"),
                new JMenuItem("终端组地址"),
                new JMenuItem("终端抄表日"),
                new JMenuItem("终端事件记录配置"),
                new JMenuItem("终端配置数量表"),
                new JMenuItem("终端电能表/交流采样装置配置"),
                new JMenuItem("终端脉冲配置参数"),
                new JMenuItem("终端状态量输入参数"),
                new JMenuItem("终端电压/电流模拟量配置"),
                new JMenuItem("终端总加组配置"),
                new JMenuItem("有功总电能差动越限事件"),
                new JMenuItem("虚拟专网用户名\\密码"),
                new JMenuItem("终端保安定值"),
                new JMenuItem("终端功控时段"),
                new JMenuItem("终端时段功控定制浮动系数"),
                new JMenuItem("终端月电能量控定值浮动系数"),
                new JMenuItem("终端电能量费率时段和费率数"),
                new JMenuItem("终端电能量费率"),
                new JMenuItem("终端催费告警参数"),
                new JMenuItem("终端抄表间隔设置"),
                new JMenuItem("终端抄表间隔")
        };
        for(int i=0;i<setting.length;i++){
            setting[i].addActionListener(new AL1(i));
            item1.add(setting[i]);
        }
        menu.add(item1);
        item.addActionListener(al);



    }
  
  
}
