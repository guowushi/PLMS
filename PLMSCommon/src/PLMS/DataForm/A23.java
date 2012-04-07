package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 * 数据格式A23
 */
public class A23 extends AbstractDataFormat
{
    public static final int LENGTH = 3;
    private double value;
    // --------------------------------------------
    public A23(){}
    public A23(byte[] in ){
        init(in);
    }
    //----------------------------------------------
    @Override
    public int length() {
        return LENGTH;  //返回长度
    }
    @Override
    public byte[] valueOf()
    {
        byte []out = new byte[LENGTH];
        try{
        out[0] = (byte)((BCD.getBCDBit(value,-3)<<4)+BCD.getBCDBit(value,-4));//千分位和万分位
        out[1] = (byte)((BCD.getBCDBit(value,-1)<<4)+BCD.getBCDBit(value,-2));//十分位和百分位
        out[2] = (byte)((BCD.getBCDBit(value,2)<<4)+BCD.getBCDBit(value,1));//十位数和个位数
        }catch (ParameterInvalidException e)
        {

        }
        return out;
    }

    @Override
    public void init(byte[] in) {

    }

    @Override
    public <T> T getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
