package PLMS.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.*;
import java.util.List;

/**
 * 自定义JFrame
 */
public abstract class MyJFrame extends JFrame{
    protected boolean dataModified;         // 数据是否被修改过
    protected JMenuBar menuBar=new JMenuBar();   // 菜单
    protected int roleID;                    // 当前角色
    protected int moduleID;                  // 当前模块
    protected Connection conn;                  // 当前数据库连接
    protected StatusBar statusBar=new StatusBar();
    protected JPanel rootPane=new JPanel();
    /**
     * 菜单项，哈希图
     * */
    protected Map<String,JMenuItem> menuMap=new LinkedHashMap<String, JMenuItem>();
    // ----------------------------------------------------------
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    public int getModuleID() {
        return moduleID;
    }
    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }
    public  Connection getConn()
    {
        return this.conn;
    }
    public JMenuItem getMenuItemByName(String menuname)
    {
       return menuMap.get(menuname);
    }
    // --------------------构造函数-------------------------
    public MyJFrame(String title,int role,int moduleID){
        super(title);
        this.roleID=role;
        this.moduleID=moduleID;
       // init();    //子类需要调用
     }

    /**
     * 返回窗体的主容器，其他的组件都应该添加在该容器中
     * @return
     */
    public Container getMainPane(){
        return rootPane;
    }

    /**
     *  基本的UI设计
     *  创建状态栏
     */
    private void baseUI(){
        setLayout(new BorderLayout());
        super.add(statusBar, BorderLayout.SOUTH);
        super.add(rootPane,BorderLayout.CENTER);
        rootPane.setLayout(new GridLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
/*    public MyJFrame(String title){
        super(title);
    }
    public MyJFrame(int role){
        super();
        this.roleID=role;

    }*/
     // ----------------------所有窗体共有-------------------------------------
    /**
     * 主入口，所有子类都应该根据自己的界面设计重写该方法
     */
    public void init()
    {
        conn=DBUtil.connecDB();    //(1)连接数据库
        initUI();                  // (2)初始化界面
        defaultSetting();          // (3)初始化基本设置
        initMenu(this.roleID);    // (4)初始化菜单
        addEventHandler();          // （5）设置事件处理
        talk();                     //  (6) 设置事件交互
        baseUI();                   // (7) 状态栏
    }

    /**
     *  实现菜单载入
     */
    protected  void initMenu(int  roleID){
        this.setJMenuBar(menuBar);
        ArrayList<MenuObj> lists = DBUtil.getMenuList(roleID,moduleID);
        buildMenu(lists);

    }
    /**
     *  JFrame的基础设置（子类建议重写）
     */
    protected  void defaultSetting()
    {
        //-------设置默认的字体大小---------------
        try{
        Font   font   =   new   Font("宋体",Font.PLAIN,12);
        UIManager.put( "Button.font ",font);
        UIManager.put( "ToggleButton.font ",font);
        UIManager.put( "RadioButton.font ",font);
        UIManager.put( "CheckBox.font ",font);
        UIManager.put( "ColorChooser.font ",font);
        UIManager.put( "ToggleButton.font ",font);
        UIManager.put( "ComboBox.font ",font);
        UIManager.put( "ComboBoxItem.font ",font);
        UIManager.put( "InternalFrame.titleFont ",font);
        UIManager.put( "Label.font ",font);
        UIManager.put( "List.font ",font);
        UIManager.put( "MenuBar.font ",font);
        UIManager.put( "Menu.font ",font);
        UIManager.put( "MenuItem.font ",font);
        UIManager.put( "RadioButtonMenuItem.font ",font);
        UIManager.put( "CheckBoxMenuItem.font ",font);
        UIManager.put( "PopupMenu.font ",font);
        UIManager.put( "OptionPane.font ",font);
        UIManager.put( "OptionPane.messageFont ",font);
        UIManager.put( "OptionPane.buttonFont ",font);
        UIManager.put( "Panel.font ",font);
        UIManager.put( "ProgressBar.font ",font);
        UIManager.put( "ScrollPane.font ",font);
        UIManager.put( "Viewport ",font);
        UIManager.put( "TabbedPane.font ",font);
        UIManager.put( "TableHeader.font ",font);
        UIManager.put( "TextField.font ",font);
        UIManager.put( "PasswordFiled.font ",font);
        UIManager.put( "TextArea.font ",font);
        UIManager.put( "TextPane.font ",font);
        UIManager.put( "EditorPane.font ",font);
        UIManager.put( "TitledBorder.font ",font);
        UIManager.put( "ToolBar.font ",font);
        UIManager.put( "ToolTip.font ",font);
        UIManager.put( "Tree.font ",font);
        UIManager.put( "Table.font ",font);
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
        // -----------------------------------------
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //--------------事件处理--------------------
        this.addWindowListener(new winHandler());
    }

     // --------------------------------------------------------------
    /**
     * 产生MenuBar上的菜单
     * @param menuObjs
     */
    protected void buildMenu(ArrayList<MenuObj> menuObjs) {
        MenuObj current = null;
        for (MenuObj mi : menuObjs) {
            if (mi.parentID == 0) {
                JMenu menu = new JMenu(mi.menuName);
                this.getJMenuBar().add(menu);

                menuName=mi.menuName;
                buildChildMenu(menu, menuObjs, mi);
            }
        }
    }

    /**
     * 根据菜单产生菜单项
     * @param current
     * @param mns
     * @param obj
     * @return
     */
    private String menuName="";
    protected boolean buildChildMenu(JMenu current, ArrayList<MenuObj> mns, MenuObj obj) {
        ArrayList<MenuObj> childs = new ArrayList<MenuObj>();     //保存子菜单
        sortMenuObject(childs);
        for (MenuObj mi : mns) {
            if (mi.parentID == obj.mnid) {
                childs.add(mi);       // 获取obj菜单的子菜单
            }
        }

        for (int i = 0; i < childs.size(); i++) {
            //obj 的确有子菜单
            MenuObj mn = childs.get(i);
            JMenu menu = new JMenu(mn.menuName);   //obj的第一个儿子菜单
            menuName+="."+ mn.menuName;
                        // 子菜单添加到当前obj父菜单下
            if (buildChildMenu(menu, mns, mn)){            //递归儿子的儿子菜单,返回值表示儿子菜单是否有子菜单
                current.add(menu);
                menuMap.put(menuName,menu);
                //JOptionPane.showMessageDialog(null,menuName);
                int index=menuName.lastIndexOf('.');
                if(index!=-1)
                {
                    menuName=menuName.substring(0,index);
                }
            }else {
                JMenuItem menuItem = new JMenuItem(mn.menuName);
               // menuItem.setFont(new Font("宋体",Font.PLAIN,14));
                current.add(menuItem);
                menuMap.put(menuName,menuItem);
                //JOptionPane.showMessageDialog(null, menuName);
                int index=menuName.lastIndexOf('.');
                if(index!=-1)
                {
                    menuName=menuName.substring(0,index);
                }
            }
        }
        return childs.size() != 0;

    }



    protected void sortMenuObject(List<MenuObj> list){
        Collections.sort(list,new MenuOrderComparator());
    }
    // -------------------子类必须自己实现----------------------------------------
    protected abstract  void initUI();              //界面初始化
    protected abstract void addEventHandler();         // 实现菜单事件
    protected abstract void   talk();                   // 事件绑定
    // -------------------窗体关闭监听器------------------------------------------
    class winHandler extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            // super.windowClosing(e);
            int option = JOptionPane.showConfirmDialog(e.getWindow(), "确定退出系统? ", "提示 ", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                e.getWindow().dispose();
                System.exit(0);
            } else {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 这个是关键
                return;
            }
        }
    }
}
