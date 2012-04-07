package PLMS.DataUnits.AFN04;
import PLMS.DataUnits.AbstractFn;
/**
 * 设置参数（AFN04）-终端通信参数设置（F1_UP)
 */
public class F1 extends AbstractFn {
    /**
     *  Fn的长度，有3种情况：
     *  （1）没有数据区域，则定义一个公共静态变量LENGTH=0;
     *  （2）为固定长度,则定义一个公共静态变量LENGTH=xx;
     *  （3）可变长度，则定义一个私有变量length,但定义一个公有方法length();
     */
    public static  final  int LENGTH=6;
    //  --------------------   成员变量  --------------------------------------------
    /**
     * 心跳周期
     */
    private int heartBeatCycle;
    private int need_Master_confirm_level;   //需要主站确认的通信服务（con=1)的标志1-需要确认0-无需确认
    private int RTS;                             // 终端数传机延时时间
    private int delayTime;                      //  终端作为启动站允许发送传输延时时间
    private int timeOut;                        // 确认超时时间
    private int retryTimes;                     //重发次数 (单位，次）
    //-----------------------------------------------------------------------------
    public int getHeartBeatCycle() {
        return heartBeatCycle;
    }

    public void setHeartBeatCycle(int heartBeatCycle) {
        this.heartBeatCycle = heartBeatCycle;
    }

    public int getNeed_Master_confirm_level() {
        return need_Master_confirm_level;
    }

    public void setNeed_Master_confirm_level(int need_Master_confirm_level) {
        this.need_Master_confirm_level = need_Master_confirm_level;
    }

    public int getRTS() {
        return RTS;
    }

    public void setRTS(int RTS) {
        this.RTS = RTS;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }
    // -------------------构造函数-----------------------------------------------
    /**
     * 在默认的构造函数中初始化一些默认值
     */
    public F1() {
    }
    /**
     * 所有Fn都需要一个构造函数，将in字节数组解析成具体的成员变量值
     * @param in
     */
    public F1(byte[] in) {
        init( in);
    }
    //--------------------------------------------------------------------

    // ------------------成员方法-----------------------------------------
    /**
     * 所有Fn都需要重写length()，返回该Fn的长度
     * @return
     */
    @Override
    public int length() {
        return LENGTH;
    }

    /**
     * 所有Fn都需要重写length()，返回该Fn的字节数组
     * @return
     */
    @Override
    public byte[] valueOf() {
        byte[] bs=new byte[this.length()];
            
        return bs;   
    }
    @Override
    public void init(byte[] in) {
        this.RTS=in[0];
        this.delayTime=in[1];
        this.timeOut=(in[2]<<4)+ in[3] & 0x0F;
        this.retryTimes=in[3] &0x30;
        this.need_Master_confirm_level=in[4];    // 转BCD
        this.heartBeatCycle=in[5];
        
    }
}
