package PLMS.DataUnits.AFN0C;

import PLMS.DataUnits.AbstractFn;

/**
 * 请求1类数据--透传设备数据
 */
public class F137 extends AbstractFn {

    public byte[] data;
    //透传设备数据回复帧   满足 DL/T  645-2007 协议      上行数据格式     变长
    // -----------------------------------------------------------------------------

    // -----------------------------------------------------------------------------
    @Override
    public int length() {
        return data.length;
}

    @Override
    public byte[] valueOf() {
        return this.data;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(byte[] in) {
       data=in;
    }
}
