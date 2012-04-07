package PLMS.AreaJTree.TreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-11
 * Time: 下午6:17
 * To change this template use File | Settings | File Templates.
 */
public class AreaNode extends MyTreeNode{
    @Override
    public Icon getIcon() {
        return  new ImageIcon(this.getClass().getResource("images/house.png"));
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMenuItem() {

        JMenuItem item=menu.add("添加供电所");
        JMenuItem item1=menu.add("重命名");
       
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.

                menu.setVisible(false);
            }
        });

    }
}
