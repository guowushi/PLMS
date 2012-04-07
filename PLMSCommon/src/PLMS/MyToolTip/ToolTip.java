package PLMS.MyToolTip;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.Size;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-2-25
 * Time: 下午10:45
 * To change this template use File | Settings | File Templates.
 */
public class ToolTip extends JWindow implements Runnable{
    public void setLife(long life) {
        this.life = life;
    }
    protected Container container;
    protected Thread showThread;
    protected   long endTime;
    public void setComponet(JComponent componet) {
        if(componet!=this.componet){
            endTime=life+System.currentTimeMillis();//变化了与提示相关联的组件，提示显示的结束时间重设
        this.componet = componet;
        container=setting();      //添加事件监听器
        }
    }

    public JComponent getComponet() {
        return componet;
    }

    protected JComponent componet;
    
    protected long life;

    public long getLife() {
        return life;
    }

    protected JLabel text=new JLabel();
    {
        text.setBorder(new LineBorder(Color.gray));
       setContentPane(text);
       setBackground(new Color(184,207,229));
    }
    public ToolTip(){
        showThread=new Thread(this);
        text.setText("I am ToolTip");
        Dimension sz=getSize1();
        life=5000;
        setBounds(0,0,sz.width+10,sz.height+10);
    }

    public ToolTip(String str,Point position){
        showThread=new Thread(this);
       text.setText(str);
        Dimension sz=getSize1();
       setBounds(position.x,position.y,sz.width,sz.height);
    }
    public String getText(){return text.getText();}
    public void  setText(String text)
    {
        this.text.setText(text);
        Dimension sz=getSize1();
        this.setSize(sz);
    }
    public void Show(){
         if(showThread!=null)
            showThread.interrupt();
       this.setVisible(true);
        showThread=new Thread(this);
        showThread.start();
    }
    protected Dimension getSize1(){
        Font f=text.getFont();
        FontMetrics fm=sun.font.FontDesignMetrics.getMetrics(f);
        Dimension sz= new Dimension ();
        sz.width=fm.stringWidth(text.getText())+10;
        sz.height=fm.getHeight()+10;
        return sz;

    }
    protected Container getOutContainer(){
        Container c=componet;
        while (c.getParent()!=null){
            c=c.getParent();
        }
        return c;
    }
    protected Container setting(){
        Container c=componet;
        Point comp=componet.getLocation();
        
        while (c.getParent()!=null){
            c=c.getParent();
            comp.x+=c.getLocation().x;
            comp.y+=c.getLocation().y;
            
        }
        Point conp=c.getLocation();
        comp.x-=conp.x;
        comp.y-=conp.y;
        comp.y+=componet.getHeight();
        this.setLocation(comp.x+conp.x,comp.y+conp.y);
        if(c!=container)//  消除重复添加事件监听器
        c.addComponentListener(new ComponentEventListener(this,c.getLocation()));
        return c;
    }
    @Override
    public void run() {
        endTime=life+System.currentTimeMillis();
        while (true){
            if(endTime<=System.currentTimeMillis())
            {
                setVisible(false);
                break;
            }
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
class ComponentEventListener implements ComponentListener {
    ToolTip tt;
    Point oldPosition;
    public void setToolTip(ToolTip tt){
        this.tt=tt;
    }
    public ComponentEventListener(ToolTip tt,Point oldPosition){
        this.tt=tt;
        this.oldPosition
                =oldPosition;
    }
    @Override
    public void componentResized(ComponentEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        Component com=(Component)e.getSource();
        Point newPoint=new Point();
        Point ttPoint=tt.getLocation();
        newPoint.x=ttPoint.x-oldPosition.x+com.getLocation().x;
        newPoint.y=ttPoint.y-oldPosition.y+com.getLocation().y;
        tt.setLocation(newPoint);
        oldPosition.x=com.getLocation().x;
        oldPosition.y=com.getLocation().y;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
