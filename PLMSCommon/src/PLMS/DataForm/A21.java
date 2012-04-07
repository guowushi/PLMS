package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Utils.ByteUtil;

import java.util.Calendar;

/**
 * 数据格式A21
 */
public class A21 extends AbstractDataFormat
{
    public static final  int LENGTH = 2;
    private  int month;
    private  int year;
    //---------------------------------------------------------
    public A21()
    {
        Calendar cd = Calendar.getInstance();
        month = cd.get(Calendar.MONTH);
        year = cd.get(Calendar.YEAR); 
    }
    public  A21(byte []in)
    {
        init(in);
    }
    // -----------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;  //获取长度
    }

    @Override
    public byte[] valueOf() {
        byte [] out = new byte[LENGTH];
        out[0] = ByteUtil.str2Bcd(Integer.toString(month))[0];
        out[1] = ByteUtil.str2Bcd(Integer.toString(year))[0];
        return out;
    }

    @Override
    public void init(byte[] in) {
        month = (in[0]>>4) *10+(in[0]&0x0F);
        year = (in[1]>>4) *10+(in[1]&0x0F);
    }

    @Override
    public <T> T getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
