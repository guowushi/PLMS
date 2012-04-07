package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

import java.util.Calendar;
import java.util.Date;

/**
 * 数据格式17
 * 月日时分
 */
public class A17 extends AbstractDataFormat {
    public final static  int  LENGTH=4;
    public int Minute;
    public int Hour;
    public int Day;
    public int Month;
    // -------------------------------------------------------
    public A17(){
        Calendar c=Calendar.getInstance();

        Minute=c.get(Calendar.MINUTE);
        Hour=c.get(Calendar.HOUR);
        Day=c.get(Calendar.DAY_OF_MONTH);
        Month=c.get(Calendar.MONTH)+1;
    }
    public A17(byte []in){
         init(in);
    }
    // -----------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }


    @Override
    public byte[] valueOf() {
        byte []ret=new byte[LENGTH];
        try{
        //Minute
        ret[0]=(byte) BCD.getBCDBit(Minute,1);
        ret[0]+=(byte)(BCD.getBCDBit(Minute,2)<<4);
        //Hour
        ret[1]=(byte) BCD.getBCDBit(Hour,1);
        ret[1]+=(byte)(BCD.getBCDBit(Hour,2)<<4);
        //Day
        ret[2]=(byte) BCD.getBCDBit(Day,1);
        ret[2]+=(byte)(BCD.getBCDBit(Day,2)<<4);
        //Month
        ret[3]=(byte) BCD.getBCDBit(Month,1);
        ret[3]+=(byte)(BCD.getBCDBit(Month,2)<<4);
        }catch (ParameterInvalidException e)
        {

        }
        //Minute
        return ret;
    }

    @Override
    public void init(byte[] in) {
        Minute=(in[0]>>4)*10+(in[0]&0xf);
        Hour=  (in[1]>>4)*10+(in[1]&0xf);
        Day=(in[2]>>4)*10+(in[2]&0xf);
        Month=(in[3]>>4)*10+(in[3]&0xf);
    }

    @Override
    public Date getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
/*    public static void main(String []args){
        A17 SlightBevelBorder=new A17();
        byte []buf=SlightBevelBorder.valueOf();
        A17 b=new A17(buf);
        System.out.println(SlightBevelBorder.Minute+" "+SlightBevelBorder.Hour+" "+SlightBevelBorder.Day+" "+SlightBevelBorder.Month);
        System.out.println(b.Minute+" "+b.Hour+" "+b.Day+" "+b.Month);
    }*/
}
