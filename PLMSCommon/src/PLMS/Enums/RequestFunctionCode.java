package PLMS.Enums;

/**
 * PRM=1时， 功能码定义
 */
public enum RequestFunctionCode {
    RESET_COMMAND(1),     // 用于复位命令     （发送/应答方式）
    USER_DATA(4),         // 用于发送用户数据   （发送/无应答）
    LINK_TEST(9),          // 用于链路测试        （请求/相应）
    REQUEST_CLASS1(10),     // 用于请求1级别数据        （请求/相应）
    REQUEST_CLASS2(11);       // 用于请求2级别数据        （请求/相应）
    // --------------------------------------
    private int value;
    private RequestFunctionCode(int v) {
        value=v;
    }
    public int valueOf()
    {
        return value;
    }
    // 根据值，找对应的枚举值
    public static RequestFunctionCode get(int code) {
        for(RequestFunctionCode s : values()) {
            if(s.value == code) return s;
        }
        return null;
    }
}
