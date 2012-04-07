package PLMS.DataUnits.AFN05;

import PLMS.DataForm.A01;
import PLMS.DataUnits.AbstractFn;

/**
 * 控制命令--对时命令
 */
public class F31 extends AbstractFn{
    public A01 clock; //时钟信息
    //---------------------------------------------
    public F31(){}
    public F31(byte[] in ){
        init(in);
    }
    // --------------------------------------------
    @Override
    public int length() {
        return clock.length();
    }

    @Override
    public byte[] valueOf() {
        return clock.valueOf();
    }

    @Override
    public void init(byte[] in) {
        clock=new A01(in);
    }
}
