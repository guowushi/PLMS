package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 *  数据格式A05:
 *  取值范围 -xxx.x至 +xxx.x
 */
public class A05 extends AbstractDataFormat{
    public static final int LENGTH=2;
    public double value;
    private int sign;   // 正负 0-正；1-负
    //-----------------------------------------
    public A05() {
        
    }
    public A05(byte[] in){
        init(in);
    }
    //-----------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte[] out=new byte[LENGTH];
        if(value>0)
        {
            sign=0;
        }else {
            sign=1;
        }
        try{
            out[0]=(byte) ( (BCD.getBCDBit(value,1)<<4) + BCD.getBCDBit(value,-1) ) ;
            out[1]=(byte) ( (sign<<7)+(BCD.getBCDBit(value,3)<<4) + BCD.getBCDBit(value,2) ) ;

        } catch(ParameterInvalidException e) {

        }
        return out;   
    }

    @Override
    public void init(byte[] in) {
        double tmp=0;

        tmp=tmp + (in[0] & 0x0F)*0.1;
        tmp=tmp + (double)((in[0] & 0xF0) >>4) ;
        tmp=tmp + (double)((in[1] & 0x0F) )*10;
        tmp=tmp + (double)((in[1] & 0x70) >>4)*100;
        sign= (in[1] &0x80)>>7;
        if(sign==1)tmp=tmp*(-1);
        value=tmp;
    }

    @Override
    public Double getValue() {
        return this.value;
    }
}
