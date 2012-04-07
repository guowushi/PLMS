package plms.client;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 */
public class client {
    public static void main(String[] args) {
        JFrame frame = new JFrame("client1");
        clientGUI c1=new clientGUI();

        frame.setContentPane(c1.getPanel());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

       // JFrame frame2 = new JFrame("client2");
       // clientGUI c2=new clientGUI();
     //   frame2.setContentPane(c2.getPanel());
        //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  frame2.pack();
       // frame2.setVisible(true);


    }
}
