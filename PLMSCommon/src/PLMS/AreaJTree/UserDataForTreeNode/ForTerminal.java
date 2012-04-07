package PLMS.AreaJTree.UserDataForTreeNode;

import PLMS.talk.EventEx.ActionEventEx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-6
 * Time: 下午9:25
 * To change this template use File | Settings | File Templates.
 */
public class ForTerminal extends UserData{
    protected static LinkedList<PropertyChangeListener> propertyChange=new LinkedList<PropertyChangeListener>();
    public static void addPropertyChangeListener(PropertyChangeListener l){
        propertyChange.add(l);
    }
    public static void removePropertyChangeListener(PropertyChangeListener l){
        propertyChange.remove(l);
    }
    protected  boolean  onLine;
    protected long time;
    protected int address;

    public boolean isOnLine(){return onLine;}
    public void setOnline(boolean  b){
        if(b!=onLine){
            onLine=b;
            PropertyChangeEvent evt=new PropertyChangeEvent(this,"onLine",!b,b);
            for(PropertyChangeListener l :propertyChange){
                l.propertyChange(evt);
            }
        }
    }
    public void setTime(long time){
        this.time=time;
    }
    public long getTime(){return time;}
    public void setAddress(int address){
        this.address=address;
    }
    public int getAddress(){return address;}
     
}
