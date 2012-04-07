package PLMS.DataUnits.AFN0D;


import PLMS.DataForm.A06;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求2类数据-----
 */
public class F92 extends AbstractFn{

    public Td_C     timeMark;   //曲线类数据时标 Td_c  见5.10.1.3.2条    7
    public A06[]     I;         // 电流数据 1-n  数据格式 06  A  2
    // ----------------------------------------------------------------------------
    public F92(){}
    public F92(byte[] in ){
        init(in);
    }
    // ---------------------------------------------------------------------------
    public  static  int LENGTH;
    @Override
    public int length() {
        int len=0;
        len=len+timeMark.length();
        len=len+ A06.LENGTH*timeMark.freezeDataCount;
        return len;
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
        tmp=new byte[A06.LENGTH];
        for(int i = 0;i<timeMark.freezeDataCount;i++)
        {
            buf.get(tmp);
            I[i]=new A06(tmp);

        }
    }
}
