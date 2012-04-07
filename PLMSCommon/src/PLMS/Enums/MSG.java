package PLMS.Enums;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

/**
 *  消息的结构相关的常量
 */
public class MSG {
    // ----------------------  ------------------------------------
    /**
     * 起始字符（START_CHAR）（第1字节）
     */
    public static final int OFFSET_OF_START_CHAR = 0;
    public static final int LEN_OF_START_CHAR = 1;
    public static  final byte DEFAULT_START_CHAR=0x68;
    /**
     * 用户数据长度（L1，L2）字段
     */
    public static final int OFFSET_OF_L1 = 2;
    public static final int OFFSET_OF_L2 = 4;
    public static final int LEN_OF_MESSAGE_LENGTH = 2;
    /**
     *  控制域(CONTROL_DOMAIN)（第7字节）
     */
    public static  final short  OFFSET_OF_control_domain=7;
    public static final int LEN_OF_CONTROL_DOMAIN=1;
    /**
     * 地址域(ADDRESS_DOMAIN)（第8字节开始，共5字节）
     * 行政区域（2字节）
     * 终端地址（2字节）
     * 主站地址（1字节）
     */
    public  static final short  OFFSET_OF_address_domain=8;
    public  static final short  LEN_OF_address_domain=5;
    public static final int LEN_OF_DISTRICT=2;
    public static final int LEN_OF_TREMINAL_ADDR=2;
    public static final int LEN_OF_PRIMARY_STATION_ADDR=1;

    public static final int OFFSET_OF_USER_DATA=12;
    /**
     * 验证和
     */
    public static final int LEN_OF_CHECK_SUM = 1;
    /**
     * 结束字符
     */
    public static final int LEN_OF_END_CHAR = 1;
    public static final byte DEFAULT_END_CHAR = 0x16;
    public static final  int SPECIFICATION_MARK =0x2;
    /* ---------------------------------------------------------------------------- */
    /**
     * 默认的超时事件（单位：秒）
     */
    public static final int  TIME_OUT=60;
    /**
     * 重发次数
     */
    public static final int TRY_TIMES=3;    
    public static final int  UPSTREAM=1;
    public static final int DOWNSTREAM=0;
    public static final  int  MULTICAST_YES=1;
    public static final  int  MULTICAST_NO=0;
    
    // -----------------------------------------------------------------------------------
    final static int BIT1 = 1;      // 第1位
    final static int BIT2 = 2;       //第2位
    final static int BIT3 = 4;
    final static int BIT4 = 8;
    final static int BIT5 = 16;
    final static int BIT6 = 32;
    final static int BIT7 = 64;
    final static int BIT8 = 128;

}
