package PLMS.DataUnits.AFN0D;

import PLMS.DataUnits.AbstractFn;

/**
 * 请求2类数据-----  反向无功分相电能示值
 */
public class F200 extends AbstractFn{
    public  static  int LENGTH;
    @Override
    public int length() {
        return 0;   
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  
    }

    @Override
    public void init(byte[] in) {
       
    }
}
