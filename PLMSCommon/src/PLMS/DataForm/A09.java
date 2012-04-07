package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 * 数据格式A09
 */
public class A09  extends AbstractDataFormat
{
    public static final int LENGTH = 3;
    private double value;//值
    private int sign;  //符号 0正 1负
    //----------------------------------------------------------
    public A09(){}

    public A09(byte []in)
    {
        init(in);
    }
    //-------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;  //返回长度
    }

    @Override
    public byte[] valueOf() {
        byte []out = new byte[LENGTH];
        
        if(value>0)
        {
            sign = 0;
        }
        else 
        {
            sign = 1;
        }
        try{
        out[0] =(byte)((BCD.getBCDBit(value,-3)<<4)+(BCD.getBCDBit(value,-4)));//千分位和万分位
        out[1] = (byte)((BCD.getBCDBit(value,-1)<<4+BCD.getBCDBit(value,-2)));//十分位和百分位
        out[2] = (byte)((((sign<<3)+BCD.getBCDBit(value,2))<<4) + BCD.getBCDBit(value,1));//符号位，和十位数百位数
        }catch (ParameterInvalidException e){}
         return out;
        
    }

    @Override
    public void init(byte[] in) {
        value = (in[0]>>4)/1000.0f + (in[0]&0x0F)/10000.0f + (in[1]>>4)/10.0f + (in[1] & 0x0F)/100.0f + (in[2]<<1>>5)*10+(in[0]&0x0F);
        sign = in[2]>>7;
        value *= sign==1?-1:1;
    }

    @Override
    public Double getValue() {
        return this.value;
    }
}
