package PLMS.server;

import PLMS.Base.Module;
import PLMS.GUI.MyJFrame;
import PLMS.talk.CustomEvent;
import PLMS.talk.CustomEventListener;
import PLMS.talk.LoudSpeaker;
import com.adamtaft.eb.EventBus;
import com.jidesoft.swing.JideScrollPane;
import org.apache.mina.core.session.IoSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.util.Map;

/**
 * 主站的主窗体
 */
public class MainStationFrame extends MyJFrame {
    private Server server;              // 服务器对象
    private TreeModule treeModule;      //树模块
    private TabModule tabModule;        //tab 模块
    // -----------------布局 对象----------------
    private JSplitPane split_main;
    private JSplitPane split_main_right;
    private JideScrollPane statusScroll;
    private JideScrollPane mainScollPane;

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    private EventBus eventBus;

    //------------------构造方法-----------------------
    public MainStationFrame(String title, int role,int moduleID) {
        super(title, role,moduleID);
    }

/*    public MainStationFrame(String title) {
        super(title);
    }

    public MainStationFrame(int role) {
        super(role);
    }*/
    // --------------------重写的方法----------------------
    @Override
    protected void defaultSetting() {
        super.defaultSetting();
    }
    @Override
    protected void initUI() {

        // 初始化界面
        StartProgess.startProgess.plus();
        treeModule = new TreeModule();
        StartProgess.startProgess.plus();
        tabModule = new TabModule();
        StartProgess.startProgess.plus();
        split_main = new JSplitPane();
        StartProgess.startProgess.plus();
        split_main.setOrientation(1);
        StartProgess.startProgess.plus();
        split_main.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        StartProgess.startProgess.plus();
        split_main_right = new JSplitPane();
        StartProgess.startProgess.plus();
        split_main_right.setOrientation(0);
        StartProgess.startProgess.plus();
        split_main.setRightComponent(split_main_right);
        StartProgess.startProgess.plus();
        statusScroll = new JideScrollPane();
        StartProgess.startProgess.plus();
        statusScroll.setHorizontalScrollBarPolicy(30);
        StartProgess.startProgess.plus();
        statusScroll.setVerticalScrollBarPolicy(22);
        StartProgess.startProgess.plus();
        split_main_right.setRightComponent(statusScroll);
        StartProgess.startProgess.plus();
        mainScollPane = new JideScrollPane();
        StartProgess.startProgess.plus();
        split_main_right.setLeftComponent(mainScollPane);
        StartProgess.startProgess.plus();
        mainScollPane.setViewportView(tabModule);
        StartProgess.startProgess.plus();
        split_main.setLeftComponent(new JideScrollPane(treeModule));
        StartProgess.startProgess.plus();

        statusScroll.setMinimumSize(new Dimension(50, 50));
        mainScollPane.setMinimumSize(new Dimension(100, 400));

        this.getMainPane().add(split_main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //talk();


    }
    @Override
    protected void addEventHandler() {

        this.getMenuItemByName("系统.启动服务").addActionListener(new startHandler());
        this.getMenuItemByName("系统.停止服务").addActionListener(new stopHandler());
        this.getMenuItemByName("系统.退出").addActionListener(new exitHandler());
    }
    @Override
    public void talk() {
        LoudSpeaker loudSpeaker = Module.getLoudSpeaker();
        loudSpeaker.registerActionListener(treeModule, tabModule.getAlforSettingMenuItem());  //tabModule 需要响应treeModule弹出菜单的事件
        loudSpeaker.registerActionListener(treeModule, tabModule.getAlForAddNode());//tabModule响应treeModule的添加XX菜单
       // loudSpeaker.registerCustomEventListener(Server.getInstance(),new messageHandler(),Server.CUSTOM_RECV);
    }

    // -------------------------------------------------------------------
    private class messageHandler implements CustomEventListener
    {

        @Override
        public void todo(CustomEvent event) {
            MainStationFrame.this.tabModule.getActiveTab().add(new JButton("aaa"));
        }
    }
    private class exitHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    private class startHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (server == null) {
               // PLMS.server = Server.getInstance();
                 server =Server.getInstance();
                server.getEventBus().subscribe(MainStationFrame.this);
                server.init();
                Map<Long, IoSession> sessionMap = server.getIoAcceptor().getManagedSessions();
            }
            if (server.getIoAcceptor().isActive() == false) {
                server.start();
                JMenuItem currentMnItem = (JMenuItem) e.getSource();
                currentMnItem.setEnabled(false);
                MainStationFrame.this.getJMenuBar().getMenu(0).getItem(1).setEnabled(true);

            }

        }
    }

    private class stopHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (server.getIoAcceptor().isActive()) {
                server.stop();
                JMenuItem currentMnItem = (JMenuItem) e.getSource();
                currentMnItem.setEnabled(false);
                MainStationFrame.this.getJMenuBar().getMenu(0).getItem(0).setEnabled(true);

            }
        }
    }
    private class terminalSettingHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    // ------------------------------------------------------------------------
    public void showLog() {
        Map<Long, IoSession> sessionMap = server.getIoAcceptor().getManagedSessions();
        for (IoSession s : sessionMap.values()) {
            System.out.println("会话ID:" + s.getId());
            System.out.println("     会话读取消息数：" + s.getReadMessages());
            System.out.println("     会话发送消息数：" + s.getWrittenMessages());
            System.out.println("     客户端地址：" + ((InetSocketAddress) s.getRemoteAddress()).getAddress().toString());

        }
    }

}
