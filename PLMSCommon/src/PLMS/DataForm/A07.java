package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 * A07 取值范围(xxx.x)
 */
public class A07 extends AbstractDataFormat {
    public static final int LENGTH=2;
    public double value;
    //----------------------------------------------
    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    //---------------------------------------
    public A07(){

    }
    public A07(byte[] in){
        init(in);
    }
    public A07(double v){
        this.value=v;
    }
    // ---------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }
    @Override
    public byte[] valueOf() {
        byte[] out=new byte[LENGTH];

        try{
            out[0]=(byte) ( (BCD.getBCDBit(value, 1)<<4) + BCD.getBCDBit(value,-1) ) ;
            out[1]=(byte) ( (BCD.getBCDBit(value,3)<<4) + BCD.getBCDBit(value,2) ) ;

        } catch(ParameterInvalidException e) {

        }
        return out;
    }

    @Override
    public void init(byte[] in) {
        double tmp=0;

        tmp=tmp + (in[0] & 0x0F)*0.1;
        tmp=tmp + ((in[0] & 0xF0) >>4)*1 ;
        tmp=tmp + ((in[1] & 0x0F) )*10;
        tmp=tmp + ((in[1] & 0xF0) >>4)*100;
        value=tmp;
    }
}
