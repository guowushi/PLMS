package PLMS.AreaJTree;

import PLMS.AreaJTree.TreeNode.MyTreeNode;
import PLMS.AreaJTree.UserDataForTreeNode.UserData;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-3
 * Time: 下午8:02
 * To change this template use File | Settings | File Templates.
 */
public class CellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,
                                                  boolean selected,
                                                  boolean expanded,
                                                  boolean leaf,
                                                  int row,
                                                  boolean hasFocus)
    {
        JLabel
                c=(JLabel) super.getTreeCellRendererComponent(tree,value,selected,expanded,leaf,row,hasFocus);

        try{
            MyTreeNode node=(MyTreeNode)value;
        c.setIcon(node.getIcon());
        }catch (NullPointerException ex){
            ex.printStackTrace();
        } catch (ClassCastException ex){
            ex.printStackTrace();
        }
        
        return c;
    }
}
