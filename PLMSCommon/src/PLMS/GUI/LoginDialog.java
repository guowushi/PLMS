package PLMS.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * 登录对话框
 */
public class LoginDialog extends JDialog  {
    private JPanel myPanel = null;
    private JPanel btnPanel =null;

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField jTextField1;
    private JPasswordField jPasswordField1;
    private JButton jButton1=new JButton();
    private JButton jButton2=new JButton();
    private JPanel contentPane;
    private boolean answer = false;
    public boolean getAnswer() {
        return answer;
    }

    // ------------------------------------------------
    public LoginDialog(JFrame frame, boolean modal, String myMessage) {
        super(frame, modal);
        createUI();
        setLocationRelativeTo(frame);
        this.setModal(true);
        setVisible(true);
    }
    private void createUI()
    {
        //-------设置默认的字体大小---------------
        try{
            Font   font   =   new   Font("宋体",Font.PLAIN,12);
            //-----------------------------------------------
            Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                if (UIManager.get(key) instanceof Font) {
                    UIManager.put(key, font);
                }
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jPasswordField1 = new JPasswordField();
       // jButton1 = new JButton();
       // jButton2 = new JButton();

        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setForeground(new Color(0, 0, 255));
        jLabel1.setBackground(new  Color(255, 255, 255));
        jLabel1.setText("用户名:");

        jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel2.setForeground(new Color(0, 0, 255));
        jLabel2.setText("密　码:");

        jTextField1.setForeground(new Color(0, 0, 255));
        jTextField1.setSelectedTextColor(new Color(0, 0, 255));
        jTextField1.setToolTipText("输入用户名");
        jTextField1.setText("李局长");
      

        jPasswordField1.setForeground(new Color(0, 0, 255));
        jPasswordField1.setToolTipText("输入登录密码");
        jPasswordField1.setText("123");


        jButton1.setBackground(new Color(204, 204, 204));
        jButton1.setForeground(new Color(0, 0, 255));
        jButton1.setText("登录");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoginDialog.this.answer=  DBUtil.checkLogin(jTextField1.getText(),jPasswordField1.getText());
                    if(LoginDialog.this.getAnswer()){
                        LoginDialog.this.setVisible(false);
                        LoginDialog.this.dispose();
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null,e1.getMessage());
                }
            }
        });
     
        jButton2.setBackground(new Color(204, 204, 204));
        jButton2.setForeground(new Color(0, 0, 255));
        jButton2.setText("退出");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginDialog.this.setVisible(false);
                LoginDialog.this.dispose();
            }
        });


        myPanel=new JPanel();
        myPanel.setLayout(new GridBagLayout());


        EtchedBorder titledBorder = (EtchedBorder)BorderFactory.createEtchedBorder();
        EmptyBorder emptyBorder=(EmptyBorder)BorderFactory.createEmptyBorder(10,25,10,25);
        CompoundBorder border=BorderFactory.createCompoundBorder(titledBorder, emptyBorder) ;
        myPanel.setBorder(border);
        GridBagConstraints c = new GridBagConstraints ();
        c.ipadx=c.ipady=0;              //组件内部的填充
        c.insets=new Insets(0,0,10,0);   //组件周边的空隙，如用weightx和weighty则不再使用Insets
        c.weightx=0;c.weighty=0;
       // c.fill=GridBagConstraints.NONE;  //
        c.gridx=0;c.gridy=0;              //组件在网格中的横向、纵向坐标
        c.gridwidth=1;  c.gridheight=1;
        c.anchor=GridBagConstraints.EAST;

        c.fill=GridBagConstraints.BOTH;
        myPanel.add(jLabel1,c);

        c.gridx=0;c.gridy=1;
        myPanel.add(jLabel2,c);

        c.gridx=1;c.gridy=0;
        c.gridwidth=2;  c.gridheight=1;
        c.weightx=1;c.weighty=0;
        c.fill=GridBagConstraints.BOTH;
        myPanel.add(jTextField1,c);

        c.gridx=1;c.gridy=1;
        c.gridwidth=4;  c.gridheight=1 ;
        c.fill=GridBagConstraints.BOTH;
        myPanel.add(jPasswordField1,c);


        btnPanel=new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
        btnPanel.add(jButton1);
        btnPanel.add(jButton2 );

        contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEtchedBorder());
        contentPane.setBackground(new Color(204, 204, 204));
        contentPane.add(myPanel,BorderLayout.CENTER);
        contentPane.add(btnPanel,BorderLayout.SOUTH);

     /*   addComponent(myPanel, jLabel1, 25, 10, 106, 18);
        addComponent(myPanel, jLabel2, 25,47,97,18);
        addComponent(myPanel, jTextField1, 110,10,183,22);
        addComponent(myPanel, jPasswordField1, 110,45,183,22);*/

        this.setTitle("电力管理系统-登录");
         this.setSize(new Dimension(335, 171));
        //this.getContentPane().setSize(335,171);
        //this.setLocationRelativeTo(null);
        this.setResizable(true);
    }
    private void addComponent(Container container,Component c,int x,int y,int width,int height)
    {
        c.setBounds(x,y,width,height);
        container.add(c);
    }
    /*//-----------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        if(e.getSource()==jButton1){
            try {
                LoginDialog.this.answer=  DBUtil.checkLogin(jTextField1.getText(),jPasswordField1.getText());
                if(LoginDialog.this.getAnswer()){
                    LoginDialog.this.setVisible(false);
                    LoginDialog.this.dispose();
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null,e1.getMessage());
            }
        }
        if(e.getSource()==jButton2){
            LoginDialog.this.setVisible(false);
            LoginDialog.this.dispose();
        }
    }*/
}
