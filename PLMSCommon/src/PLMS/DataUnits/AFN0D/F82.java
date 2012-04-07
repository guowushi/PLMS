package PLMS.DataUnits.AFN0D;

import PLMS.DataUnits.AbstractFn;

/**
 * 测量点 U 相有功功率曲线
 */
public class F82 extends AbstractFn {
    private F81 fn=new F81();
    @Override
    public int length() {
        return fn.length();
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];
    }

    @Override
    public void init(byte[] in) {
       fn.init(in);
    }
}

