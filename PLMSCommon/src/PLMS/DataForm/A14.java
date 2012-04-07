package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;
import PLMS.Utils.*;

/**
 * 数据格式14
 * 范围:0-xxxxxx.xxxx
 */
public class A14 extends AbstractDataFormat {
    public  static int  LENGTH=5;
    public  double value;
    // -------------------------------------------------------------
    public  A14(){}
    public A14(byte []in){
         init(in);
    }
    // ------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte []ret=new byte[LENGTH];

        int postion=-4;
        int i=0;
        int bcd=0;
        try{
        for(;postion<0;){
            bcd= BCD.getBCDBit(value,postion++);
            ret[i]=(byte)bcd;
            bcd=BCD.getBCDBit(value,postion++);
            ret[i]+=(((byte)bcd)<<4);
            i++;
        }
        postion++;
       // i--;
        for(;postion<7;)
        {
            bcd=BCD.getBCDBit(value,postion++);
            ret[i]=(byte)bcd;
            bcd=BCD.getBCDBit(value,postion++);
            ret[i]+=(((byte)bcd)<<4);

             i++;
        }
        }catch (ParameterInvalidException e){}
        return ret;
    }

    @Override
    public void init(byte[] in) {
        StringBuffer sb=new StringBuffer();
        for(int i=in.length-1;i>=0;i--){
            char ch= (char)((in[i]>>4)+'0');
            sb.append(ch);
            ch=(char)(in[i]&0xF+'0');
            sb.append(in[i]&0xF);
            if(i==2)
                sb.append('.');
        }
        value=Double.parseDouble(sb.toString());
    }

    @Override
    public <T> T getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
