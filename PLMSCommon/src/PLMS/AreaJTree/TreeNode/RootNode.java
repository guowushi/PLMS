package PLMS.AreaJTree.TreeNode;

import PLMS.AreaJTree.AreaJTreeTalkId;
import PLMS.AreaJTree.NodeType;
import PLMS.talk.EventEx.ActionEventEx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-11
 * Time: 下午6:44
 * To change this template use File | Settings | File Templates.
 */
public class RootNode extends MyTreeNode{

    @Override
    public Icon getIcon() {

        return  new ImageIcon(this.getClass().getResource("images/world.png"));
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMenuItem() {

        JMenuItem item=menu.add("添加供电局");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                for(int i=0;i<actionListeners.size();i++){
                    e.setSource(RootNode.this);
                    actionListeners.get(i).actionPerformed(new ActionEventEx(e, AreaJTreeTalkId.ADD_AREA));

                }
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


    }
}
