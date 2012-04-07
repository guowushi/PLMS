package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

import java.util.Calendar;

/**
 * 数据格式19
 */
public class A19 extends AbstractDataFormat {
    public static final  int LENGTH=2;
    public int Minute;
    public int Hour;
    // -------------------------------------------------
    public A19()
    {
        Calendar c=Calendar.getInstance();
        Minute=c.get(Calendar.MINUTE);
        Hour=c.get(Calendar.HOUR);
    }
    public  A19(byte []in){
        init(in);
    }
    // --------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte[] valueOf() {
        byte []ret=new byte[LENGTH];

        //Minute
        try{
        ret[0]=(byte) BCD.getBCDBit(Minute, 1);
        ret[0]+=(byte)(BCD.getBCDBit(Minute,2)<<4);
        //Hour
        ret[1]=(byte) BCD.getBCDBit(Hour,1);
        ret[1]+=(byte)(BCD.getBCDBit(Hour,2)<<4);
        }catch (ParameterInvalidException e)
        {

        }
        return ret;
    }

    @Override
    public void init(byte[] in) {
        Minute=(in[0]>>4)*10+(in[0]&0xf);
        Hour=(in[1]>>4)*10+(in[1]&0xf);
    }

    @Override
    public <T> T getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
/*    public static void main(String []args){
        A19 SlightBevelBorder=new A19();
        byte []buf=SlightBevelBorder.valueOf();
        A19 b=new A19(buf);
        System.out.println(SlightBevelBorder.Minute+" "+SlightBevelBorder.Hour);
        System.out.println(b.Minute+" "+b.Hour);
    }*/
}
