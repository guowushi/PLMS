package PLMS.DataUnits.AFN00;

import PLMS.DataUnits.AbstractFn;

/**
 * 确认/否认功能的F2（全部否认）
 */
public class F2  extends AbstractFn{
    private static final int LENGTH=0;
    public int f2;
    @Override
    public  int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];
    }

    @Override
    public void init(byte[] in) {

    }
}
