package PLMS.AreaJTree.TreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-11
 * Time: 下午6:18
 * To change this template use File | Settings | File Templates.
 */
public class PowerSubstationNode extends MyTreeNode{
    @Override
    public Icon getIcon() {
        return  new ImageIcon(this.getClass().getResource("images/house.png"));
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMenuItem() {

        JMenuItem item=menu.add("添加线路");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }
}
