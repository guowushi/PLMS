package PLMS.DataUnits.AFN0C;

import PLMS.DataForm.A01;
import PLMS.DataUnits.AbstractFn;

/**
 * 终端日历时钟
 */
public class F2 extends AbstractFn{
    private final int LENGTH=6;
    private A01 terminalDate=new A01();
    // ----------------------------------------------------------
    public F2(){}
    public F2(byte[] in){
        init(in);
    }
    // ----------------------------------------------------------
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
        terminalDate.init(in);
    }
}
