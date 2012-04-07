package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A21;

/**
 *  月冻结类数据时标 Td_m 的长度为 2 个字节
 */
public class Td_M {
   public A21 freezeMonth;
    public Td_M(){}
    public Td_M(byte[] in){
        init(in);
    }
    
    // ---------------------------------
    public int length() {
        return freezeMonth.length();
    }
    public byte[] valueOf()
    {
        return freezeMonth.valueOf();
    }
    public void init(byte[] in){
        freezeMonth.init(in);
    }
}
