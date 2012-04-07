package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 * 数据格式A02: 有符号数
 * 科学计数法： xxx*e-3 --- xxx*e4
 */
public class A02 extends AbstractDataFormat {
    public static final int LENGTH = 2;
    private String tips="";
    //----------------------------------------
    private double value;
    private int power;           //10e power
    private int sign;            // 符号位

    // --------------------------------------------
    public A02() {  }
    public A02(byte[] in) {
            init(in);
    }
    // ---------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte[] out = new byte[LENGTH];
        if (value > 0) {
            sign = 0;
        } else {
            sign = 1;
        }
        try{
        out[0]=(byte) ( (BCD.getBCDBit(value, 2)<<4) + BCD.getBCDBit(value,1) ) ;
        out[1]=(byte) (  (power<<5)+ (sign<<4) + BCD.getBCDBit(value,7) ) ;
        }
        catch (ParameterInvalidException e)
        {

        }
        return out;
    }

    @Override
    public void init(byte[] in) {
        double tmp=1;
        tmp=tmp * (in[0] & 0x0F)*1;
        tmp=tmp *  (in[0] >>4)*10;
        tmp=tmp * (in[1] & 0x0F)*100;
        sign=in[1] & 0x10;
        power=4-(in[1] &0xE0);
        if(sign==1)value=tmp*(-1)* power;
    }

    @Override
    public Double getValue() {
        double ret=0;
        ret=value;
        return ret;
    }
}
