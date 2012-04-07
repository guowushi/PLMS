package PLMS.DataUnits.AFN0C;

import PLMS.DataForm.*;
import PLMS.DataUnits.*;

import java.nio.ByteBuffer;

/**
 * 请求1类数据--电能表日历时钟及电能表状态信息
 */
public class F27 extends AbstractFn {
    public final int LENGTH = 28;
    public A15 readingTime;                 //终端抄表时间  数据格式 15  分时日月年  5
    public A01 METER_CLOCK;                 //电能表日历时钟  数据格式 01  秒分时日月年  6
    public byte Meter_BYTE_CHANGE;         //电表运行状态字  BS8    1
    public byte METER_BYTE;                 //电网状态字  BS8    1
    public A17 Last_Programming_TIME;             //最近一次编程时间  数据格式 17  分时日月  4
    public A17 Last_Reset_TIME;                   //最近一次最大需量清零时间  数据格式 17  分时日月  4
    public A08 BREAK_OP_NUM;                     //编程次数  数据格式 08  次  2
    public A08 RESET_NUM;                           //最大需量清零次数  数据格式 08  次  2
    public A10 batteryUsedTime;                 //电池工作时间  数据格式 10  min  3
    // ------------------------------------------------------------------------
     public F27(){}
    public  F27(byte[] in ){
        init(in);
    }
    // ------------------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf = ByteBuffer.wrap(in);
        byte[] tmp;
        // -------------------
        tmp=new byte[readingTime.length()];
        buf.get(tmp);
        readingTime.init(tmp);
        tmp=new byte[METER_CLOCK.length()];
        buf.get(tmp);
        METER_CLOCK.init(tmp);


        Meter_BYTE_CHANGE=buf.get();
        METER_BYTE=buf.get();


        tmp=new byte[Last_Programming_TIME.length()];
        buf.get(tmp);
        Last_Programming_TIME.init(tmp);
        tmp=new byte[Last_Reset_TIME.length()];
        buf.get(tmp);
        Last_Reset_TIME.init(tmp);

        tmp=new byte[BREAK_OP_NUM.length()];
        buf.get(tmp);
        BREAK_OP_NUM.init(tmp);

        tmp=new byte[RESET_NUM.length()];
        buf.get(tmp);
        RESET_NUM.init(tmp);

        tmp=new byte[batteryUsedTime.length()];
        buf.get(tmp);
        batteryUsedTime.init(tmp);

    }
}
