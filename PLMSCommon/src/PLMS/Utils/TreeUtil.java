package PLMS.Utils;

import com.jidesoft.swing.CheckBoxTree;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 */
public class TreeUtil {


    /**
     * 根据XML文件产生一个节点树
     * @param xmlfile
     * @return
     */
    private DefaultMutableTreeNode loadNodeFromXML(String xmlfile) {

        DefaultMutableTreeNode node_afn=null;
        DefaultMutableTreeNode  node_group, node_fn;
        try {
            XMLConfiguration conf = new XMLConfiguration(xmlfile);
            // ---------------tree显示特性--------------------------

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

            }

        } catch (ConfigurationException e) {
            System.out.println("读取配置文件(func.xml)失败！");
        }
        return node_afn;
    }
}
