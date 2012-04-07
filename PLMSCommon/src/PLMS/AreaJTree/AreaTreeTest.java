package PLMS.AreaJTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-3
 * Time: 下午7:36
 * To change this template use File | Settings | File Templates.
 */
public class AreaTreeTest extends JFrame{
    AreaTree checkBoxTree;
    static  int address=0;
    public AreaTreeTest(){


        checkBoxTree=AreaTree.getAreaTree();

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new JScrollPane(checkBoxTree),BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);
    }
    




    public static void main(String []arge){
        new AreaTreeTest();
       // new MsgSimulator();
    }
}
