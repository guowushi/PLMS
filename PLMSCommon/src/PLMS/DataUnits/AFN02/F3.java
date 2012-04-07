package PLMS.DataUnits.AFN02;

import PLMS.DataUnits.AbstractFn;

/**
 * 链路检测--心跳
 */
public class F3 extends AbstractFn {
    public final static int LENGTH=0;
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
