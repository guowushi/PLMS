package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A05;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求2类数据-----  ：A 相 2 次~31 次谐波电流及总电流谐波量、A 相电流波形畸变率
 */
public class F149 extends AbstractFn{
    //public  static  int LENGTH;
    private int MAX=30;
    public Td_C  timeMark;                      // 曲线类数据时标
    public A05[]  Harms=new A05[MAX];         // 2-31次电流谐波
    public A05 I_Harmonic;                    //   voltage harmonic   电流谐波量
    public A05   I_WDF;                         //waveform distortion factor   电流波形畸变率
    //--------------------------------------------------------------------------------

    public  F149(){}
    public F149(byte[] in){init(in);}
    // -------------------------------------------------------------------------------
    @Override
    public int length() {
        int len=0;
        len=timeMark.length();
        len=len+I_Harmonic.length();
        len=len+I_WDF.length();
        len=len+Harms.length*A05.LENGTH;
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
        tmp=new byte[A05.LENGTH];
        for(int i = 0;i<MAX;i++)
        {
            buf.get(tmp);
            Harms[i]=new A05(tmp);

        }
        tmp=new byte[A05.LENGTH];
        buf.get(tmp);
        I_Harmonic.init(tmp);
        tmp=new byte[A05.LENGTH];
        buf.get(tmp);
        I_WDF.init(tmp);
    }
}
