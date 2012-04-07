package PLMS.AreaJTree;

import PLMS.AreaJTree.TreeNode.TerminalNode;
import PLMS.AreaJTree.UserDataForTreeNode.ForTerminal;
import PLMS.AreaJTree.UserDataForTreeNode.UserData;
import com.jidesoft.swing.CheckBoxTree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-4
 * Time: 上午10:26
 * To change this template use File | Settings | File Templates.
 */

/**
 * 使用TerminalNodeManager.getManager()获取本类的唯一实例。
 * */
public class TerminalNodeManager implements Runnable{
    long LEFTTIME=15000;
    Map<Integer,ForTerminal> map= Collections.synchronizedMap(new HashMap<Integer,ForTerminal>())  ; //创建一个同步map
    public CheckBoxTree tree;
    public static TerminalNodeManager Manager=new TerminalNodeManager();
    private TerminalNodeManager(){
        ForTerminal.addPropertyChangeListener(new onlineListener());
    }
    public static TerminalNodeManager getManager(){return  Manager;}
    @Override
    public void run() {
        ForTerminal node;
        while (true){
            Iterator it=  map.values().iterator();
            while (it.hasNext()){
                node=(ForTerminal) it.next();
                long time= System.currentTimeMillis() ;
                if(time-node.getTime()>=LEFTTIME){
                    node.setOnline(false);
                }
            }

            try {
                Thread.sleep(LEFTTIME);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }
    class onlineListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getPropertyName()=="onLine"){
                tree.repaint();
            }
        }
    }
    /**
     * 设置地址为address的终端在线
     * @param address 终端地址
     *
     * */
    public void setTerminalNodeOnLine(int address){
        ForTerminal ud=map.get(new Integer(address));
        if(ud!=null){
            ud.setOnline(true);  //设置上线
            ud.setTime(System.currentTimeMillis()); //设置上线时间
        }
    }
    /**
     * 向管理器中添加一个终端节点
     * */
    public void AddTerminalNode(ForTerminal node){

           map.put(new Integer(node.getAddress()),node);

    }

    public void setLeftTime(long leftTiem){
       LEFTTIME=leftTiem;
    }
    /**
     * 获取终端生命长度
     * */
    public long getLeftTime(){
        return LEFTTIME;
    }
}