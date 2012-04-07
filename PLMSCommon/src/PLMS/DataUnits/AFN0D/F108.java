package PLMS.DataUnits.AFN0D;

import PLMS.DataUnits.AbstractFn;

/**
 * 请求2类数据-----
 */
public class F108 extends AbstractFn{
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
