package PLMS.AreaJTree;

import PLMS.AreaJTree.TreeNode.MyTreeNode;
import PLMS.AreaJTree.TreeNode.RootNode;
import PLMS.AreaJTree.UserDataForTreeNode.UserData;
import com.jidesoft.swing.CheckBoxTree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-3
 * Time: 下午8:23
 * To change this template use File | Settings | File Templates.
 */

/**
 * 使用getAreTree获取一个AreaTree的实例
 * */
public class AreaTree extends CheckBoxTree {

    protected TerminalNodeManager manager=TerminalNodeManager.getManager();
    protected AreaTree(DefaultMutableTreeNode root){
          super(root);
          setCellRenderer(new CellRenderer());
    }
    public static AreaTree getAreaTree(){
        RootNode root=new RootNode();
        final AreaTree tree=new AreaTree(root);
        TerminalNodeManager.getManager().tree=tree;
        new Thread(TerminalNodeManager.getManager()).start();//启动管理器
        tree.addMouseListener(new MouseEventListener());
        MyTreeNode.addAddNodeEventListener(new AddNodeEventListener() {
            @Override
            public void added(AddNodeEvent e) {
                tree.updateUI();
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        return tree;
    }


  
}

class MouseEventListener extends MouseAdapter{
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.isPopupTrigger()){
            try{
            AreaTree tree=(AreaTree) e.getSource();
            TreePath path= tree.getPathForLocation(e.getX(), e.getY());
            MyTreeNode node= (MyTreeNode)path.getLastPathComponent();
            node.showMenu(tree,e.getX(),e.getY());
            }
            catch (Exception ex){
                ex.printStackTrace();

            }
        }
    }
}


//树创建器
class Creator{
    static int id=0;
    static TerminalNodeManager manager=TerminalNodeManager.getManager();
    static int address=0;
    static void createTree(DefaultMutableTreeNode root){
        System.out.print("t");
     //   createSubTree(root,getNodeList());
    }
    
    static void createSubTree(DefaultMutableTreeNode parent,ArrayList<DefaultMutableTreeNode> list){
        DefaultMutableTreeNode myNode;
        UserData pUd=(UserData)parent.getUserObject();
        for(int i=0;i<list.size();i++)
        {
            myNode=list.get(i);
            UserData ud=(UserData)myNode.getUserObject();
            
            if(ud.getPid()==pUd.getId()){
                parent.add(myNode);
                createSubTree(myNode,list);
            }
        }
    }
    
    
    static ArrayList<DefaultMutableTreeNode> getNodeList()  {
        ArrayList<DefaultMutableTreeNode> ns=new ArrayList<DefaultMutableTreeNode>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");
        Statement s= con.createStatement();
        ResultSet rt= s.executeQuery("SELECT * FROM tb1");
        while (rt.next()){

        }
        }catch (Exception e){e.printStackTrace();}
        return ns;
    }
}




