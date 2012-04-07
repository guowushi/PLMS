package PLMS.Enums;

/**
 * PRM=0时的 功能码定义
 */
public enum ResponseFunctionCode {
    CONFIRM(0),         // 认可（确认）
    USER_DATA(8),       // 用户数据响应
    DENY(9),            // 否认
    LINK_STATUS(11);  // 链路状态
    //------------------------------------------------
    private ResponseFunctionCode(int value) {
        this.value = value;
    }
    public  int  valueOf()
    {
       return value;
    }
    private int value;
    // 根据值，找对应的枚举值
    public static ResponseFunctionCode get(int code) {
        for(ResponseFunctionCode s : values()) {
            if(s.value == code) return s;
        }
        return null;
    }
}
