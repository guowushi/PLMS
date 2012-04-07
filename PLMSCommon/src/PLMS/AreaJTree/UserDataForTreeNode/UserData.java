package PLMS.AreaJTree.UserDataForTreeNode;

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
 * Date: 12-3-5
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */
public  class UserData {

    public static UserData getUserData(String type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c=Class.forName(type);
        return (UserData)c.newInstance();
    }
    protected int  pid;
    protected int id;
    protected DefaultMutableTreeNode node; //与该用户数据相关联的树节点
    protected String name;  //显示名称
 

    protected int type;

    public int getPid(){return pid;}
    public void setPid(int pid){this.pid=pid;}
    public int getId(){return  id;}
    public void setId(int id){this.id=id;}
    public void setName(String name){
        this.name=name;
    }
    public void setNode(DefaultMutableTreeNode node){this.node=node;}
    public DefaultMutableTreeNode getNode(){return node;}
    public String getName(){return name;}

    public String toString(){return name;}



}
