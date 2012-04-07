package PLMS.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录窗体
 */
public class LoginWindow extends JDialog{
    // Variables declaration
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField jTextField1;
    private JPasswordField jPasswordField1;
    private JButton jButton1;
    private JPanel contentPane;
// End of variables declaration


    public LoginWindow(JFrame frame)
    {
        super(frame);
        create();
        this.setVisible(true);
    }


    private void create()
    {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jPasswordField1 = new JPasswordField();
        jButton1 = new JButton();
        contentPane = (JPanel)this.getContentPane();

        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setForeground(new Color(0, 0, 255));
        jLabel1.setText("用户名:");

        jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel2.setForeground(new Color(0, 0, 255));
        jLabel2.setText("密码:");

        jTextField1.setForeground(new Color(0, 0, 255));
        jTextField1.setSelectedTextColor(new Color(0, 0, 255));
        jTextField1.setToolTipText("输入用户名");
        jTextField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jTextField1_actionPerformed(e);
            }

        });

        jPasswordField1.setForeground(new Color(0, 0, 255));
        jPasswordField1.setToolTipText("输入登录密码");
        jPasswordField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jPasswordField1_actionPerformed(e);
            }

        });

        jButton1.setBackground(new Color(204, 204, 204));
        jButton1.setForeground(new Color(0, 0, 255));
        jButton1.setText("登录");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                jButton1_actionPerformed(e);
            }

        });

        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createEtchedBorder());
        contentPane.setBackground(new Color(204, 204, 204));
        addComponent(contentPane, jLabel1, 25,10,106,18);
        addComponent(contentPane, jLabel2, 25,47,97,18);
        addComponent(contentPane, jTextField1, 110,10,183,22);
        addComponent(contentPane, jPasswordField1, 110,45,183,22);
        addComponent(contentPane, jButton1, 150,75,83,28);

        this.setTitle("电力管理系统-登录");
        this.setLocation(new Point(76, 182));
        this.setSize(new Dimension(335, 141));
       // this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    /** Add Component Without a Layout Manager (Absolute Positioning) */
    private void addComponent(Container container,Component c,int x,int y,int width,int height)
    {
        c.setBounds(x,y,width,height);
        container.add(c);
    }


    private void jTextField1_actionPerformed(ActionEvent e)
    {


    }

    private void jPasswordField1_actionPerformed(ActionEvent e)
    {

    }

    private void jButton1_actionPerformed(ActionEvent e)
    {
        //System.out.println("\njButton1_actionPerformed(ActionEvent e) called.");
        String username = new String(jTextField1.getText());
        String password = new String(jPasswordField1.getPassword());
        if(username.equals("") || password.equals("")) // If password and username is empty > Do this >>>
        {
            jButton1.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>必须输入用户名和密码</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jTextField1.setText("");
            jPasswordField1.setText("");
            jButton1.setEnabled(true);
            this.setVisible(true);
        }
        else
        {
            JLabel optionLabel = new JLabel("<HTML><FONT COLOR = Blue>You entered</FONT><FONT COLOR = RED> <B>"+username+"</B></FONT> <FONT COLOR = Blue>as your username.<BR> Is this correct?</FONT></HTML>");
            int confirm =JOptionPane.showConfirmDialog(null,optionLabel);
            switch(confirm){ // Switch > Case
                case JOptionPane.YES_OPTION: // Attempt to Login user
                    jButton1.setEnabled(false); // Set button enable to false to prevent 2 login attempts
                    break;

                case JOptionPane.NO_OPTION: // No Case.(Go back. Set text to 0)
                    jButton1.setEnabled(false);
                    jTextField1.setText("");
                    jPasswordField1.setText("");
                    jButton1.setEnabled(true);
                    break;

                case JOptionPane.CANCEL_OPTION: // Cancel Case.(Go back. Set text to 0)
                    jButton1.setEnabled(false);
                    jTextField1.setText("");
                    jPasswordField1.setText("");
                    jButton1.setEnabled(true);
                    break;

            } // End Switch > Case


        }
    }









}
