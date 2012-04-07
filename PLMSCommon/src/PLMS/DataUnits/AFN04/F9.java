package PLMS.DataUnits.AFN04;

import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 参数设置(04)-终端配置数量
 */
public class F9 extends AbstractFn{
    public static  final  int LENGTH=4;
    public int meterCount; // 电能表/交流采样装置配置总块数
    public int plusCount;  //  脉冲配置路数 
    public int analogCount;              // 电压/电流模拟量配置总路数
    public int groupCount;      //总加组配置总组数
    // -----------------------------------------------------------------
    public F9(){}
    public F9(byte[] in){
        init(in);
    }
    // -----------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;   
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        buf.put((byte)meterCount);
        buf.put((byte)plusCount);
        buf.put((byte)analogCount);
        buf.put((byte)groupCount);
        return buf.array();   
    }

    @Override
    public void init(byte[] in) {
       meterCount=in[0];
       plusCount=in[1];
       analogCount=in[2];
      groupCount=in[3];
    }
}
