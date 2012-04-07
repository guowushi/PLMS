package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 * 数据格式 13
 * 范围：0-xxxx.xxxx
 */
public class A13 extends AbstractDataFormat {
    public static final int LENGTH = 4;
    public double value;
    //-----------------------------------------------
    public A13()     {}
    public  A13(double  value){this.value=value;}

    public A13(byte []in)
    {
        init(in);
    }
    // -----------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte []ret=new byte[LENGTH];
        
             try{
            int bcd=BCD.getBCDBit(value,-4);
            ret[0]=(byte)bcd;
            bcd=BCD.getBCDBit(value,-3);
            ret[0]+=(((byte)bcd)<<4);

            bcd=BCD.getBCDBit(value,-2);
            ret[1]=(byte)bcd;
            bcd=BCD.getBCDBit(value,-1);
            ret[1]+=(((byte)bcd)<<4);

            bcd=BCD.getBCDBit(value,1);
            ret[2]=(byte)bcd;
            bcd=BCD.getBCDBit(value,2);
            ret[2]+=(((byte)bcd)<<4);

            bcd=BCD.getBCDBit(value,3);
            ret[3]=(byte)bcd;
            bcd=BCD.getBCDBit(value,4);
            ret[3]+=(((byte)bcd)<<4);
             }catch (ParameterInvalidException e)
             {}

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
        value=Double.valueOf(sb.toString());

    }

    @Override
    public Double getValue() {
        return this.value;
    }
}
