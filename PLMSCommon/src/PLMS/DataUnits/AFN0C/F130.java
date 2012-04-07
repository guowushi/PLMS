package PLMS.DataUnits.AFN0C;

import PLMS.DataForm.A11;
import PLMS.DataForm.A15;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求1类数据--（扩展）正向无功分相电能示值
 */
public class F130 extends AbstractFn
{
    private final int LENGTH=25;
    public A15 readingTime;             //终端抄表时间  数据格式 15  分时日月年  5
    public A11 PRP_R_TOTAL;             //正向无功分相电能总  数据格式 11  kVarh  4
    public A11 PRP_R_A;                 //正向无功分相电能 A 相  数据格式 11  kVarh  4
    public A11 PRP_R_B;                 //正向无功分相电能 B 相  数据格式 11  kVarh  4
    public A11 PRP_R_C;                 //正向无功分相电能 C 相  数据格式 11  kVarh  4
    // --------------------------------------------------------------------------------------
    public F130(){}
    public F130(byte[] in){
        init(in);
    }
    // --------------------------------------------------------------------------------------
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
        ByteBuffer buf= ByteBuffer.wrap(in);
        byte[] tmp;
        tmp=new byte[readingTime.length()];
        buf.get(tmp);
        readingTime.init(tmp);
        tmp=new byte[PRP_R_TOTAL.length()];
        buf.get(tmp);
        PRP_R_TOTAL.init(tmp);
        tmp=new byte[PRP_R_A.length()];
        buf.get(tmp);
        PRP_R_A.init(tmp);
        tmp=new byte[PRP_R_B.length()];
        buf.get(tmp);
        PRP_R_B.init(tmp);
        tmp=new byte[PRP_R_C.length()];
        buf.get(tmp);
        PRP_R_C.init(tmp);
    }
}
