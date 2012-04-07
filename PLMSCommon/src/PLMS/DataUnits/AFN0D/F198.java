package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A11;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求2类数据----- 正向无功分相电能示值
 */
public class F198 extends AbstractFn{
    public final static  int LENGTH=23;
    public Td_C timeMark;       //曲线类数据时标 Td_c  见 2.1    7
    public A11 total_EN;        //分相电能总  数据格式 11  kVarh  4
    public A11 E_A;             //分相电能 A 相  数据格式 11  kVarh  4
    public A11 E_B;             //分相电能 B 相  数据格式 11  kVarh  4
    public A11 E_C;             //分相电能 C 相  数据格式 11  kVarh  4
    //----------------------------------------------------------------------

    public F198(){}
    public F198(byte[] in){
        init(in);
    }
    // ---------------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf=ByteBuffer.wrap(in);
        byte[] tmp;
        tmp=new byte[timeMark.length()];
        buf.get(tmp);
        timeMark.init(tmp);
        tmp=new byte[A11.LENGTH];
        buf.get(tmp);
        total_EN.init(tmp);
        buf.get(tmp);
        E_A.init(tmp);
        buf.get(tmp);
        E_B.init(tmp);
        buf.get(tmp);
        E_C.init(tmp);
    }
}
