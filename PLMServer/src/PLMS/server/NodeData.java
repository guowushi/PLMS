package PLMS.server;

/**
 * 节点数据对象
 */
public class NodeData {
    private int value;
    private String name;
    private String tip;
    private NodeType type;

    public NodeData(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    //必须实现该方法，就是toString的返回值作为节点显示文字
    public String toString() {
        return name;
    }
}
