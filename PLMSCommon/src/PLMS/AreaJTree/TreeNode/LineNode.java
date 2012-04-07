package PLMS.AreaJTree.TreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-11
 * Time: 下午6:19
 * To change this template use File | Settings | File Templates.
 */
public class LineNode extends MyTreeNode{
    @Override
    public Icon getIcon() {
        return  new ImageIcon(this.getClass().getResource("images/line.png"));//To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMenuItem() {

        JMenuItem item=menu.add("添加终端");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }
}
