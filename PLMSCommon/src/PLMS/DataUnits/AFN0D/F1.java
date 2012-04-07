package PLMS.DataUnits.AFN0D;

import PLMS.DataUnits.AbstractFn;

/**
 * 请求2类数据-- 正向有/无功电能示值、一/四象限无功电能示值（总、费率 1~M）
 */
public class F1 extends AbstractFn {
    public final int LENGTH=12;
    // -------------------------------------------------------------
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
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getDescription() {
        return "正向有/无功电能示值、一/四象限无功电能示值";
    }
}

