package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 * A06  -xx.xx +xx.xx
 */
public class A06  extends AbstractDataFormat {
    public static final int LENGTH=2;

    public  double value;
    private int sign;
    //--------------------------------------
    public A06(){  }
    public A06(byte[] in){
       init(in);
    }
    // -------------------------------------
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
            out[0]=(byte) ( (BCD.getBCDBit(value, -1)<<4) + BCD.getBCDBit(value,-2) ) ;
            out[1]=(byte) ( (sign<<7)+(BCD.getBCDBit(value,2)<<4) + BCD.getBCDBit(value,1) ) ;

        } catch(ParameterInvalidException e) {

        }
        return out;
    }

    @Override
    public void init(byte[] in) {
        double tmp=0;

        tmp=tmp + (in[0] & 0x0F)*0.01;
        tmp=tmp + ((in[0] & 0xF0) >>4)*0.1 ;
        tmp=tmp + ((in[1] & 0x0F) )*1;
        tmp=tmp + ((in[1] & 0x70) >>4)*10;
        sign= (in[1] &0x80)>>7;
        if(sign==1)tmp=tmp*(-1);
        value=tmp;
    }

    @Override
    public Double getValue() {
        return this.value;
    }
}
