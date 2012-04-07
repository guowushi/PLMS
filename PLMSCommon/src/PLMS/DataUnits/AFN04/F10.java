package PLMS.DataUnits.AFN04;

import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * 参数设置--终端电能表配置参数
 */
public class F10  extends AbstractFn{
    public static  final  int LENGTH=18;
    public int meterCount;
    public ArrayList<meterParam> meters=new ArrayList<meterParam>();     //电能表/交流采样装置
    // --------------------------------------------------
    public F10()
    {

    }
    public F10(byte[] in){
        init(in);
    }
    //-----------------------------------------------
    @Override
    public int length() {
        int len=0;
        for(meterParam e: meters){
          len=len+  e.length();
        }
        return len+1;
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        buf.put((byte)this.meterCount);
        for(meterParam e: meters){
            buf.put(e.valueOf());
        }
        return buf.array();
        
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf= ByteBuffer.wrap(in);
        this.meterCount=buf.get();
        for(int i=0;i<meterCount;i++)
        {

            meterParam param=new meterParam();

            byte[] b=new byte[param.length()];
            buf.get(b);
            param.init(b);
            meters.add(param);
        }

    }


}
