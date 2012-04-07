package PLMS.DataUnits.AFN04;

import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 参数设置--主站电话号码和短信中心号码
 */
public class F4 extends AbstractFn{
    private static final int LENGTH=16;
    private String telephone;
    private String smsCenter;
    // ------------------------------------------------------------
    public F4(){}
    public F4(byte[] in){init(in);}
    // ------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;  
        
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf=ByteBuffer.allocate(this.length());
        buf.put(phone2byte(this.telephone));
        buf.put(phone2byte(this.smsCenter));
        return buf.array();
        
    }

    /**
     *
     * @param phone
     * @return
     */
    public byte[] phone2byte(String phone)
    {
        int phone_max_length=16;
        ByteBuffer buf= ByteBuffer.allocate(phone_max_length);

       // byte[] ret =new byte[phone_max_length];
        // --------------号码补齐16位（0xF）---------------------------
        String phoneMax="";
        for(int i=0;i<phone_max_length;i++)
        {
            if(i<phone.length()){
                phoneMax=phoneMax+phone.substring(i,i+1);
                
            }else{
                phoneMax=phoneMax+"F";
            }

        }

        for(int i=0;i<phone_max_length;i++)
        {
               char  current_high=phoneMax.charAt(i);
               int tmp1;
               if(current_high!='F')
               {
                 tmp1 =Integer.parseInt(phoneMax.substring(i,i+1));  // 高4为
               }else
               {
                 tmp1=0xf;
               }

                //i++;
               char current_low=phoneMax.charAt(++i);
               int tmp2;
               if(current_low!='F'){
                     tmp2 =Integer.parseInt(phoneMax.substring(i,i+1));  // 低4位

                }else{
                     tmp2=0xf;
                }
               byte b=(byte)((tmp1<<4)+tmp2);
                buf.put(b);
        }
     //   if(b)
        //ret=
        return buf.array();
    }
    @Override
    public void init(byte[] in) {
        this.telephone="";
        for(int i=0;i<8 ;i++)
        {
            int tmp1= (in[i] & 0xf0) >> 4;
            int tmp2=  in[i] & 0x0f ;
            if(tmp1!=0xF) this.telephone=this.telephone+ String.valueOf(tmp1);
            if(tmp2!=0xF) this.telephone=this.telephone+ String.valueOf(tmp2);

        }
        int offset=8;
        this.smsCenter="";
        for(int i=0;i<8 ;i++)
        {
            int tmp1= (in[i+offset] & 0xf0) >> 4;
            int tmp2=  in[i+offset] & 0x0f ;
            if(tmp1!=0xF) this.smsCenter=this.smsCenter+ String.valueOf(tmp1);
            if(tmp2!=0xF) this.smsCenter=this.smsCenter+ String.valueOf(tmp2);

        }
        //-------------------------------------------------------------------
        
        
    }
}
