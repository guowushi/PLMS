package PLMS.Enums;

/**
 * 请求访问位（上行响应消息）
 */
public enum ACD {
       HAS_IMPORTANT_INFO(1),
       NO_IMPORTANT_INFO(0);

    private  int value;
    private ACD(int v) {
        this.value=v;
    }
    public int valueOf()
    {
        return  value;
    }
}
