package PLMS.DataUnits.AFN0C;

import PLMS.DataUnits.AbstractFn;

/**
 * 请求1类数据--（扩展） 反向无功分相电能示值
 */
public class F132 extends AbstractFn {

    //终端抄表时间  数据格式 15  分时日月年  5
    //正向有功分相电能总  数据格式 14  kWh  5
    //正向有功分相电能 A 相  数据格式 14  kWh  5
    //正向有功分相电能 B 相  数据格式 14  kWh  5
    //正向有功分相电能 C 相  数据格式 14  kWh  5
    @Override
    public int length() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(byte[] in) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
