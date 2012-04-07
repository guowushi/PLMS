package PLMS.DataForm;
import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

/**
 * 数据格式A15
 * 年月日分
 */
public class A15 extends AbstractDataFormat {

    public  static int LENGTH=5;
    public int Minute;
    public int Hour;
    public int  Day;
    public int Month;
    public int  Year;
    // -----------------------------------------
    public  A15()
    {
        Calendar c= Calendar.getInstance();
        Year=c.get(Calendar.YEAR)-1900;
        Hour=c.get(Calendar.HOUR_OF_DAY);
        Day=c.get(Calendar.DAY_OF_MONTH);
        Month=c.get(Calendar.MONTH);
        Minute=c.get(Calendar.MINUTE);

    }
    public A15(byte []in){
        init(in);
    }
    // ------------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte []ret=new byte[LENGTH];
        try{
        //Minute
        byte bcd=(byte)BCD.getBCDBit(Minute,1);
        ret[0]=bcd;
        bcd=(byte)BCD.getBCDBit(Minute,2);
        ret[0]+=(bcd<<4);
        //Hour
        bcd=(byte)BCD.getBCDBit(Hour,1);
        ret[1]=bcd;
        bcd=(byte)BCD.getBCDBit(Hour,2);
        ret[1]+=(bcd<<4);
        //Day
        bcd=(byte)BCD.getBCDBit(Day,1);
        ret[2]=bcd;
        bcd=(byte)BCD.getBCDBit(Day,2);
        ret[2]+=(bcd<<4);
        //Minute
        bcd=(byte)BCD.getBCDBit(Month,1);
        ret[3]=bcd;
        bcd=(byte)BCD.getBCDBit(Month,2);
        ret[3]+=(bcd<<4);
        //Year
        bcd=(byte)BCD.getBCDBit(Year,1);

        ret[4]=bcd;
        bcd=(byte)BCD.getBCDBit(Year,2);

        ret[4]+=(bcd<<4);
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
        Month=(in[3]>>4)*10+(in[3]&0xf);
        Year=(in[4]>>4)*10+(in[4]&0xf);
    }

    @Override
    public Date getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    /*  public static void main(String []args){
        A15 SlightBevelBorder=new A15();
        byte []buf=SlightBevelBorder.valueOf();
        A15 b=new A15(buf);
        System.out.println(SlightBevelBorder.Year+"-"+SlightBevelBorder.Month+"-"+SlightBevelBorder.Day+"-"+SlightBevelBorder.Hour+"-"+SlightBevelBorder.Minute);
        System.out.println(b.Year+"-"+b.Month+"-"+b.Day+"-"+b.Hour+"-"+b.Minute);
    }*/
}
