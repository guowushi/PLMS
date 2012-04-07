package PLMS.DataUnits.AFN02;
import PLMS.DataUnits.AbstractFn;

/**
 * 链路检测-F1登录
 */
public class F1 extends AbstractFn{
    public  static final int LENGTH=0;
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        return null;
    }

    @Override
    public void init(byte[] in) {

    }
}
