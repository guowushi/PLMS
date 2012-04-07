package PLMS.AreaJTree.TreeNode;

import PLMS.AreaJTree.AddNodeEventListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-11
 * Time: 下午6:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class MyTreeNode extends DefaultMutableTreeNode{
    protected static LinkedList<AddNodeEventListener> eventListensers=new LinkedList<AddNodeEventListener>();
    protected static LinkedList<ActionListener> actionListeners=new LinkedList<ActionListener>();
    /**
     * 用于菜单项被单击的事件监听器
     * */
    public static void addActionListener(ActionListener l){
        if(!actionListeners.contains(l))
            actionListeners.add(l);
    }
    public static void removeActionListener(ActionListener l){
        actionListeners.remove(l);
    }
    public static void addAddNodeEventListener(AddNodeEventListener l){
        eventListensers.add(l);
    }
    public static void removeAddNodeEventListener(AddNodeEventListener l){
        eventListensers.remove(l);
    }

    public  static MyTreeNode getMyTreeNode(String type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c=Class.forName(type);
        return  (MyTreeNode)c.newInstance();
    }
    
    

    public abstract  Icon getIcon();
    public abstract void setMenuItem();
    protected static JPopupMenu menu=new JPopupMenu();
    public  void showMenu(Component component,int x,int y){
        resetMenu();
        setMenuItem();

        menu.show(component,x,y);
    }

    /**重置菜单*/
    public void resetMenu(){
        menu.setVisible(false);
        menu=new JPopupMenu();
    }

}
