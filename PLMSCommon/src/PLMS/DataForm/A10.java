package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 *数据格式A10
 * 范围：0-xxxxxx
 */
public class A10 extends AbstractDataFormat
{
    public static final int LENGTH = 3;
    private int value;
    //-------------------------------------------------------
    public A10(){}
    public A10(byte []in)
    {
        init(in);
    }
    //-------------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;  //返回长度
    }
    @Override
    public byte[] valueOf() {
        byte []out = new byte[LENGTH];
        try{
        out[0] = (byte)((BCD.getBCDBit(value,2)<<4)+BCD.getBCDBit(value,1));//十位数和个位数
        out[1] = (byte)((BCD.getBCDBit(value,4)<<4)+BCD.getBCDBit(value,3));//千位数和百位数
        out[2] = (byte)((BCD.getBCDBit(value,6)<<4)+BCD.getBCDBit(value,5));//十万位数和万位数
        }catch (ParameterInvalidException e)
        {

        }
        return out;
    }
    @Override
    public void init(byte[] in) {
        value = (in[0]>>4)*10+(in[0] & 0x0F) + (in[1]>>4)*1000+(in[1] & 0x0F)*100+(in[2]>>4)*100000+(in[2]&0x0F)*10000;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
