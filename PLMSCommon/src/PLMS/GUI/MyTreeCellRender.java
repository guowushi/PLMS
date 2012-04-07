package PLMS.GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.

 */
public class MyTreeCellRender extends DefaultTreeCellRenderer {
    ImageIcon tutorialIcon;
    public MyTreeCellRender() {
        tutorialIcon = new ImageIcon("images/middle.gif");
    }
    // ---------------------------------------------------------

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        setToolTipText("This book is in the Tutorial series.");
        return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);    //To change body of overridden methods use File | Settings | File Templates.
    }

    // ----------------------------------------------------------


}
