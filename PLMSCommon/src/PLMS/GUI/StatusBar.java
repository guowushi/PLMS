package PLMS.GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * 自定义的状态栏
 */
public class StatusBar extends JPanel{
    private JLabel []jLabels;
    public StatusBar(){
       jLabels=new JLabel[1];
        jLabels[0]=new JLabel();
        setLayout(new GridLayout());
        jLabels[0].setBorder(new EtchedBorder(EtchedBorder.RAISED));
        jLabels[0].setText("Status");
        add(jLabels[0]);
        
    }

    public StatusBar(Object[] infos)
     {
          updateStatusBar(infos);
     }
    public void  updateStatusBar(int index,Object obj){
        jLabels[index].setText(obj.toString());
    }
     public void  updateStatusBar(Object[]  infos)
     {
         jLabels=new JLabel[infos.length];
         this.setLayout(new GridLayout());
         EtchedBorder border=new EtchedBorder(EtchedBorder.RAISED);

         for(Object o:infos)
         {
             JLabel lbl=new JLabel();
             lbl.setText(infos.toString());
             lbl.setBorder(border);
             this.add(lbl);
         }
     }

}
