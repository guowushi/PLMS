package PLMS.DataUnits.AFN04;

import PLMS.DataForm.A19;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 参数设置---终端抄表日设置
 */
public class F7 extends AbstractFn {
    private final static int LENGTH = 6;
    private int[] meterReadingDate = new int[31]; //：D0~D30 按顺序对位表示每月 1 日~31 日，置“1”为有效，置“0”为无效。
    public A19 meterReadingTime;
    // --------------------------------------------------------
    public F7(){}
    public F7(byte[] in){
        init(in);
    }

    // --------------------------------------------------------
    public void setMeterReadingDate(int offset, boolean yn) {
        if (yn)
        { meterReadingDate[offset-1] = 1; }
        else
        {  meterReadingDate[offset-1] = 0;    }
    }

    // --------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        int tmp=0;
        for (int i = 0; i < meterReadingDate.length; i++) {
            tmp=tmp + (meterReadingDate[i]<< i );
        }

        buf.putInt(tmp);
        buf.put(meterReadingTime.valueOf());
        return buf.array();
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf= ByteBuffer.wrap(in);
        int tmp=buf.getInt();
        for (int i = 0; i < 32; i++) {
            int bit=    (tmp >>i  ) &0x1;
            if(bit==1){
                setMeterReadingDate(i+1,true);
            }else{
                setMeterReadingDate(i+1,false);
            }
        }
        byte[] a19=new byte[meterReadingTime.length()];
        buf.get(a19);
        this.meterReadingTime=new A19(a19);
    }
}
