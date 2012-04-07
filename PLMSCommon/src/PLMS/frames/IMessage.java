package PLMS.frames;

import PLMS.Enums.EnumPRM;
import PLMS.Enums.MSG_DIRECTION;

import java.io.Serializable;

/**
 * 消息接口
 */
public interface IMessage extends Serializable {
    public static final int HEAD_LENGTH=6;   // 消息头长度
    public static final int FOOT_LENGTH=2;   // 消息尾长度
    public static final int MINI_LENGTH=20; // 消息最小长度
    // ------------------------------------------------------------------
    public static final byte DEFAULT_START_CHAR=0x68;   // 默认起始字符
    public static final byte DEFAULT_END_CHAR = 0x16;    //默认结束字符
    public static final  int SPECIFICATION_MARK =0x2;     // 规约标识

    /**
     * 默认的超时事件（单位：秒）
     */
    public static final int  TIME_OUT=60;
    /**
     * 默认重发次数
     */
    public static final int TRY_TIMES=3;

    // ------------------------------------------------------------
    public int length();                        // 获取消息的长度
    public byte[] valueOf();                    // 获取消息的字节数组
    public boolean isValid();                          // 消息是否有效
    // --------------------下面是所有消息需要实现的方法---------------------------
    public void setRegionAddress(int v);        //设置行政区域
    public void setTermialAddress(int v);       // 设置终端地址
    public void setMainStationAddress(int v);    //设置主站地址
    //---------
    public void setPrimary(EnumPRM prm);                   //设置是否为启动站
    public void setDirection(MSG_DIRECTION dir);    //设置传输方向
    //public void  setFunctionCode()

}
