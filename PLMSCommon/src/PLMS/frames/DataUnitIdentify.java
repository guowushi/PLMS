package PLMS.frames;

import java.nio.ByteBuffer;

/**
 * 数据单元标识
 */
public class DataUnitIdentify {
    private byte da2;
    private byte da1;
    private byte dt2;
    private byte dt1;
    private static final int length=4;
    //--------------------------------------------------------
    public DataUnitIdentify() {
    }
    public DataUnitIdentify(byte[] in){init(in);}
    // -------------------------------------------------------
    public int length()
    {
        return length;
    }
    public byte[] valueOf(){
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        buf.put(da1);
        buf.put(da2);
        buf.put(dt1);
        buf.put(dt2);
        return buf.array();
    }
    public  void init(byte[] in)
    {

    }
}
