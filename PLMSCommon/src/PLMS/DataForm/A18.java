package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

import java.util.Calendar;

/**
 * 数据格式18
 */
public class A18 extends AbstractDataFormat {
    public static int LENGTH=3;
    public int Minute;
    public int Hour;
    public int Day;
    // ----------------------------------------------
    public A18()
    {
        Calendar c=Calendar.getInstance();
        Minute=c.get(Calendar.MINUTE);
        Hour=c.get(Calendar.HOUR);
        Day=c.get(Calendar.DAY_OF_MONTH);
    }
    public  A18(byte []in){
         init(in);
    }
    // -------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte []ret=new byte[LENGTH];
        try{
        //Minute
        ret[0]=(byte) BCD.getBCDBit(Minute, 1);
        ret[0]+=(byte)(BCD.getBCDBit(Minute,2)<<4);
        //Hour
        ret[1]=(byte) BCD.getBCDBit(Hour,1);
        ret[1]+=(byte)(BCD.getBCDBit(Hour,2)<<4);
        //Day
        ret[2]=(byte) BCD.getBCDBit(Day,1);
        ret[2]+=(byte)(BCD.getBCDBit(Day,2)<<4);
        }catch (ParameterInvalidException e)
        {

        }
        return ret;
    }

    @Override
    public void init(byte[] in) {
        Minute=(in[0]>>4)*10+(in[0]&0xf);
        Hour=(in[1]>>4)*10+(in[1]&0xf);
        Day=(in[2]>>4)*10+(in[2]&0xf);
    }

    @Override
    public <T> T getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
/*    public static void main(String []args){
        A18 SlightBevelBorder=new A18();
        byte []buf=SlightBevelBorder.valueOf();
        A18 b=new A18(buf);
        System.out.println(SlightBevelBorder.Minute+" "+SlightBevelBorder.Hour+" "+SlightBevelBorder.Day );
        System.out.println(b.Minute+" "+b.Hour+" "+b.Day );
    }*/
}
