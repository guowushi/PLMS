package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 * 数据格式11
 * 范围：0-xxxxxx.xx
 */
public class A11 extends AbstractDataFormat
{
    public static final int LENGTH = 4;
    public double value;
    //---------------------------------------------------
    public A11(){}
    public A11(byte []in)
    {
         init(in);
    }
    // ------------------------------------------------------------
    @Override
    public int length()
    {
        return LENGTH;  //返回长度
    }
    @Override
    public byte[] valueOf() {
        byte []out = new byte[LENGTH];
        try{
        out[0] = (byte)((BCD.getBCDBit(value,-1)<<4)+BCD.getBCDBit(value,-2));
        out[1] = (byte)((BCD.getBCDBit(value,2)<<4)+BCD.getBCDBit(value,1));
        out[2] = (byte)((BCD.getBCDBit(value,4)<<4)+BCD.getBCDBit(value,3));
        out[3] = (byte)((BCD.getBCDBit(value,6)<<4)+BCD.getBCDBit(value,5));
        }catch (ParameterInvalidException e)
        {}
        return out;
    }

    @Override
    public void init(byte[] in) {
        value = (in[0]>>4)/10.0 + (in[0] & 0x0F)/100.0; //十分位，百分位
        value += (in[1]>>4)*10 + (in[1]& 0x0F);   //十位数，个位数
        value += (in[2]>>4)*1000 + (in[2] & 0x0F)*100;    //千位数百位数
        value += (in[3]>>4)*100000 +(in[3]&0x0F)*10000;  //十万位数，万位数
    }

    @Override
    public Double getValue() {
        return this.value;
    }
}
