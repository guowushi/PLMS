package PLMS.DataUnits.AFN0E;

import PLMS.DataForm.A14;
import PLMS.DataForm.A15;
import PLMS.Enums.EVENT;

import java.nio.ByteBuffer;

/**
 * 电能表示度下降记录
 */
public class ERC27 extends  AbstactERC {
    private final int LENGTH=18;
    private A15 eventTime;         // 发生时间：分时日月年
    private  int measuringPoint;    //测量点号
    private A14 pap_before;         //下降前电能表正向有功总电能示值
    private A14 pap_after;          //下降后电能表正向有功总电能示值
    // ------------------------------------------------------------------
    public ERC27(){
        this.length=18;
    }
    public ERC27(byte[] in){
        init(in);
    }
    // ------------------------------------------------------------------
    @Override
    public int length() {
        return this.length;
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf= ByteBuffer.wrap(in);
        this.erc= EVENT.get(buf.get());
        this.length=buf.get();
        //---------------------------------------------------------
        byte[] bytes=new byte[eventTime.length()];
        buf.get(bytes);
        this.eventTime.init(bytes);

        byte tmp=buf.get();
        measuringPoint=tmp & 0x3f;
        bytes=new byte[pap_before.length()];
        buf.get(bytes);
        pap_before.init(bytes);

        bytes=new byte[pap_after.length()];
        buf.get(bytes);
        pap_after.init(bytes);

    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
