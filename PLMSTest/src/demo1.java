import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-4
 * Time: 下午8:45
 * To change this template use File | Settings | File Templates.
 */
public class demo1 {
    public static void main(String []args){
        JFrame frame=new JFrame();
        M1 m1=new M1();
        m1.setText("Click Me");
        m1.setBorder(new TitledBorder("M1"));
        M2 m2=new M2();
        m2.setBorder(new TitledBorder("M2"));
        m1.AddIListener(m2.l);
        frame.setLayout(new GridLayout(1,2));
        frame.add(m1);
        frame.add(m2);
        frame.setVisible(true);
    }
    
    
}
class M1 extends JLabel implements I
{
    List<I> eventlist=new ArrayList<I>();
    public M1(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i=0;i<eventlist.size();i++)
                    eventlist.get(i).fun();
            }
        });
    }
    public void AddIListener(I i){
        eventlist.add(i);
    }

    @Override
    public void fun() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
class M2 extends  JLabel{
    I l=new I() {
        @Override
        public void fun() {
            M2.this.setText("M1被单击,时间"+System.currentTimeMillis());
        }
    };

}



interface I {
    public void fun();
}
