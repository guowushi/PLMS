package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;
import sun.nio.cs.StreamEncoder;

import java.util.Calendar;
import java.util.Date;

/**
 * 数据格式16
 * 日时分秒
 */
public class A16 extends AbstractDataFormat {
    public final static int LENGTH=4;
    public int Second;
    public int Minute;
    public int Hour;
    public int Day;
    // --------------------------------------------------------
    public A16(){
        Calendar c=Calendar.getInstance();
        Second=c.get(Calendar.SECOND);
        Minute=c.get(Calendar.MINUTE);
        Hour=c.get(Calendar.HOUR);
        Day=c.get(Calendar.DAY_OF_MONTH);
    }
    public  A16(byte []in){
          init(in);
    }
    //---------------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte []ret=new byte[LENGTH];
        try{
        //Second
        ret[0]=(byte) BCD.getBCDBit(Second,1);
        ret[0]+=(byte)(BCD.getBCDBit(Second,2)<<4);
        //Minute
        ret[1]=(byte) BCD.getBCDBit(Minute,1);
        ret[1]+=(byte)(BCD.getBCDBit(Minute,2)<<4);
        //Hour
        ret[2]=(byte) BCD.getBCDBit(Hour,1);
        ret[2]+=(byte)(BCD.getBCDBit(Hour,2)<<4);
        //Day
        ret[3]=(byte) BCD.getBCDBit(Day,1);
        ret[3]+=(byte)(BCD.getBCDBit(Day,2)<<4);
        } catch (ParameterInvalidException e)
        {}
        return ret;
    }
    @Override
    public void init(byte[] in) {
        Second=(in[0]>>4)*10+(in[0]&0xf);
        Minute=(in[1]>>4)*10+(in[1]&0xf);
        Hour=(in[2]>>4)*10+(in[2]&0xf);
        Day=(in[3]>>4)*10+(in[3]&0xf);
    }

    @Override
    public Date getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
/*    public static void main(String []args){
        A16 SlightBevelBorder=new A16();
        byte []buf=SlightBevelBorder.valueOf();
        A16 b=new A16(buf);
        System.out.println(SlightBevelBorder.Day+"-"+SlightBevelBorder.Hour+"-"+SlightBevelBorder.Minute);
        System.out.println(b.Day+"-"+b.Hour+"-"+b.Minute);
    }*/
}
