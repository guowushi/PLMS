package PLMS.DataForm;

import PLMS.DataUnits.AbstractDataFormat;
import PLMS.Exceptions.ParameterInvalidException;
import PLMS.Utils.BCD;
import PLMS.Utils.ByteUtil;


/**
 * 数据格式3-数字
 * 范围  0-xxxxxxx
 */
public class A03 extends AbstractDataFormat
{
    private String tips="数字的格式：0-XXXXXXX";
    public static final int LENGTH = 4;//长度

     public enum UNITS{
         KWH,
         MWH;        
    }
    // --------------------------------------------------
    public String dw;                     // 单位显示的字符串
    public long value;                   // 具体值
    private  int sign;                  // 正负
    private  int g;                     // 单位表示 ,0-单位为KWH/厘；1-单位为MWH/元
    private  UNITS unit;
    public  void setUnit(UNITS u)
    {
        if(u==UNITS.KWH){
            g=0;
        }else{
            g=1;
        }
    }
    public void setValue(long v)
    {
        if(v>0)
        {
            sign=0;
        }
        else{
            sign=1;
        }
    }
    // ------------------------------------------------
    public A03() {
    }

    public A03(byte[] in) {
          init(in);
    }
    // --------------------------------------------------
     @Override
    public int length() {
        return LENGTH; 
    }

    @Override
    public byte[] valueOf()  {
        byte[] out=new byte[LENGTH];
        try{
            out[0]=(byte) ( (BCD.getBCDBit(value,2)<<4) + BCD.getBCDBit(value,1) ) ;
            out[1]=(byte) ( (BCD.getBCDBit(value,4)<<4) + BCD.getBCDBit(value,3) ) ;
            out[2]=(byte) ( (BCD.getBCDBit(value,6)<<4) + BCD.getBCDBit(value,5) ) ;
            out[3]=(byte) (  (g<<6)+ (sign<<4) + BCD.getBCDBit(value,7) ) ;   
        } catch(ParameterInvalidException e) {
            
        }
        return out;
    }

    @Override
    public void init(byte[] in) {
        g= in[3] &0x40;
        if(g==0){
            dw="KWH/厘";

        }else
        {
            dw="MWH/元";
        }
        sign= in[3] & 0x10;
        in[3]=(byte)(in[3] & 0x0F);
        value=Long.parseLong(ByteUtil.bcd2Str(in));

    }

    @Override
    public <T> T getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //--------------------------------------------------------------------

}
