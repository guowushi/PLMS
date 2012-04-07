package PLMS.GUI;

import javax.swing.*;
import java.util.Collection;

/**
 * 菜单对象类
 */
public class MyMenu {
    private String menuName;
    private int menuID;
    private ImageIcon menuICO;
    private int parentMenuID;
    private boolean  isLeaf=false;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public ImageIcon getMenuICO() {
        return menuICO;
    }

    public void setMenuICO(ImageIcon menuICO) {
        this.menuICO = menuICO;
    }

    public int getParentMenuID() {
        return parentMenuID;
    }

    public void setParentMenuID(int parentMenuID) {
        this.parentMenuID = parentMenuID;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

 /*   public Collection childMenus() ;
    public getChild(String s);
    public add(Menu menu);
    public remove(MyMenu menu);*/
}
