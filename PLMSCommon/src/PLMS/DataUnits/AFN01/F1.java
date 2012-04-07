package PLMS.DataUnits.AFN01;

import PLMS.DataUnits.AbstractFn;

/**
 * 复位命令-硬件初始化
 */
public class F1 extends AbstractFn{
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
