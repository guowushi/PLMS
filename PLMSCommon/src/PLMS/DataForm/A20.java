package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Utils.ByteUtil;

import java.util.Calendar;
/**
 * 数据格式A20
 * Date: 12-2-19
 * Time: 下午3:22
 */
public class A20 extends AbstractDataFormat
{
    public static final int LENGTH = 3;

    private int day; //日
    private int month; //月
    private int year;//年

    public  A20()  //无参数构造函数
    {
        Calendar cd = Calendar.getInstance(); //日期实例
        day = cd.get( Calendar.DATE); //月
        month = cd.get(Calendar.MONTH);//日
        year = cd.get(Calendar.YEAR);  //年

    }
    public A20(byte [] in)//从字节数组构造
    {
        init(in);
    }
    @Override
    public int length()
    {
        return LENGTH;  //返回字节长度
    }

    @Override
    public byte[] valueOf()
    {
        byte [] out = new byte[LENGTH];
        /*int ten_bit;
        int ge_bit;
        ten_bit =  day/10;
        ge_bit = day%10;
        out[0] = (byte)(ten_bit<<4+ge_bit); */
        out[0] = ByteUtil.str2Bcd(Integer.toString(day))[0];
        out[1] = ByteUtil.str2Bcd(Integer.toString(month))[0];
        out[2] = ByteUtil.str2Bcd(Integer.toString(year))[0];
        return  out;
    }

    @Override
    public void init(byte[] in) {
        day = (in[0]>>4)*10+(in[0]&0x0F);
        month = day = (in[1]>>4)*10+(in[1]&0x0F);
        year = (in[2]>>4)*10+(in[2]&0x0F);

    }

    @Override
    public <T> T getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
