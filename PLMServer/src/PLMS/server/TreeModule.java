package PLMS.server;

import PLMS.AreaJTree.CellRenderer;
import PLMS.AreaJTree.TerminalNodeManager;
import PLMS.AreaJTree.TreeNode.MyTreeNode;
import PLMS.AreaJTree.TreeNode.RootNode;
import PLMS.AreaJTree.TreeNode.TerminalNode;
import PLMS.AreaJTree.UserDataForTreeNode.ForRoot;
import PLMS.AreaJTree.UserDataForTreeNode.ForTerminal;
import PLMS.AreaJTree.UserDataForTreeNode.UserData;
import PLMS.Base.Module;
import PLMS.GUI.DBUtil;
import PLMS.talk.EventEx.ActionEventEx;
import com.jidesoft.swing.CheckBoxTree;
import com.jidesoft.swing.CheckBoxTreeSelectionModel;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 界面左边的“树”模块
 * 公开的事件：树中结点的某些右键菜单
 */
public class TreeModule extends Module {

    private CheckBoxTree tree_termial;

    /**
     *
     */
    public TreeModule() {
        tree_termial = new CheckBoxTree(new RootNode());
        tree_termial.setCellRenderer(new CellRenderer());
        TerminalNodeManager.getManager().tree = tree_termial;
        loadTreeFromDB(tree_termial);
        tree_termial.getModel().addTreeModelListener(new TreeDataListener());
        tree_termial.addMouseListener(new MouseEventListener());
        tree_termial.getCheckBoxTreeSelectionModel().addTreeSelectionListener(new treeSelectListener());
        tree_termial.setDigIn(true);
        tree_termial.setCheckBoxEnabled(true);
        tree_termial.setSelectPartialOnToggling(false);
        tree_termial.setShowsRootHandles(false);
        tree_termial.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                tree_termial.scrollPathToVisible(event.getPath());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        //  tree_termial.setToolTipText("选择");
        //TODO 监听MyTreeNode 的菜单单击事件
        MyTreeNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loudSpeaker.ActionEvent(TreeModule.this, (ActionEventEx) e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        setLayout(new BorderLayout());
        add(tree_termial, BorderLayout.CENTER);


    }


    /**
     * 在某个节点在，添加某区域的终端
     * @param parent
     * @param regid
     */
    public void showTerminals(DefaultMutableTreeNode parent, int regid) {
        TerminalNode temp = null; //当前节点
        String strsql = "select  TerminalID,TerminalName  from TERMINALS  ";
        strsql = strsql + " where    RegionID= " + regid + "  ";
        boolean havechild = false;
        try {
            Statement stat = DBUtil.connecDB().createStatement();
            ResultSet rs = stat.executeQuery(strsql);
            while (rs.next()) {
                havechild = true;
                ///NodeData node = new NodeData(rs.getInt("TerminalID"), rs.getString("TerminalName"));
                //node.setType(NodeType.TERMINAL);
                ForTerminal ud = new ForTerminal();
                ud.setName(rs.getString("TerminalName"));
                StartProgess.startProgess.setText(ud.getName());
                ud.setOnline(false);


                temp = new TerminalNode();
                temp.setUserObject(ud);
                TerminalNodeManager.getManager().AddTerminalNode(ud);
                parent.add(temp);

            }
        } catch (Exception e) {
            System.out.println("获取节点树错误");
        }
    }

    /**
     * 从数据库中载入区域树
     */
    public void loadTreeFromDB(CheckBoxTree tree) {
        try {
            // tree.setRootVisible(false);
            // 获取当前tree的根节点

            MyTreeNode root = (MyTreeNode) tree.getModel().getRoot();
            UserData ud = new ForRoot();
            ud.setName("电力管理");
            ud.setNode(root);
            root.setUserObject(ud);
            root.removeAllChildren();  //删除所有节点
            createNodes(root, 1, 0);
            tree.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
    }

    /**
     * 递归创建 Tree
     * @param layer    节点所在的层数
     * @param parentid 父节点id号
     */
    public void createNodes(MyTreeNode parent, int layer, int parentid) {
        MyTreeNode temp = null; //当前节点
        String strsql = "select   RegID,RegName ,ParentRegID ,RegType,NodeType  from  Regions1 ";
        strsql = strsql + " where    ParentRegID= " + parentid + "   order   by   layerorder ";
        boolean havechild = false;
        try {
            Statement stat = DBUtil.connecDB().createStatement();
            StartProgess.startProgess.setText("Reading data...");
            ResultSet rs = stat.executeQuery(strsql);

            while (rs.next()) {
                havechild = true;    //有子节点
                // NodeData node = new NodeData(rs.getInt("RegID"), rs.getString("RegName"));

                temp = MyTreeNode.getMyTreeNode(rs.getString("NodeType")); //创建这个子节点 ，也是当前节点
                UserData node = UserData.getUserData(rs.getString("RegType"));
                node.setName(rs.getString("RegName"));
                StartProgess.startProgess.setText(node.getName());
                temp.setUserObject(node);
                parent.add(temp);  //作为父节点的儿子
                createNodes(temp, layer + 1, rs.getInt("RegID"));   //取当前节点子节点
                showTerminals(temp, rs.getInt("RegID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class TreeDataListener implements TreeModelListener {

        @Override
        public void treeNodesChanged(TreeModelEvent e) {
            System.out.print("失修");
        }

        @Override
        public void treeNodesInserted(TreeModelEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void treeNodesRemoved(TreeModelEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void treeStructureChanged(TreeModelEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    /**
     * 树的选择事件
     */
    class treeSelectListener implements TreeSelectionListener {
        private ArrayList<DefaultMutableTreeNode> arr;

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            arr = new ArrayList<DefaultMutableTreeNode>();// 选取所有的终端节点
            // CheckBoxTree tree = (CheckBoxTree) e.getSource();
            CheckBoxTreeSelectionModel model = (CheckBoxTreeSelectionModel) e.getSource();
            TreePath[] treePaths = model.getSelectionPaths();
            // System.out.println(model.getSelectionCount());
            java.util.ArrayList<Object> leafs = new ArrayList<Object>();
            if (treePaths != null) {
                // 有选中的路径
                for (int i = 0; i < treePaths.length; i++) {

                    // 将路径转化为节点数组
                    Object[] nodes = treePaths[i].getPath();
                    // 得到当前路径的最后一个节点,即选择的节点

                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[nodes.length - 1];
                    if (node.isLeaf()) {
                        leafs.add(node);
                    }
                    getAllCheckedLeaf(node, leafs);

                    /* if(!node.isLeaf()){

                        arr.add(node.getFirstLeaf());
                       for(int k=1;k<node.getLeafCount();k++)
                       {
                           arr.add(node.);
                       }
                    }else{
                        arr.add(node);
                    }*/
                    /*  for (int j = 0; j < nodes.length; j++) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[j];
                      //  node.
                        System.out.println("当前节点的叶子：" + node.getLeafCount());
                        System.out.print("-"  );
                        System.out.print(node.toString());
                    }*/

                    // 输出节点名
                    System.out.println("");
                }
            }

            for (Object node : leafs) {
                System.out.println(node.toString());
            }
        }

        public void getAllCheckedLeaf(DefaultMutableTreeNode node, ArrayList<Object> leafs) {
            //java.util.ArrayList<Object> leafs=new ArrayList<Object>();
            DefaultMutableTreeNode current_node = node;
            for (Enumeration e = node.children(); e.hasMoreElements(); ) {
                DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
                getAllCheckedLeaf(n, leafs);
                if (n.isLeaf()) leafs.add(n);
            }
        }

        /**
         * 获取所有TreePath
         * @param parentPath
         * @return
         */
        private List<TreePath> getChildrenElement(CheckBoxTree _tree, TreePath parentPath) {
            //CheckBoxTree _tree=null;
            List<TreePath> childList = new ArrayList<TreePath>();
            Object parentNode = parentPath.getLastPathComponent();
            int childCount = _tree.getModel().getChildCount(parentNode);
            for (int i = 0; i < childCount; i++) {
                Object child = _tree.getModel().getChild(parentNode, i);
                final TreePath childPath = parentPath.pathByAddingChild(child);
                childList.add(childPath);
                childList.addAll(getChildrenElement(_tree, childPath)); //递归
            }
            return childList;
        }


    }

    /**
     * 树的鼠标事件处理
     */
    class TreeMouseListener extends MouseAdapter {
        private JTree tree;
        private JPopupMenu popMenu;

        public TreeMouseListener(JTree tree, JPopupMenu popMenu) {
            this.tree = tree;
            this.popMenu = popMenu;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            // 根据鼠标位置获取TreePath
            TreePath path = tree.getPathForLocation(e.getX(), e.getY());
            if (path == null) {
                //JTree上没有任何项被选中，直接返回
                return;
            }
            tree.setSelectionPath(path);
            if (e.getButton() == 3) {
                //鼠标右键
                popMenu.show(tree, e.getX(), e.getY());
            }


        }


    }

    class MouseEventListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                try {
                    CheckBoxTree tree = (CheckBoxTree) e.getSource();
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    MyTreeNode node = (MyTreeNode) path.getLastPathComponent();
                    node.showMenu(tree, e.getX(), e.getY());
                } catch (Exception ex) {
                }
            }
        }
    }
}
