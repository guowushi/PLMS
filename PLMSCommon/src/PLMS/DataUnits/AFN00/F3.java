package PLMS.DataUnits.AFN00;

import PLMS.DataUnits.AbstractFn;
import PLMS.frames.DataUnitIdentify;

import java.util.ArrayList;

/**
 * 确认/否认功能的F3（对每个数据单元标识依次进行确认或否定）
 */
public class F3 extends AbstractFn{
    int afn;
    ArrayList<DataUnitIdentify> unitIdentifies;
    @Override
    public int length() {
        return 0;  //
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
