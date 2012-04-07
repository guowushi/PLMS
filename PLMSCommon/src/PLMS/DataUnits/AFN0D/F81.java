package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A09;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求2类数据--测量点有功功率曲线  ?????????
 */
public class F81 extends AbstractFn {

   public Td_C  timeMark;                                           // 曲线类数据时标 Td_c  见5.10.1.3.2    7
   public A09[]  curvePoints=new A09[timeMark.freezeDataCount]; //有功功率 1-n  数据格式 09  kW  3
    {
        description="";
    }
    // --------------------------------------------------------------
    public F81(){}
    public F81(byte[] in){
             init(in);
    }
    // --------------------------------------------------------------
    @Override
    public int length() {
        return timeMark.length()+curvePoints.length*A09.LENGTH; 
        
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf=ByteBuffer.allocate(this.length());
        byte[] tmp1=new byte[timeMark.length()];
        buf.get(tmp1);
        timeMark.init(tmp1);
        //----------------------------------------------
        curvePoints=new A09[timeMark.freezeDataCount];
        buf=ByteBuffer.allocate(this.length());
        //-----------------------------------------
        byte[] tmp=new byte[A09.LENGTH];
        for(int i=0;i<timeMark.freezeDataCount;i++)
        {
           buf.get(tmp);
           curvePoints[i]=new A09(tmp);  
        }
    }

    @Override
    public String getDescription() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

