package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 * 数据格式A22
 */
public class A22 extends AbstractDataFormat
{
    public static final int LENGTH = 1;
    private  int value;
    //---------------------------------------------
    public A22() {}
    public  A22(byte []in)
    {
       init(in);
    }

    @Override
    public int length() {
        return LENGTH; 
    }

    @Override
    public byte[] valueOf() {
        byte[] out = new byte[LENGTH];
        try{
        int ten_bit = BCD.getBCDBit(value,2); //取十位数
        int ge_bit = BCD.getBCDBit(value,1);  //取个位数

        out[0] = (byte)((ten_bit<<4) + ge_bit);
        }catch (ParameterInvalidException e)
        {

        }
        return out;
    }

    @Override
    public void init(byte[] in) {
        value = (in[0]>>4)*10+(in[0]&0x0F);
    }

    @Override
    public <T> T getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
