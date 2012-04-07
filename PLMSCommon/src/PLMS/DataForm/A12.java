package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;

/**
 * 数据格式 12   （范围：0-xxxxxxxxxxxx)
 */
public class A12 extends AbstractDataFormat {
    private  final  int LENGTH=6;
    public Long value;
    //---------------------------------------------
    public A12(){}
    public A12(Long v){
         this.value=v;
    }
    public A12(byte[] in)
    {
        init(in);
    }
    //----------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        byte[] ret=new byte[LENGTH];

        try{
            int bcd= BCD.getBCDBit(value, 1);   // 个位
            ret[0]=(byte)bcd;
            bcd=BCD.getBCDBit(value,2);        // 十位
            ret[0]+=(((byte)bcd)<<4);

            bcd=BCD.getBCDBit(value,3);      // 百位
            ret[1]=(byte)bcd;
            bcd=BCD.getBCDBit(value,4);        // 千位
            ret[1]+=(((byte)bcd)<<4);

            bcd=BCD.getBCDBit(value,5);         // 万位
            ret[2]=(byte)bcd;
            bcd=BCD.getBCDBit(value,6);         // 十万位
            ret[2]+=(((byte)bcd)<<4);

            bcd=BCD.getBCDBit(value,7);         // 百万位
            ret[3]=(byte)bcd;
            bcd=BCD.getBCDBit(value,8);         // 千万位
            ret[3]+=(((byte)bcd)<<4);

            bcd=BCD.getBCDBit(value,9);         // 亿位
            ret[4]=(byte)bcd;
            bcd=BCD.getBCDBit(value,10);         // 十亿位
            ret[4]+=(((byte)bcd)<<4);

            bcd=BCD.getBCDBit(value,11);         // 百亿位
            ret[5]=(byte)bcd;
            bcd=BCD.getBCDBit(value,12);         // 千亿位
            ret[5]+=(((byte)bcd)<<4);
        }catch (ParameterInvalidException e)
        {}

        return ret;
    }

    @Override
    public void init(byte[] in) {
        StringBuffer sb=new StringBuffer();
        for(int i=in.length-1;i>=0;i--){
            char ch= (char)((in[i]>>4)+'0');     // 高位
            sb.append(ch);
            ch=(char)(in[i]&0xF+'0');            // 低位
            sb.append(in[i]&0xF);

        }
        value=Long.valueOf(sb.toString());
    }

    @Override
    public Long getValue() {
        return this.value;
    }
}
