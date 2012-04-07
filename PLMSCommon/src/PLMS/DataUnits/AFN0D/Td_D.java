package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A20;

/**
 * Created by IntelliJ IDEA.
 日冻结类数据时标 Td_d 的长度为 3 个字节，
 */
public class Td_D {
    public A20 freezeDay;
    //-------------------------------
    public Td_D(){}
    public Td_D(byte[] in){
           init(in);
    }
    // ---------------------------------
    public int length() {
        return freezeDay.length();
    }
    public byte[] valueOf()
    {
        return freezeDay.valueOf();
    }
    public void init(byte[] in){
           freezeDay.init(in);
    }
}
