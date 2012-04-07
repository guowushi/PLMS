package plms;

import PLMS.GUI.*;
import net.infonode.docking.DockingWindow;
import net.infonode.docking.RootWindow;
import net.infonode.docking.SplitWindow;
import net.infonode.docking.View;
import net.infonode.docking.util.DockingUtil;
import net.infonode.docking.util.ViewMap;
import net.infonode.util.Direction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户端程序主窗体
 */
public class dockFrame extends MyJFrame {
    
    protected ViewMap  viewMap=new ViewMap();
    private Map<String,View>  vMap=new HashMap<String, View>();
    private int viewCount=0;
    //-------------------------------------------------------------
    public dockFrame(String title, int role,int moduleId) {
        super(title, role,moduleId);
        init();
    }
    public View getView(String key){
       return  vMap.get(key);
    }
    // ------------------------------------------------------------
    @Override
    public void init() {
        super.init();
        initView();
    }

    @Override
    protected void defaultSetting() {
        super.defaultSetting();

    }

    @Override
    protected void initUI() {

        // ---------------创建View --------------
        createView("导航树", true, true, false);
        createView("主区域", true, true, false);
        createView("日志", true, true, true);

        // ----------------创建Dock的Root-------------------
        RootWindow root = DockingUtil.createRootWindow(viewMap, true);
        root.getWindowBar(Direction.LEFT).setEnabled(true);  //允许使用左边的最小化区域
        root.getWindowBar(Direction.DOWN).setEnabled(true);  //允许使用左边的最小化区域
        root.getWindowBar(Direction.RIGHT).setEnabled(true);  //允许使用左边的最小化区域
        root.setPreferredSize(new Dimension(800, 600));
        // root.getWindowProperties().setMinimizeEnabled(true);
        // ---------------设计Dock结构----------------------
        try {
           // DockingWindow left = viewMap.getView(0);
            DockingWindow left =vMap.get("导航树");
            SplitWindow right = new SplitWindow(false, 0.8f, viewMap.getView(1), viewMap.getView(2));
            DockingWindow myLayout = new SplitWindow(true, 0.3f, viewMap.getView(0), right);
            root.setWindow(myLayout);
            
            this.getMainPane().add(root);
        } catch (Exception e) {

        }

    }

    @Override
    protected void talk() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void addEventHandler() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    // ---------------------------------------------------------------------
    public View addView(String key){
        View view = new View("",null,null);
        vMap.put(key,view);
        int i = viewMap.getViewCount();
        viewMap.addView(i, view);        //添加到管理器中
        return view;
    }
    public View createView(String title) {
        View view = new View(title, null, null);

        int i = viewMap.getViewCount();
        viewMap.addView(i, view);
        vMap.put(title,view);
        return view;
    }
    public View createView(String title, boolean min, boolean max, boolean close) {
        View view = createView(title);
        view.getWindowProperties().setMinimizeEnabled(min);
        view.getWindowProperties().setMaximizeEnabled(max);
        view.getWindowProperties().setCloseEnabled(close);
         
        // view.getWindowProperties().setUndockEnabled(false);
        return view;
    }
    public void initView() {
      /*  quick.dbtable.DBTable dBTable1 = new quick.dbtable.DBTable();
        dBTable1.setConnection(this.getConn());
        dBTable1.setSelectSql("select * from view_user");
        dBTable1.createControlPanel();
        try {
            //fetch the data from database to fill the table
            dBTable1.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        MyTable<aa> dBTable1=new MyTable<aa>(aa.class);
        String[] titles={"a","B","c"};
        
       MyTableModel<aa> model=new MyTableModel<aa>();
       // dBTable1.addRow(aa.class);

        model.addRow(new aa("aaaa","aaaa","aaaa"));
        model.addRow(new aa("vvv","vaa","aaaa"));
        model.addRow(new aa("vvdsdv","vaa","aaaa"));
        model.addRow(new aa("assas","vaa","aaaa"));
        dBTable1.setTableModel(model);
        //dBTable1.getTable().setModel(model);
       // model.setTitleNames(titles);
       /* Object[][] tableData =
                {
                        new Object[]{"李清照" , 29 , "女"},
                        new Object[]{"苏格拉底", 56 , "男"},
                        new Object[]{"李白", 35 , "男"},
                        new Object[]{"弄玉", 18 , "女"},
                        new Object[]{"虎头" , 2 , "男"}
                };

        DefaultTableModel model = new DefaultTableModel(tableData, titles) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dBTable1.getTable().setModel(model);
        dBTable1.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dBTable1.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2)
                {
                    EditDialog dialog = new  EditDialog("aaa");
                    dialog.setTitle("测试");
                    Object o=dialog.showDialog();
                }
            }
        });
        // --------------获取第3列------------------------------
        TableColumn sportColumn = dBTable1.getTable().getColumnModel().getColumn(2);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("男");
        comboBox.addItem("女");

        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
*/
        //dBTable1.getTableModel().addRow(new Object[]{"sitinspring", "35", "Boss"});
      //  model.addRow(new Object[]{"sitinspring", "35", "Boss"});
        dBTable1.updateUI();
        this.getView("主区域").add(dBTable1);
        //viewMap.getView(1).add(dBTable1);
        //vMap.get("主区域").add(dBTable1);
    }
}
