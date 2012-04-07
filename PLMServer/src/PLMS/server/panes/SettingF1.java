package PLMS.server.panes;

import PLMS.DataUnits.AFN04.F1;
import PLMS.GUI.GUIUtil;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.Size;
import com.jidesoft.swing.JideLabel;

import javax.swing.*;
import java.awt.*;

/**
 * 设置参数F1
 */
public class SettingF1 extends  SettingTab {

    private JPanel panel1;
    //------------用户交互组件----------
    private JTextField txtTimeOut;
    private JTextField txtDelayTime;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JTextField textField3;
    private JRadioButton Rad;
    private JRadioButton RadioButton;
    // -----------数据对象---------------------
    private F1 f1;

    //--------------构造函数---------------------
    public SettingF1() {
        super();
        setTitle_of_border("终端通信参数设置");
    }
    // ---------------初始化界面--------------------------
    @Override
    protected void initComponent() {
        super.initComponent();
        //-------自己的组件------------
        panel1 = new JPanel();
        GridBagLayout layout=new GridBagLayout();
      // FormLayout layout1=new FormLayout("right:m:noGrow,left:max(p;50px):grow(0.5),left:40dlu:noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow",
        //        "center:30px:noGrow,center:30px:noGrow,center:30px:noGrow,center:30px:noGrow,center:30px:noGrow,center:30px:noGrow");
       // FormLayout layout1=new FormLayout();
       // layout1.appendColumn(ColumnSpec.decode("right:m:noGrow"));
       // DefaultFormBuilder.
        panel1.setLayout(layout);

        //-----------------------------------------------------
        GridBagConstraints cc=new GridBagConstraints();

        //-----------------------------------------------
        cc.insets=new Insets(1,20,1,20);
        cc.gridwidth=1;  cc.gridheight=1;
        cc.fill=GridBagConstraints.HORIZONTAL;
        cc.anchor=GridBagConstraints.NORTHEAST;
        cc.weightx=1;    cc.weighty=1;
        cc.gridx=0;       cc.gridy=0;
        final JLabel label1 = new JLabel("超时时间(分钟)");
        panel1.add(label1, cc);

        txtTimeOut = new JTextField();
        cc.insets=new Insets(1,20,5,20);
        cc.gridx=0;       cc.gridy=1;
        panel1.add(txtTimeOut, cc);
        // --------------------------------------------------
        final JLabel label2 = new JLabel("最大传输延时(MS)");
        cc.gridx=0;       cc.gridy=2;
        panel1.add(label2, cc);

        txtDelayTime = new JTextField();
        cc.gridx=0;       cc.gridy=3;
        panel1.add(txtDelayTime, cc);


        final JLabel label5 = new JLabel();
        cc.gridx=0;       cc.gridy=4;
        label5.setText("终端等待从动站的超时时间");
        panel1.add(label5, cc);

        spinner1 = new JSpinner();
        cc.gridx=0;       cc.gridy=5;
        panel1.add(spinner1, cc);

        final JideLabel jideLabel1 = new JideLabel();
        jideLabel1.setText("终端等待从动站响应的重发次数 ");
        cc.gridx=0;       cc.gridy=6;
        panel1.add(jideLabel1, cc);

        spinner2 = new JSpinner();
        cc.gridx=0;       cc.gridy=7;
        panel1.add(spinner2, cc);





        final JideLabel jideLabel3 = new JideLabel();
        jideLabel3.setText("心跳周期 ");
        cc.gridx=0;       cc.gridy=8;
        panel1.add(jideLabel3, cc);

        textField3 = new JTextField();
        cc.gridx=0;       cc.gridy=9;
        panel1.add(textField3, cc);

        final JideLabel jideLabel2 = new JideLabel();
        jideLabel2.setText("需要主站确认的通信服务（CON=1）的标志  ");
        cc.gridx=0;       cc.gridy=10;
        panel1.add(jideLabel2, cc);

        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        cc.gridx=0;       cc.gridy=11;
        panel1.add(panel2, cc);

        Rad = new JRadioButton();
        Rad.setText("需要确认");
        panel2.add(Rad);
        RadioButton = new JRadioButton();
        RadioButton.setText("无需确认");
        panel2.add(RadioButton);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(RadioButton);
        buttonGroup.add(RadioButton);
        buttonGroup.add(Rad);

        JPanel helpPanel=new JPanel();
        helpPanel.setBorder(GUIUtil.createBorder("帮助"));
        cc.gridx=1;       cc.gridy=1;
        cc.gridwidth=1;  cc.gridheight=10;
        cc.fill=GridBagConstraints.BOTH;
        panel1.add(helpPanel,cc);
        mainArea.setViewportView(panel1);
    }

    @Override
    public void initLayout() {
        super.initLayout();

    }

    @Override
    protected void saveSetting() {
        super.saveSetting();

        JOptionPane.showMessageDialog(null,txtDelayTime.getText());
    }

    @Override
    protected void getSetting() {
        super.getSetting();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new SettingF1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
