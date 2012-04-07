package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Utils.ByteUtil;

/**
 * 数据格式4-数字
 * 0-79
**/
public class A04  extends AbstractDataFormat {
    public static final int LENGTH=1;
    private int s0;     // 0-上浮；1-下浮
    public int value;
    //----------------------------------------------------
    public A04(){

    }
    public A04(byte[] in){
           init(in);
    }
    //----------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte[] out=new byte[LENGTH];
        out[0]=(byte) ((s0 <<7) + value);
        return out;
    }

    @Override
    public void init(byte[] in) {
        s0=in[0] & 0x80;
        in[0] =(byte) (in[0] & 0xBF);
        value=Integer.parseInt( ByteUtil.bcd2Str(in));
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
