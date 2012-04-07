package PLMS.DataUnits.AFN0D;

import PLMS.DataUnits.AbstractFn;

/**
 * 请求2类数据-----  B 相 2 次~31 次谐波电流及 B 相电流波形畸变率
 */
public class F150 extends AbstractFn{
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
