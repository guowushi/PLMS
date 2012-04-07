package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.DataFormException;
import PLMS.Utils.ByteUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * 数据格式01 (时间格式） 
 */
public class A01 extends AbstractDataFormat {
    public static final int LENGTH = 6;
    private String tips="数据格式为：年月日时分秒 星期";
    // -----------------------------------------------
    private int second;
    private int minute;
    private int hour;
    private int day;
    private int weekday;
    private int month;
    private int year;

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // ---------------------构造函数-----------------------------
    public A01() {
        Calendar CD = Calendar.getInstance();
        year = CD.get(Calendar.YEAR) ;
        month = CD.get(Calendar.MONTH)+1;
        day = CD.get(Calendar.DATE);
        hour= CD.get(Calendar.HOUR);
        minute = CD.get(Calendar.MINUTE);
        second = CD.get(Calendar.SECOND);
        weekday=CD.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * @param in
     *
     * */
    public A01(byte[] in) {
         init(in);
    }
     
    //---------------------需要重写的方法--------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte[] out=new byte[LENGTH];
        out[0]= ByteUtil.str2Bcd(Integer.toString(second))[0];
        out[1]= ByteUtil.str2Bcd(Integer.toString(minute))[0];
        out[2]= ByteUtil.str2Bcd(Integer.toString(hour))[0];
        out[3]= ByteUtil.str2Bcd(Integer.toString(day))[0];
        byte w= ByteUtil.str2Bcd(Integer.toString(weekday))[0];
        byte m=  ByteUtil.str2Bcd(Integer.toString(month))[0];
        out[4]=(byte) ((w<<5) + m);
        out[5]= ByteUtil.str2Bcd(Integer.toString(year))[0];
        return out;

    }

    @Override
    public void init(byte[] in)  {

        year =  (in[5]>>4)*10 + (in[5] & 0x0F)   ;
        month = ((in[4]>>4) & 0x03) *10 + (in[4] & 0x0F) ;
        weekday=((in[4]>>4) & 0x03);
        day = (in[3]>>4)*10 + (in[3] & 0x0F) ;
        hour= (in[2]>>4)*10 + (in[2] & 0x0F) ;
        minute =(in[1]>>4)*10 + (in[1] & 0x0F) ;
        second =(in[0]>>4)*10 + (in[0] & 0x0C) ;

    }

    @Override
    public Date getValue() {
        //Date d=new Date();
        Calendar cal =Calendar.getInstance();
        cal.set(year, month, day, hour, minute, second);
        return cal.getTime();
    }
}
