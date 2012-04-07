package PLMS.DataUnits.AFN00;
import PLMS.DataUnits.AbstractFn;

/**
 * 确认/否认功能的F1（全部确认）
 */
public class F1 extends AbstractFn {
    public  static final int LENGTH=0;
    public  int f1;
    @Override
    public int length() {
         return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];
    }

    @Override
    public void init(byte[] in) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
