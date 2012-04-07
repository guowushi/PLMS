package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A05;
import PLMS.DataForm.A07;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 ：A 相 2 次~31 次谐波电压及总电压谐波量、A 相电压波形畸变率
 */
public class F145  extends AbstractFn{
    // -----------------------------------------------------------------------
    private int MAX=30;
    public Td_C  timeMark;                      // 曲线类数据时标
    public A05[]  Harms=new A05[MAX];         // 2-31次电压谐波
    public A07 U_Harmonic;                    //   voltage harmonic   电压谐波量
    public A05   U_WDF;                         //waveform distortion factor   电压波形畸变率 
    
   
    // ------------------------------------------------------------------------
    public F145(){}
    public F145(byte[] in){
          init(in);
    }
    //--------------------------------------------------------------------------
    @Override                    
    public int length() {
        int len=0;
        len=timeMark.length();
        len=len+U_Harmonic.length();
        len=len+U_WDF.length();
        len=len+Harms.length*A05.LENGTH;
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
        tmp=new byte[A05.LENGTH];
        for(int i = 0;i<MAX;i++)
        {
            buf.get(tmp);
            Harms[i]=new A05(tmp);

        }
        tmp=new byte[A07.LENGTH];
        buf.get(tmp);
        U_Harmonic.init(tmp);
        tmp=new byte[A05.LENGTH];
        buf.get(tmp);
        U_WDF.init(tmp);
    }
}
