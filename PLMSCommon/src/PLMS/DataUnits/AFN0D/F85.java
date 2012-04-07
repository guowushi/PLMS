package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A09;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求2类数据-----：测量点无功功率曲线
 */
public class F85  extends AbstractFn{
    public Td_C     timeMark;     //曲线类数据时标 Td_c  见5.10.1.3.2条    7
    public A09[]  WP;               //wattless power 无功功率 1-n  数据格式 09  kvar  3
    // ------------------------------------------------------------------------------
    public F85(){}
    public F85(byte[] in){
           init(in);
    }

    // ------------------------------------------------------------------------------
    public  static  int LENGTH;
    @Override
    public int length() {
        int len=0;
        len=len+timeMark.length();
        len=len+A09.LENGTH*timeMark.freezeDataCount;
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
        tmp=new byte[A09.LENGTH];
        for(int i = 0;i<timeMark.freezeDataCount;i++)
        {
            buf.get(tmp);
           WP[i]=new A09(tmp);

        }
    }
}
