package PLMS.DataUnits.AFN0D;

import PLMS.DataUnits.AbstractFn;

/**
 * 请求2类数据----- C 相 2 次~31 次谐波电压及 C 相电压波形畸变率
 */
public class F147 extends AbstractFn{
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
