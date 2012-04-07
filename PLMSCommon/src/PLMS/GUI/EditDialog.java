package PLMS.GUI;

import PLMS.DataUnits.AFN04.F1;
import PLMS.DataUnits.AFN04.F10;
import PLMS.server.panes.setting_F1;
import PLMS.server.panes.setting_F10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 对话框
 */
public class EditDialog extends JDialog{
    private Object res;     //返回值对象
    private JScrollPane area;   // 添加组件的主区域
    private String title;   //标题
    public EditDialog(){
        this.setModal(true); //必须是模态对话框才可以做返回值
        initUI();//这里自己写些布局的业务代码
    }
    public EditDialog(Object o ){
         this();
        this.res=o;
    }
    // --------------------------------------------
    public void initUI()
    {
        this.setSize(new Dimension(400,300));
        this.setMinimumSize(new Dimension(300, 200));
      JPanel panel=new JPanel();

     this.add(panel);
     panel.setBorder(BorderFactory.createEmptyBorder(5,5,20,5));
     panel.setLayout(new BorderLayout());

        area=new JScrollPane();

        area.setBorder(BorderFactory.createBevelBorder(1));
        panel.add(area,BorderLayout.CENTER);
        JPanel btnArea=new JPanel();
        panel.add(btnArea,BorderLayout.SOUTH);
        btnArea.setLayout(new FlowLayout(FlowLayout.RIGHT,1,5));
        JButton btnOk=new JButton("确定");
        JButton btnCancel=new JButton("取消");
        btnArea.add(btnOk);
        btnArea.add(btnCancel);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                res= getReturnObject();
                dispose();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     *
     * @return
     */
    public Container getPanel()
    {
        return area;
    }
    //执行这个方法即可得到返回值.
    public Object showDialog(){

        Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();
        Dimension   frameSize   =   this.getSize();
        if   (frameSize.height   >   screenSize.height)   {
            frameSize.height   =   screenSize.height;
        }
        if   (frameSize.width   >   screenSize.width)   {
            frameSize.width   =   screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
        this.setVisible(true);
        return res;
    }

    /**
     * 执行代码，获取返回值
     */
    public Object getReturnObject()
    {
             return "aaaaaa";
    }
    public static void main(String[] args) {
        EditDialog dialog = new  EditDialog("aaa");

        dialog.setTitle("测试");
        dialog.getPanel().add(new setting_F10(new F10()).getContentPane())   ;
        Object o=dialog.showDialog();
       // dialog.pack();
        System.out.print(o.toString());
        System.exit(0);
    }

}
