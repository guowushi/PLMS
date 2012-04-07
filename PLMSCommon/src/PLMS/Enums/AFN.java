package PLMS.Enums;

/**
 * Applicatipn Function Code
 */
public enum AFN {

    /**
     * 确认否认
     */
    CONFIRM_DENY(0x00),
    /**
     * 复位命令
     */
    RESET(0x01),
    /**
     * 链路接口检查
     */
    LINK_DETECT(0x02),
    /**
     * 中继站命令
     */
    Repeater_Station_Commad(0x03),
    /**
     * 设置参数
     */
    PARAMETERS_SETTING(0x04),
    /**
     * 控制命令
     */
    CONTROL_CMD(0x05),
    /**
     * 身份识别
     */
    AUTHENTICATION(0x06),
    /**
     * 查询参数
     */
    QUERY_PARAMETERS(0x0A),
    /**
     * 获取1类数据
     */
    REQUEST_CLASS_1(0x0C),
    /**
     * 获取2类数据
     */
    REQUEST_CLASS_2(0x0D),
    /**
     * 获取3类数据
     */
    REQUEST_CLASS_3(0x0E),
    /**
     * 文件传输
     */
    FILE_TRANSFER(0x0F),
    /**
     * 数据转发
     */
    DATA_FORWARD(0x10);
    //--------------------------------------
    private byte code;
    //---------------------------------------
    private AFN(int c) {
        this.code =(byte) c;
    }
    public byte valueOf() {
        return (byte)code;
    }
    // 根据值，找对应的枚举值
    public static AFN get(int code) {
        for(AFN s : values()) {
            if(s.code == code) return s;
        }
        return null;
    }
}


