import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import com.l2fprod.common.util.ResourceManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-28
 * Time: 下午9:53
 * To change this template use File | Settings | File Templates.
 */
public class l2fprod_taskPane {
   // static ResourceManager RESOURCE = ResourceManager.get(l2fprod_taskPane.class);
    public static void main(String[] args) {


        JFrame frame = new JFrame();
        JTaskPane taskPane = new JTaskPane();

        JTaskPaneGroup systemGroup = new JTaskPaneGroup();
        systemGroup.setTitle("ddd");
        systemGroup.setToolTipText("ass");
        systemGroup.setSpecial(true);
        taskPane.add( systemGroup);

        JTaskPaneGroup detailsGroup = new JTaskPaneGroup();
        detailsGroup.setTitle(("Main.tasks.details"));
        detailsGroup.setScrollOnExpand(true);
        taskPane.add(detailsGroup);

        frame.add(new JScrollPane(taskPane), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }

}
