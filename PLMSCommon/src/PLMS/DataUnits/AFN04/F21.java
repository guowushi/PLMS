package PLMS.DataUnits.AFN04;

import PLMS.DataUnits.AbstractFn;

/**
 * 参数设置--终端电能表费率时段和费率
 * 每 30Min 以 4 位比特编码表示 14 种费率，取值 0~13 依次表示费率 1~费率 14。
 */
public class F21 extends AbstractFn{
    
    public static  final  int LENGTH=25;
    public int[] fees;
    private int fee_count=14;     //默认的费率数量
    // -------------------------------------------
    public F21( ) {
        fees=new int[48];    //共可以表示48个时段，每个时段指定一个费率
    }
    public F21( byte[] in ) {
        init(in);
    }
    // ----------------------------------------------------------
    public void setFee(int timeInterval ,int feeID)
    {
           fees[timeInterval]=feeID;
    }
    // -----------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;  
    }


    @Override
    public byte[] valueOf() {
        byte[] out =new byte[LENGTH];
        for(int i=0;i<fees.length;i++)
        {
            out[i/2]=(byte)( fees[i]+(fees[++i]<<4));
        }
        out[24]=(byte)fee_count;  //
        return out;
    }

    @Override
    public void init(byte[] in) {
        fees=new int[48];
        for(int i=0;i<fees.length;i++)
        {
            fees[i]= in[i/2]  & 0x0F;
            fees[++i]= in[i/2] >>4;
        }
        fee_count=in[24];

    }
}
