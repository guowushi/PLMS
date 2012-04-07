package PLMS.DataUnits.AFN0C;

import PLMS.DataForm.A05;
import PLMS.DataForm.A07;
import PLMS.DataUnits.AFN0D.Td_C;
import PLMS.DataUnits.AbstractFn;

/**
 * 请求1类数据-- （扩展）反向有功分相电能示值
 */
public class F131 extends AbstractFn{
   private final int LENGTH=71;
   public Td_C   timeMark;              // 曲线类数据时标 Td_c  见 2.1    7
   public A05[]   aaa =new A05[30];      //  2 次---31 A 相电压谐波  数据格式 05  %  2
   public A07 v;                     // A 相电压谐波量  数据格式 07  V  2
   public A05  v2;                      // A 相电压波形畸变率  数据格式 05  %  2
    // --------------------------------------------------------------
    public F131(){}
    public F131(byte[] in ){
        init(in);
    }

    // --------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(byte[] in) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
