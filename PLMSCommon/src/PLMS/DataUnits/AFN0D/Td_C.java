package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A15;

import java.nio.ByteBuffer;

/**
 曲线类数据时标 Td_c
 表示所请求或响应的数据是从起始时间 ts 起，以冻结密度 m 为时间间隔的连续 n 个点的曲线数据。
 */
public class Td_C {
    private final  int LENGTH=7;     //
    public A15 startTime;            //曲线数据的起始时间 ts  ,5字节
    public FreezeDensity freezeDensity;        //数据冻结密度 m
    public int freezeDataCount;       //数据点数 n
    // ------------------------------
    public Td_C(){}
    public Td_C(byte[] in){
           init(in);
    }
    // ----------------------------------
    public int  length()
    {
        return LENGTH;
    }
    public byte[] valueOf()
    {
        ByteBuffer buf=ByteBuffer.allocate(this.length());
        buf.put(startTime.valueOf());
        buf.put((byte)freezeDensity.valueOf());
        buf.put((byte)freezeDataCount);
        return buf.array();
    }
    public void init(byte[] in)
    {
        ByteBuffer buf=ByteBuffer.wrap(in);
        byte[] tmp=new byte[startTime.length()];
        buf.get(tmp);
        startTime.init(tmp);
        freezeDensity.setValue(buf.get());
        freezeDataCount=buf.get();
    } 
    
}
