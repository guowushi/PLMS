package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A07;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 测量点 U 相电压曲线 
 */
public class F89 extends AbstractFn {


    public Td_C     timeMark;      //曲线类数据时标 Td_c  见5.10.1.3.2条    7
    public A07[]  U;              //电压数据 1-n  数据格式 07  V  2

   // ------------------------------------------------------------------------
    public F89(){}
    public F89(byte[] in ){
        init(in);
    }

    // ------------------------------------------------------------------------
    @Override
    public int length() {
        int len=0;
        len=len+timeMark.length();
        len=len+A07.LENGTH*timeMark.freezeDataCount;
        return len;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf=ByteBuffer.wrap(in);
        byte[] tmp;
        tmp=new byte[timeMark.length()];
        buf.get(tmp);
        timeMark.init(tmp);
        tmp=new byte[A07.LENGTH];
        for(int i = 0;i<timeMark.freezeDataCount;i++)
        {
            buf.get(tmp);
            U[i]=new A07(tmp);

        }
    }
}

