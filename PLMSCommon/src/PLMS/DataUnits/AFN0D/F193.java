package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A11;
import PLMS.DataForm.A14;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求2类数据-----  正向有/无功电能示值（总、费率 1~M）
 */
public class F193 extends AbstractFn{
    public  static  int LENGTH;
    public Td_C timeMark;               //曲线类数据时标 Td_c  见 2.1 条    7
    public int feeCount;                //费率数 M  BIN  个  1
    public A14 total_AP;                //正向有功总电能示值  数据格式 14  kWh  5
    //费率 1 正向有功电能示值  数据格式 14  kWh  5
    //        „„  „„  „„  „„
    //费率 M 正向有功电能示值  数据格式 14  kWh  5
    public A14[] fees_AP;
    
    public A11 total_RAP;               //正向无功总电能示值  数据格式 11  kvarh  4
    //费率 1 正向无功电能示值  数据格式 11  kvarh  4
    //        „„  „„  „„  „„
    //费率 M 正向无功电能示值  数据格式 11  kvarh  4
    public A11[] fees_RAP;
    // -------------------------------------------------------------------------------
    public F193(){}
    public F193(byte[] in){init(in);}
    // -------------------------------------------------------------------------------
    @Override
    public int length() {
        int len=0;
        len=len+timeMark.length();
        len=len+1;
        len=len+A14.LENGTH;
        len=len+ A14.LENGTH*fees_AP.length;
        len=len+A11.LENGTH;
        len=len+ A11.LENGTH*fees_RAP.length;
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
        // ---------------------------------
        tmp=new byte[timeMark.length()];
        buf.get(tmp);
        timeMark.init(tmp);
        feeCount=buf.get();
        // ---------------------------------
        tmp=new byte[A14.LENGTH];
        buf.get(tmp);
        total_AP.init(tmp);
        for(int i=0;i<feeCount;i++)
        {
            buf.get(tmp);
            fees_AP[i]=new A14(tmp);
        }
        // ---------------------------------
        tmp=new byte[A11.LENGTH];
        buf.get(tmp);
        total_RAP.init(tmp);
        for(int i=0;i<feeCount;i++)
        {
            buf.get(tmp);
            fees_RAP[i]=new A11(tmp);
        }
    }
}
