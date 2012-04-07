package PLMS.GUI;

import com.jidesoft.swing.CheckBoxTree;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 */
public class DBUtil {

    /**
     * 连接一个SQLite数据库
     * @param
     */
    public static Connection connecDB() {
         Connection conn=null;
        try {
            //Class.forName("org.sqlite.JDBC");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print("没有驱动！");
        }
        try {
            //
            //conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plms?useUnicode=true&characterEncoding=UTF-8", "root", "123");
        } catch (SQLException e) {
            System.out.print("连接失败！");
        }
        return conn;
    }
    /**
     * 获取所有菜单项目
     * @param roleID
     * @return
     */
    public static ArrayList<MenuObj> getMenuList(int roleID,int moduleID) {
        ArrayList<MenuObj> lists = new ArrayList<MenuObj>();
        String strsql = "SELECT   MenuID,MenuName,MenuType,ParentMenuID,MenuOrder  FROM  ViewRoleMenu ";
        strsql = strsql + " where    RoleID= " + roleID;
        strsql = strsql + "  And  ModuleID= " + moduleID;
        strsql = strsql + "  Order   by  ParentMenuID , MenuOrder ASC ";
        try {
            Statement stat = DBUtil.connecDB().createStatement();
            ResultSet rs = stat.executeQuery(strsql);
            while (rs.next()) {
                int mnid = rs.getInt("MenuID");  //取当前数据
                String nn = rs.getString("MenuName");
                int parentID = rs.getInt("ParentMenuID");
                int order = rs.getInt("MenuOrder");
                lists.add(new MenuObj(mnid, nn, parentID, order));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        MenuOrderComparator ct=new MenuOrderComparator();
        Collections.sort(lists,ct);
        return lists;
    }

    /**
     * 从XML载入数据
     *
     * @param tree
     * @param xmlfile
     */
    private void loadTreeFromXML(CheckBoxTree tree, String xmlfile) {
        try {
            XMLConfiguration conf = new XMLConfiguration(xmlfile);
            // ---------------tree显示特性--------------------------
            tree.setRootVisible(false);
            // tree.setToolTipText("选择功能");
            // 获取当前tree的根节点
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
            root.removeAllChildren();  //删除所有节点

            DefaultMutableTreeNode node_afn, node_group, node_fn;
            List<HierarchicalConfiguration> sub_afn_list = conf.configurationsAt("afn");
            for (int m = 0; m < sub_afn_list.size(); m++) {
                // 获取AFN节点信息
                HierarchicalConfiguration sub_afn_conf = sub_afn_list.get(m);
                String fieldName = sub_afn_conf.getString("name");
                String fieldValue = sub_afn_conf.getString("value");
                node_afn = new DefaultMutableTreeNode(fieldName);
                List<HierarchicalConfiguration> sub_group_conf_list = sub_afn_conf.configurationsAt("group");
                // -----------------------------
                for (int n = 0; n < sub_group_conf_list.size(); n++) {
                    HierarchicalConfiguration sub_group_conf = sub_group_conf_list.get(n);
                    String groupName = sub_group_conf.getString("name");
                    String groupValue = sub_group_conf.getString("value");
                    node_group = new DefaultMutableTreeNode(groupName);
                    //---------------------------
                    List<HierarchicalConfiguration> sub_fn_conf_list = sub_group_conf.configurationsAt("fn");
                    for (int l = 0; l < sub_fn_conf_list.size(); l++) {
                        HierarchicalConfiguration sub_fn_conf = sub_fn_conf_list.get(l);
                        String fnName = sub_fn_conf.getString("[@name]");
                        String fnValue = sub_fn_conf.getString("value");
                        node_fn = new DefaultMutableTreeNode(fnName);

                        node_group.add(node_fn);

                    }
                    node_afn.add(node_group);
                }
                root.add(node_afn);
            }
            System.out.print(root.getChildCount());
            //tree_termial.
            tree.updateUI();  //更新界面
        } catch (ConfigurationException e) {
            System.out.println("读取配置文件(func.xml)失败！");
        }
    }

    /**
     * 判断是否登录成功
      * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public static boolean checkLogin (String username,String password)throws Exception
    {
        boolean ret=false;
        String strsql = "SELECT   *  FROM  View_user ";
        strsql = strsql + " where    UserName= '" + username+"'";
        strsql = strsql + "  And  Password= '" + password+"'";
            Statement stat = DBUtil.connecDB().createStatement();
            ResultSet rs = stat.executeQuery(strsql);
           if(rs.next()) {
                ret=true;
            }else{
               throw new Exception("用户和密码错误！");
           }
       return ret;
    }
}
