package PLMS.server.panes.setting;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 */
public class ParamSetting extends JPanel {
    private JTaskPane taskPane = new JTaskPane();
    private JScrollPane lstPane=new JScrollPane();
    private JScrollPane helpPane=new JScrollPane();
    private JScrollPane settingPane=new JScrollPane();
    private JPanel  actionPane=new JPanel();
    private JPanel root;

    public void  initTaskPane()
    {
        JTaskPaneGroup systemGroup = new JTaskPaneGroup();
        systemGroup.setTitle("终端参数");
        systemGroup.setToolTipText("ass");
        systemGroup.setSpecial(true);
        taskPane.add( systemGroup);
        systemGroup.add(new JLabel("ce1"));
        systemGroup.add(new JButton("ce2"));
        //----------------------------------------------
        JTaskPaneGroup detailsGroup = new JTaskPaneGroup();
        detailsGroup.setTitle(("测量点参数"));
        detailsGroup.setScrollOnExpand(true);
        taskPane.add(detailsGroup);
    }
    public void TaskGroup1()
    {
        String listItem[] = {"苹果","香蕉","橘子","核桃"};
        JList actionList=new JList(listItem);
        lstPane.setViewportView(actionList);
    }
    public void   initLayout(JPanel root)
    {


        root.setLayout(new GridBagLayout());
        root.setPreferredSize(new Dimension(600, 400));
        GridBagConstraints cc=new GridBagConstraints();
        //------------------------------
        cc.gridx=0;        cc.gridy=0;
        cc.gridwidth=1;    cc.gridheight=2;
        cc.fill=GridBagConstraints.BOTH;
        cc.anchor=GridBagConstraints.NORTHWEST;
        cc.weightx=0.15;cc.weighty=0;
         cc.insets=new Insets(2,2,2,2);
        root.add(lstPane,cc);
        //---------------------------
        cc.gridx=1;        cc.gridy=0;
        cc.gridwidth=1;    cc.gridheight=1;
        cc.weightx=0.85;cc.weighty=0.2;
        root.add(helpPane,cc);
       //------------------------
        cc.gridx=1;        cc.gridy=1;
        cc.gridwidth=1;    cc.gridheight=1;
        cc.weightx=0.7;cc.weighty=0.8;
        root.add(settingPane,cc);
    }
    public static void main(String[] args){

        ParamSetting root=new ParamSetting();


        root.initLayout(root);
       // root.initTaskPane() ;
        root.TaskGroup1();

        JFrame frame = new JFrame("GridPane");

        frame.setLayout(new BorderLayout());
        frame.setContentPane(root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
